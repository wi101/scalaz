---
layout: docs
title:  "Either"
---

# Either [![GitHub](../img/github.png)](https://github.com/scalaz/scalaz/blob/series/8.0.x/std/shared/src/main/scala/either.scala)

Scalaz provides instances for Scala's built-in [Either](https://www.scala-lang.org/api/current/scala/util/Either.html) such as [Monad](../ct/Monad.html) and [Eq](../core/Eq.html).

**Typical imports**

```tut:silent
import scalaz.Scalaz._
import scalaz.std._
```

## Use the type class instances

```tut
val either1: Either[String, Int] = Right(1)
val either2: Either[String, Int] = Left("scalaz")

val fs: Either[String, Int => Int] = Right(_ * 2)

either1 === either2

either1.ap(fs)

either2.bimap(
 left => left.toUpperCase,
 right => right * 10
)

either1.debug.toString
either2.debug.toString
```
