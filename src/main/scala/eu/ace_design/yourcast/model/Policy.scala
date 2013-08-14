package eu.ace_design.yourcast.model

/**
 * A policy is considered as a source .
 * It basically implements a stackable modification to the source it is applied on, yielding a
 * modified (processed) Sequence of Information w.r.t. the one obtained from the source
 */
trait Policy extends Source {

  /**
   * The yieldWith method implements the stackable modifier, and thus cannot be override
   */
  final abstract override def yieldsWith(ctx: CallContext): Seq[Information] = process(super.yieldsWith(ctx))

  /**
   * The process method must be implemented. It realize the behavior of the policy
   */
  protected def process(in: Seq[Information]): Seq[Information]

}
