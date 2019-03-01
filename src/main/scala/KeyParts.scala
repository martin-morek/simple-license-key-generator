import java.time.LocalDate

import scala.util.{Success, Try}

object KeyParts {
  private def isValidDate(expirationDate: String): Boolean = {
    Try(LocalDate.parse(expirationDate)) match {
      case Success(_) => true
      case _ => false
    }
  }

  def stringFromParts(domainName: String, expirationDate: String): Either[String, String] = {
    if (isValidDate(expirationDate)) Right(domainName + "|" + expirationDate)
    else Left("Error: Invalid date format!")
  }

  def toParts(value: String): Either[String, KeyParts] = {
    value.split('|').toList match {
      case domain :: expirationDate :: Nil => Right(KeyParts(domain, LocalDate.parse(expirationDate)))
      case _ => Left("Error: Key parsing error!")
    }
  }
}

case class KeyParts(domainName: String, expirationDate: LocalDate)
