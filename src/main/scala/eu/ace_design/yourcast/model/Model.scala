package eu.ace_design.yourcast.model


trait Information

trait ScalarInformation extends Information

case class CompositeInformation(val contents: Seq[Information]) extends Information


trait Source {
  def yields(): CompositeInformation
}



