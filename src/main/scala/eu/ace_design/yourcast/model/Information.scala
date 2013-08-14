package eu.ace_design.yourcast.model


/**
 * Represents an Information in YourCast. This trait is sealed, as it should not directly be mixed in by users
 */
sealed trait Information

/**
 * A Scalar Information is an independent information.
 * This trait should be mixed for source specific information type
 */
trait ScalarInformation extends Information

/**
 * A Composite Information represent a set of related elements.
 * @param contents  the elements contained in this composite
 */
trait CompositeInformation extends Information  {
  def flattened: Seq[Information]
}

