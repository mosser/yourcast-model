package eu.ace_design.yourcast.policies

import eu.ace_design.yourcast.model.{CompositeInformation, Policy, Information}


trait Shuffle extends Policy {

   override protected def process(in: Seq[Information]): Seq[Information] = scala.util.Random.shuffle(in)

}
