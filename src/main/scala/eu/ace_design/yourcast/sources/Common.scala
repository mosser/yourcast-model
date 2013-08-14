package eu.ace_design.yourcast.sources

import eu.ace_design.yourcast.model.{CompositeInformation, ScalarInformation}
import java.util.Date
import java.awt.geom.Point2D

/**
 * Common Information Type used by multiple sources
 */
object Common {

  /**
   * A Picture Information represents an image to be displayed
   */
  case class Picture(name: String, url: String, date: Date) extends ScalarInformation

  /**
   *
   */
  case class PictureAlbum(name: String, date: Date, pictures: Seq[Picture]) extends CompositeInformation {
    def flattened: Seq[Picture] = pictures map { p => p.copy(name = this.name + " - " + p.name) }
  }


}
