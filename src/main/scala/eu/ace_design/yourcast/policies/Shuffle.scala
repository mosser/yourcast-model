package eu.ace_design.yourcast.policies

import eu.ace_design.yourcast.model.{CompositeInformation, Policy, Information}


trait Shuffle extends Policy {

   override protected def process(in: CompositeInformation): CompositeInformation = {
     val data: Seq[Information]     = in.contents
     val shuffled: Seq[Information] = scala.util.Random.shuffle(data)
     CompositeInformation(shuffled:_*)
   }

}
