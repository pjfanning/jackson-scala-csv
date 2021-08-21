package com.github.pjfanning.jackson.csv

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.dataformat.csv.{CsvMapper, CsvParser, CsvSchema}
import com.github.pjfanning.enumeratum.EnumeratumModule

import java.util.UUID
import scala.jdk.CollectionConverters._

case class NotificationConfigCsvDto(id: Option[UUID], notificationType: String, notificationName: String,
                                    disabled: Option[Boolean], userCountry: Option[String], polCountry: Option[String],
                                    podCountry: Option[String], templateName: Option[String], contentSubject: Option[String],
                                    contentLead: Option[String], contentMain: Option[String], contentCta: Option[String],
                                    toEmail: Option[String], toName: Option[String], loadType: Option[String],
                                    severity: Option[String], configFormat: Option[String], authProfile: Option[String],
                                    requestContentType: Option[String], delete: Option[Boolean] = Some(false))

object KMain extends App {
  val csvMapper = CsvMapper.builder()
    .addModule(DefaultScalaModule)
    .addModule(EnumeratumModule)
    .enable(CsvParser.Feature.TRIM_SPACES)
    .build()

  val readerSchema = CsvSchema.builder().setUseHeader(true).setReorderColumns(true).build()
  val ncIterator: MappingIterator[NotificationConfigCsvDto] = csvMapper.readerFor(classOf[NotificationConfigCsvDto]).`with`(readerSchema)
    .readValues(FileUtil.readBytes("nc.csv"))
  val ncs = ncIterator.readAll().asScala.toSeq
  println(s"read $ncs")

  val writerSchema = csvMapper.schemaFor(classOf[NotificationConfigCsvDto]).withHeader
  println(s"schema: ${writerSchema.getColumnDesc}")
  val outputCsv = csvMapper.writerFor(new TypeReference[Seq[NotificationConfigCsvDto]]{}).`with`(writerSchema).writeValueAsString(ncs)
  println(s"wrote:\n$outputCsv")
}
