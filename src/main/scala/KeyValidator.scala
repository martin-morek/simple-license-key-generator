import java.net.InetAddress
import java.time.LocalDate

import KeyParts._

import scala.util.{Failure, Success, Try}

object KeyValidator extends KeyValidator{
  def main(args: Array[String]) {
    println(validateKey(args(0)))
  }
}

trait KeyValidator extends Encryption {
  def validateKey(key: String): String = {
    Try(decrypt(key)) match {
      case Success(v) =>
        toParts(v) match {
          case Right(keyParts) =>
            if (keyParts.expirationDate.isAfter(LocalDate.now())
              && InetAddress.getLocalHost().getHostName() == keyParts.domainName) "valid"
            else "invalid"
          case Left(e) => e
        }
      case Failure(e) => "Error: Invalid key format!"
    }
  }
}