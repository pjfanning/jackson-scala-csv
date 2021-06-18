package com.github.pjfanning.jackson.csv

import org.apache.commons.io.IOUtils

import java.io.FileNotFoundException
import scala.util.Using

object FileUtil {
  def readBytes(fn: String): Array[Byte] = {
    Option(Thread.currentThread().getContextClassLoader.getResourceAsStream(fn)) match {
      case Some(stream) => {
        Using.resource(stream)(is => IOUtils.toByteArray(is))
      }
      case _ => throw new FileNotFoundException(s"failed to find $fn")
    }
  }
}
