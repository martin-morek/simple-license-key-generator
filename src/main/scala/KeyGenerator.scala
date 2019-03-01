import KeyParts._

object KeyGenerator extends KeyGenerator {

  def main(args: Array[String]) {
    println(generateKey(args(0), args(1)))
  }
}

trait KeyGenerator extends Encryption {

  def generateKey(domainName: String, expirationDate: String): String = {
    stringFromParts(domainName, expirationDate) match {
      case Right(v) => encrypt(v)
      case Left(e) => e
    }
  }
}