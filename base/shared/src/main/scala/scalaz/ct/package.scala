package scalaz

package object ct {

  val Endo: EndoModule = EndoImpl
  type Endo[=>:[_, _], A] = Endo.Endo[=>:, A]

  val Kleisli: KleisliModule = KleisliImpl
  type Kleisli[F[_], A, B] = Kleisli.Kleisli[F, A, B]
}
