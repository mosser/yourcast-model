package eu.ace_design.yourcast.model


/**
 * A source provides Information tot he YourCast system, based on a Call Context
 */
trait Source {

  /**
   * The yieldsWith method is the one publicly called. It verifies that the provided CallContext is
   * sufficient for this source, and if so delegate the invocation to the call method
   * @param ctx the CallContext ti be used
   * @return the Sequence of Information associated to this CallContext for this Source
   * @throws IllegalArgumentException  if the Call Context is insufficient
   */
  def yieldsWith(ctx: CallContext): Seq[Information] = {
    require(ctx canProvide needed, "The call context cannot provide expected parameters")
    call(ctx)
  }

  /**
   * The needed method returns the keys expected in the call context for proper execution
   */
  protected def needed: Set[String]

  /**
   * The call method actually invoke the source and produce a Sequence of Information
   * @param ctx  the ContextCall used to configure the Source (~ input parameters)
   * @return  the Sequence of Information associated to this CallContext for this Source
   */
  protected def call(ctx: CallContext): Seq[Information]

}
