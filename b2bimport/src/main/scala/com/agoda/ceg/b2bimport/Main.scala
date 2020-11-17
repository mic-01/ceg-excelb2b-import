package com.agoda.ceg.b2bimport

import java.io.{File, InputStream}
import java.text.SimpleDateFormat
import java.util.Calendar
import org.apache.poi.ss.usermodel.DataFormatter

import adp.shaded.org.joda.time.DateTime
import com.agoda.ml.spark.SparkSqlMain
import com.agoda.ml.spark.time.TimeUtils.getNominalTime
import com.typesafe.scalalogging.LazyLogging
import org.apache.hadoop.fs.Path
import org.apache.poi.ss.usermodel.{Row, WorkbookFactory}
import org.apache.spark.sql.SparkSession
import spire.compat.fractional

import scala.collection.JavaConverters.iterableAsScalaIterableConverter
import scala.math.Fractional.Implicits.infixFractionalOps

object Main extends SparkSqlMain with LazyLogging {

  def run(args: Array[String]): Unit = {

    implicit val sparkSession: SparkSession = spark

    val dt: InputStream = fs.getInputStream(new Path("/user/bi_ceg/adhoc_files/b2b-import/Write-off Detail Aug 2020.xlsx"))

    val workbook = WorkbookFactory.create(dt)
    val sheet = workbook.getSheetAt(0) // Assuming they're in the first sheet here.

    val sheetS: Iterable[Row] = sheet.asScala.drop(0)

    val data: Seq[Excel] =  sheetS.map(row => {

      Excel(
        Option(row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(3.toShort, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(7, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(8, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(9, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(10, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(11, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(12, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(13, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(14, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(15, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(16, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(17, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(18, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString),
        Option(row.getCell(19, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).map(e=>e.toString)
      )
    }).toSeq

    (0 to 10).foreach{
      c=>
        logger.info(s"Excel Row $c=>${data.lift(c)}")
    }

    LoaderWrapper(conf.getConfig("hadoop")).insertIntoTable(data)

  }
}
