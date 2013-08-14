package eu.ace_design.yourcast.policies

import org.specs2.mutable._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

import eu.ace_design.yourcast.model.{Information, ScalarInformation, CallContext, Source}

@RunWith(classOf[JUnitRunner])
class ShuffleTest extends SpecificationWithJUnit {

  "Specification of the Shuffle Policy".title

  "When applied, the shuffle policy" should {
    val initial: Seq[Information]    = (new Dummy() yieldsWith CallContext())
    val withPolicy: Seq[Information] = (new Dummy() with Shuffle yieldsWith CallContext())

    "keep the same number of elements" in { withPolicy must have size(initial.length) }

    "not change the elements" in { withPolicy must containTheSameElementsAs(initial)  }
  }

  /*********************
   ** Private Helpers **
   *********************/

  // Private type of Information, for testing purpose
  private case class DummyInfo(val i: Int) extends ScalarInformation

  // private Source of information, for testing purpose
  private class Dummy extends Source {
    val needed: Set[String] = Set()
    override def call(ctx: CallContext): Seq[Information] = for(i <- 1 to 6) yield DummyInfo(i)
  }

}