package eu.ace_design.yourcast.model

import org.specs2.mutable._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner


@RunWith(classOf[JUnitRunner])
class CallContextTest extends SpecificationWithJUnit {

  "Call Context specification".title

  "By construction, a CallContext" should {

    val context = CallContext("user" -> "myUserName", "apiKey" -> "myApiKey")
    val empty = CallContext()

    "be empty by default"        in { empty.keys must be empty }
    "store pairs"                in { context.keys must contain(exactly("user", "apiKey")) }
    "give access to a named key" in { context get "user" must_== "myUserName" }
    "reject an unknown key"      in { context get "foo"  must throwA[NoSuchElementException] }
  }

  "When merged, a CallContext" should {

    val general      = CallContext("apiKey" -> "SystemApiKey")
    val userSpecific = CallContext("user"   -> "myUserName")
    val dummy        = CallContext("foo"    -> "bar", "user" -> "anotherUserName")

    "be the union of initial contexts when no overlap" in {
      val context = general U userSpecific
      context.keys must contain(exactly("user", "apiKey"))
    }

    "return a CallContext for chaining purpose" in {
      val context = general U dummy U userSpecific
      context.keys must contain(exactly("user", "apiKey", "foo"))
    }

    "take the last pairs when the contexts overlap" in {
      val context = general U dummy U userSpecific
      context get "user" must_== (userSpecific get "user")
    }
  }
}
