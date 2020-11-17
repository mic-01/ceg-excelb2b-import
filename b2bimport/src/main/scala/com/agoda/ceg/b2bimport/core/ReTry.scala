package com.agoda.ceg.b2bimport.core

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration

object ReTry extends LazyLogging {
  def apply[T](maxAttempts: Int, delay: FiniteDuration)(r: => Future[T], failoverFn: Option[() ⇒ Unit]): Future[T] = doReTryInternal(maxAttempts, 1, delay.toMillis)(r, failoverFn)

  def doReTryInternal[T](times: Int, attempt: Int, delay: Long)(block: => Future[T], failoverFn: Option[() ⇒ Unit]): Future[T] = {
    block.recoverWith {
      case ex: Throwable =>
        if (attempt < times) {
          logger.error("Retrying: " + ex.getMessage, delay)
          if (delay > 0)
            Thread.sleep(delay)
          doReTryInternal(times, attempt + 1, delay)(block, failoverFn)
        } else if (failoverFn.isDefined){
          val fn = failoverFn.get
          fn()
          doReTryInternal(times,  attempt - times + 1, delay)(block, failoverFn)
        }
        else Future.failed(ex)
    }
  }
}