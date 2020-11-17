package com.agoda.ceg.b2bimport.core

import com.agoda.hadoop.fs.FileSystemOperation
import org.apache.hadoop.fs.Path
import spire.ClassTag

object FileSystem extends AutoCloseable {

  private val fs = FileSystemOperation.getOrCreate()

  def readData[T: ClassTag](pathString: String): Option[T] = {

    val path = new Path(pathString)
    if (fs.checkIfFileExists(path)) {
      Some(fs.loadObject[T](path))
    } else {
      None
    }
  }

  def writeData[T: ClassTag](pathString: String, data: T): Unit = {
    val path = new Path(pathString)
    fs.dumpObject(data.asInstanceOf[AnyRef], path)
  }

  def fileExists(pathString:String):Boolean={
    val path = new Path(pathString)
    fs.checkIfFileExists(path)
  }

  override def close(): Unit = {
    fs.close()
  }
}
