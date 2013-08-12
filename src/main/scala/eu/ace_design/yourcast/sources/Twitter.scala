package eu.ace_design.yourcast.sources

import eu.ace_design.yourcast.model._

class Twitter extends Source {

  def yields(): CompositeInformation = {
    CompositeInformation(Seq())
  }


}
