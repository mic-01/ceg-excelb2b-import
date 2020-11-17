package com.agoda.ceg.b2bimport.core

import java.lang.reflect.Field
import java.security.MessageDigest

import org.joda.time.DateTime

import scala.concurrent.duration.DurationConversions.Classifier
import scala.reflect.ClassTag

object Utils {
  def camelCaseToSnakeCase(str: String): String =
    "[A-Z]".r.replaceAllIn(str,m=>s"_${m.toString.toLowerCase}")

  def getFieldNames[T:ClassTag]:Seq[String]=
    implicitly[ClassTag[T]].runtimeClass.getDeclaredFields.map { m: Field => m.getName }.toSeq

  def hashMd5(str:String):String=
    MessageDigest.getInstance("MD5").digest(str.getBytes).map(0xFF & _).map { "%02x".format(_) }.foldLeft(""){_ + _}

  implicit def toDatetime(c:Long): DateTime =new DateTime(c)
}
