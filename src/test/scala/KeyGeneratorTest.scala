import java.net.InetAddress

import org.scalatest.FunSuite

class KeyGeneratorTest extends FunSuite {

  trait MockedEncryption extends Encryption {
    override def encrypt(value: String): String = "new-key"
  }

  val keyGenerator = new KeyGenerator with MockedEncryption

  test("generateKey should generate new key for valid date") {
    val newKey = keyGenerator.generateKey(s"${InetAddress.getLocalHost().getHostName()}", "2019-03-01")
    assert(newKey === "new-key")
  }

  test("generateKey should return error for invalid date") {
    val error = keyGenerator.generateKey(s"${InetAddress.getLocalHost().getHostName()}", "not-a-date")
    assert(error === "Error: Invalid date format!")
  }
}
