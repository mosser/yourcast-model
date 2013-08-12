package eu.ace_design.yourcast.model

/**
 * A CallContext represents the information needed by a source to properly retrieves information
 * @param given a sequence of key-value pairs representing these information
 */
case class CallContext(given: (String,String)*) {

  // internal representation (a very basic map)
  private val parameters: Map[String, String] = given.toMap

  // returns the keys stored in this context, as a Set
  def keys: Set[String] = parameters.keySet

  // returns the value associated to a given key, a NoSuchElementException elsewhere
  def get(k: String): String = parameters(k)

  // merge this with another context (named that).
  def U(that: CallContext): CallContext =  CallContext((parameters.toSeq ++ that.parameters.toSeq):_*)

  // Check if this context contains some needed elements
  def canProvide(needed: Set[String]): Boolean = (keys intersect needed) == needed
}
