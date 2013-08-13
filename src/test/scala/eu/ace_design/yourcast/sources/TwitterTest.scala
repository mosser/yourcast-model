package eu.ace_design.yourcast.sources

import org.specs2.mutable._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

import eu.ace_design.yourcast.model.{CallContext, Source}
import eu.ace_design.yourcast.sources.twitter._


@RunWith(classOf[JUnitRunner])
class TwitterTest extends SpecificationWithJUnit {

  "Specification of the Twitter Source".title

  val systemContext = CallContext("authorization" -> "TWITTER_API_KEY")

  "The Search source" should {
    val source = new Search()

    "be considered as a source"      in { source must beAnInstanceOf[Source] }
    "reject an insufficient context" in {
      val insufficientContext = CallContext()
      source yieldsWith insufficientContext must throwAn[IllegalArgumentException]
    }
    "accept a valid context" in {
      val context = systemContext U CallContext("query" -> "anonymous")
      source yieldsWith context must not be empty
    }
  }

  "The TimeLine source" should {
    val source = new TimeLine()

    "be considered as a source"      in { source must beAnInstanceOf[Source] }
    "reject an insufficient context" in {
      val insufficientContext = CallContext()
      source yieldsWith insufficientContext must throwAn[IllegalArgumentException]
    }
    "accept a valid context" in {
      val context = systemContext U CallContext("screen_name" -> "myUserName")
      source yieldsWith context must not be empty
    }
  }

}
