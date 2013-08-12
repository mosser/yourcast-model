package eu.ace_design.yourcast.model


case class CallContext(given: (String,String)*) {

  private val parameters: Map[String, String] = given.toMap

  def keys: Set[String] = parameters.keySet

  def get(k: String): String = parameters(k)

  def U(that: CallContext): CallContext =  CallContext((parameters.toSeq ++ that.parameters.toSeq):_*)

}
