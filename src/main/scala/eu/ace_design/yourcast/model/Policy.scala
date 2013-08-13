package eu.ace_design.yourcast.model


trait Policy extends Source {

  abstract override def yieldsWith(ctx: CallContext): CompositeInformation = {
    process(super.yieldsWith(ctx))
  }

  protected def process(in: CompositeInformation): CompositeInformation

}
