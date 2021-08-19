package com.github.pjfanning.jackson.csv

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.dataformat.csv.{CsvMapper, CsvParser}

import scala.jdk.CollectionConverters._

case class OrderLine(item: String, quantity: Int, unitPrice: BigDecimal)

object Main extends App {
  val csvMapper = CsvMapper.builder()
    .addModule(DefaultScalaModule)
    .enable(CsvParser.Feature.TRIM_SPACES)
    .build()
  val orderLineSchema = csvMapper.schemaFor(classOf[OrderLine]).withHeader
 println(s"schema: ${orderLineSchema.getColumnDesc}")
  val orderLinesIterator: MappingIterator[OrderLine] = csvMapper.readerFor(classOf[OrderLine]).`with`(orderLineSchema)
    .readValues(FileUtil.readBytes("orderLines.csv"))
  val orderLines = orderLinesIterator.readAll().asScala.toSeq
  println(s"read $orderLines")

  val outputCsv = csvMapper.writerFor(new TypeReference[Seq[OrderLine]]{}).`with`(orderLineSchema).writeValueAsString(orderLines)
  println(s"wrote:\n$outputCsv")
}
