package com.agoda.ceg.b2bimport

import com.agoda.ceg.b2bimport.core.Utils.{camelCaseToSnakeCase, getFieldNames}
import com.agoda.ml.spark.services.etl.ETLInsert._
import com.agoda.ml.spark.services.etl.LoaderRequest
import com.agoda.ml.spark.services.etl.contexts.PartitionRequestParams
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{date_format, from_unixtime}

object LoaderWrapper {
  def apply(appConfig: Config)(implicit sparkSession: SparkSession): LoaderWrapper = {

    val hadoopDatabase = appConfig.getString("table.database")
    val hadoopTableName = appConfig.getString("table.name")

    val LoaderReq = LoaderRequest(hadoopDatabase, hadoopTableName)

    new LoaderWrapper(LoaderReq, appConfig)
  }
}

class LoaderWrapper(smartLoaderReq: LoaderRequest, appConfig: Config)(implicit val sparkSession: SparkSession) extends LazyLogging{
  def insertIntoTable(data: Seq[Excel]): Unit = {

    import sparkSession.implicits._
    val partitionNum = appConfig.getInt("spark-task-partitions")
    logger.info(s"Partitions Specified$partitionNum")

    val rdd = sparkSession.sparkContext.parallelize(data, partitionNum)
    logger.info(s"RDD Created")

    val columnNames: Seq[String] = getFieldNames[Excel].map{c => camelCaseToSnakeCase(c)}
    logger.info(s"Columns Names:${columnNames.mkString(",")}")

    val df = rdd.toDF(columnNames:_*)

    df.insertIntoTable(smartLoaderReq)
  }
}