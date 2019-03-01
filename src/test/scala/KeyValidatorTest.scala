import java.net.InetAddress

import javax.crypto.IllegalBlockSizeException
import org.scalatest.FunSuite

class KeyValidatorTest extends FunSuite {

  trait MockedEncryption extends Encryption {
    override def decrypt(encryptedValue: String): String = encryptedValue match {
      case "valid-key" => s"${InetAddress.getLocalHost().getHostName()}|2020-01-01"
      case "valid-key-diff-domain" => s"different-domain-name.com|2020-01-01"
      case "expired-key" => s"${InetAddress.getLocalHost().getHostName()}|2008-01-01"
      case "wrong-format-key" => ""
      case "random-string" => throw new IllegalBlockSizeException
    }
  }

  val keyValidator = new KeyValidator with MockedEncryption

  test("validateKey should return 'valid' if date hashed in key is in the future") {
    val result = keyValidator.validateKey("valid-key")
    assert(result === "valid")
  }

  test("validateKey should return 'invalid' if date hashed in key is in the future but different domain") {
    val result = keyValidator.validateKey("valid-key-diff-domain")
    assert(result === "invalid")
  }

  test("validateKey should return 'invalid' if date hashed in key is in the past") {
    val result = keyValidator.validateKey("expired-key")
    assert(result === "invalid")
  }

  test("validateKey should return error if key cannot be parsed") {
    val result = keyValidator.validateKey("wrong-format-key")
    assert(result === "Error: Key parsing error!")
  }

  test("validateKey should return error if key is some random string") {
    val result = keyValidator.validateKey("random-string")
    assert(result === "Error: Invalid key format!")
  }

}
