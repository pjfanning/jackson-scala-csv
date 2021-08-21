package com.github.pjfanning.jackson.csv

import enumeratum.{Enum, EnumEntry, PlayFormFieldEnum, PlayInsensitiveJsonEnum, PlayPathBindableEnum, UrlBinders}
import play.api.mvc.QueryStringBindable

trait PlayQueryBindableCaseInsenstiveEnum[A <: EnumEntry] { self: Enum[A] =>
  implicit val queryBindable: QueryStringBindable[A] =
    UrlBinders.queryBinder(self, insensitive = true)
}

trait BaseEnum[A <: EnumEntry]
  extends Enum[A]
    with PlayInsensitiveJsonEnum[A]
    with PlayPathBindableEnum[A]
    with PlayQueryBindableCaseInsenstiveEnum[A]
    with PlayFormFieldEnum[A]
