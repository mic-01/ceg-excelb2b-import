package com.agoda.ceg.b2bimport

import com.agoda.ceg.b2bimport.core.FileSystem
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import org.apache.hadoop.security.authentication.util.AuthToken

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}

object AuthManager extends LazyLogging{
  private val config = ConfigFactory.load()
  private val hdfsPath = config.getString("sprinklr-api.auth-tokens.path")

//  private val hdfsPath = "ceg-sprinklr-import/sprinklrimport/auth-tokens.txt"

  def read(defaultInitialToken: AuthToken) : Try[AuthToken] ={
    logger.info(s"Reading token from hdfs Path:$hdfsPath")

    if(!FileSystem.fileExists(hdfsPath)){
      write(defaultInitialToken)
    }

    Try(FileSystem.readData[AuthToken](hdfsPath)).map(_.getOrElse(defaultInitialToken))
  }

  def write(authToken: AuthToken):Unit={
    logger.info(s"Writing token to hdfs Path:$hdfsPath")
    FileSystem.writeData[AuthToken](hdfsPath,authToken)
  }
}
