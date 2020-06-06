package squire4s

import shapeless._
import shapeless.ops.coproduct.Selector
import shapeless.ops.coproduct.Inject

/**
  * The algebra for lifting values into the 'dynamic' parts of a query.
  *
  * Think filling ? in a parameterized query
  */
trait Embed[From, Into] {
  def embed(a: From): Into
}

object Embed extends EmbedLowPriorityInstances {
  def apply[From, Into](implicit instance: From Embed Into): From Embed Into =
    instance

  def instance[From, Into](f: From => Into): From Embed Into =
    new Embed[From, Into] {
      def embed(a: From): Into = f(a)
    }

  // Allow packaging multiple embeds into an embed coproduct
  // This allows users to not need to expose quite so many unique implicit parameters in their programs

  // CNil is vacuously embeddable into all targets
  implicit def deriveBaseCoproduct[Into]: CNil Embed Into =
    Embed.instance(_.impossible)

  // Induce a coproduct embed
  implicit def deriveInductiveCoproduct[H, T <: Coproduct, Into](
      implicit H: H Embed Into,
      T: T Embed Into,
      notCo: H <:!< Coproduct
  ): (H :+: T) Embed Into =
    Embed.instance(input => input.eliminate(H.embed, T.embed))
}

trait EmbedLowPriorityInstances {
  // Given an Co Embed Into we can derive a T Embed Into if T is within Co
  // Allows tearing an Embed for a Coproduct back apart for use
  // We place this in low priority instances because we already have an inject for T Embed Into
  // in scope or else we wouldn't have been able to build the coproduct in the first place
  implicit def deriveExtractCoproduct[Co <: Coproduct, T, Into](
      implicit Co: Co Embed Into,
      inj: Inject[Co, T]
  ): T Embed Into =
    Embed.instance(input => Co.embed(inj(input)))
}
