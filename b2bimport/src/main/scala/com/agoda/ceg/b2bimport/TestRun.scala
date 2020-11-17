package com.agoda.ceg.b2bimport

import java.io.File

import scala.collection.JavaConverters.iterableAsScalaIterableConverter
import com.agoda.ceg.b2bimport.Main.logger
import org.apache.poi.ss.usermodel.{Row, WorkbookFactory}

object TestRun  extends  App {
  val dest = "\\\\bk-agfil-1001\\Department\\MK\\Partner Marketing\\Operations\\Payment\\B2B Compensation Write off (Invoice)\\Write-off Detail"

  val directory = new File(dest)

  val allFiles = directory.listFiles
    .filter(_.isFile)
    .sortBy(-_.lastModified())
    .toList
  val latestFile = allFiles(0)

  val f = new File(latestFile.toString)
  val workbook = WorkbookFactory.create(f, null, true)
  val sheet = workbook.getSheetAt(0) // Assuming they're in the first sheet here.

//  for (row <- sheet.asScala) {
//          Excel(
//            (Option(row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(7, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(8, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(9, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(10, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(11, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(12, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(13, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(14, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(15, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(16, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(17, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(18, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString,
//            (Option(row.getCell(19, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL))).toString
//          )
//        }
}
