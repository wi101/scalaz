package scalaz
package std

trait IterableInstances {

  implicit def IterableShow[CC[X] <: Iterable[X], A: Show]: Show[CC[A]] = new Show[CC[A]] {
    import syntax.show._
    def show(as: CC[A]) = {
      val i = as.iterator
      val k = new collection.mutable.ListBuffer[Char]
      k += '['
      while (i.hasNext) {
        val n = i.next
        k ++= n.show
        if (i.hasNext)
          k += ','
      }
      k += ']'
      k.toList
    }
  }

  implicit def IterableOrder[A: Order]: Order[Iterable[A]] = new Order[Iterable[A]] {
    def order(a1: Iterable[A], a2: Iterable[A]) = {
      import scalaz.Ordering._
      import syntax.order._
      val i1 = a1.iterator
      val i2 = a2.iterator
      var b = true
      var r: Ordering = EQ

      while (i1.hasNext && i2.hasNext && b) {
        val a1 = i1.next
        val a2 = i2.next

        val o = a1 ?|? a2
        if (o != EQ) {
          r = o
          b = false
        }
      }
      if (i1.hasNext)
        if (i2.hasNext)
          r
        else
          GT
      else
        LT
    }
  }

  implicit def IterableLength: Length[Iterable] = new Length[Iterable] {
    def length[A](a: Iterable[A]) = {
      var n = 0
      val i = a.iterator
      while (i.hasNext) {
        n = n + 1
        i.next
      }
      n
    }
  }

  def IterableEqual[CC[X] <: Iterable[X], A: Equal]: Equal[CC[A]] = new Equal[CC[A]] {
    import syntax.equal._
    def equal(a1: CC[A], a2: CC[A]) = {
      val i1 = a1.iterator
      val i2 = a2.iterator
      var b = false

      while (i1.hasNext && i2.hasNext && !b) {
        val x1 = i1.next
        val x2 = i2.next

        if (x1 /== x2) {
          b = true
        }
      }

      !(b || i1.hasNext || i2.hasNext)
    }
  }
}

object iterable extends IterableInstances {
  object iterableSyntax extends scalaz.syntax.std.ToIterableV
}