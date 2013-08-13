package eu.ace_design.yourcast.model


trait Source {

  def needed: Set[String]

  def yieldsWith(ctx: CallContext): CompositeInformation = {
    require(ctx canProvide needed, "The call context cannot provide expected parameters")
    call(ctx)
  }

  protected def call(ctx: CallContext): CompositeInformation

}
