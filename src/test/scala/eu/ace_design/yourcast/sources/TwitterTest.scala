package eu.ace_design.yourcast.sources

import org.specs2.mutable._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

import eu.ace_design.yourcast.model._

@RunWith(classOf[JUnitRunner])
class TwitterTest extends SpecificationWithJUnit {

  "Specification of the Twitter Source".title

  "Twitter" should {
    val source = new Twitter()
    "be considered as a source" in { source must beAnInstanceOf[Source] }
    "yields an empty CompositeInformation" in { true must beTrue }
  }


}
