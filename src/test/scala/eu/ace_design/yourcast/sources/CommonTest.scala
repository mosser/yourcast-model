package eu.ace_design.yourcast.sources

import org.specs2.mutable._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import eu.ace_design.yourcast.sources.Common._
import eu.ace_design.yourcast.model._

@RunWith(classOf[JUnitRunner])
class CommonTest extends SpecificationWithJUnit {

  "CommonTest Specifications".title

  "A Picture Album" should {

    val empty = PictureAlbum(name = "My Empty Album", date = "1970-01-01 00:01", pictures = Seq())

    val filled = PictureAlbum(name = "My Filled Album", date = "1970-01-01 00:01",
                              pictures = for(i <- 1 to 9)
                                            yield Picture(name = s"Pict #$i",
                                                          date = s"1970-01-01 00:0$i",
                                                          url  = s"http://.../$i.jpg"))

    "be a CompositeInformation" in { empty must beAnInstanceOf[CompositeInformation] }

    "support flattening when empty" in { (empty flattened) must beEmpty }

    "contains the same number of elements when flattened" in {
      (filled flattened) must have length(filled.pictures.length)
    }

    "propagate its name in the flattened pictures" in {
      (filled flattened)  must contain( (p: Picture) => p.name must startWith(filled.name) ).foreach

    }
  }


}