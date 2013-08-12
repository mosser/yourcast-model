package eu.ace_design.yourcast.model

import org.specs2.mutable._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner


@RunWith(classOf[JUnitRunner])
class CallContextTest extends SpecificationWithJUnit {

  "Call Context specification".title

  "By construction, a CallContext" should {

    val context = CallContext("user" -> "myUserName", "apiKey" -> "myApiKey")

    "be empty by default"        in { CallContext().keys must be empty                       }
    "store pairs"                in { context.keys must contain(exactly("user", "apiKey"))   }
    "give access to a named key" in { context get "user" must_== "myUserName"                }
    "reject an unknown key"      in { context get "foo"  must throwA[NoSuchElementException] }
  }

  "For checking purpose, a CallContext" should {
    val context = CallContext("user" -> "myUserName", "apiKey" -> "myApiKey")
    "provide its contents"     in { context canProvide Set("user", "apiKey") must beTrue }
    "provide a subset"         in { context canProvide Set("user") must beTrue           }
    "reject a missing element" in { context canProvide Set("user", "foo") must beFalse   }
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
      val first =  general U dummy
      val second = first U userSpecific
      first  get "user" must_== (dummy        get "user")
      second get "user" must_== (userSpecific get "user")
    }
  }
}
