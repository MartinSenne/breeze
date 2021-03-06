package breeze.math
/*
 Copyright 2012 David Hall

 Licensed under the Apache License, Version 2.0 (the "License")
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
import breeze.storage.DefaultArrayValue
import scala.reflect.ClassTag


/**
 * Marker trait for scalar values.  Scalars must be immutable.
 * TODO: maybe use spire for the basis of this?
 *
*  @author dlwh
 */
trait Field[@specialized(Int,Short,Long,Float,Double) V] extends Ring[V] {
  def /(a : V, b : V) : V
  def inverse(a: V) = /(one, a)

}

object Field {
  implicit object fieldInt extends Field[Int] {
    def zero = 0
    def one = 1
    def nan = throw new ArithmeticException("Operation resulted in integer-valued NaN")
    def ==(a : Int, b : Int) = a == b
    def !=(a : Int, b : Int) = a != b
    def +(a : Int, b : Int) = a + b
    def -(a : Int, b : Int) = a - b
    def *(a : Int, b : Int) = a * b
    def /(a : Int, b : Int) = a / b
    def norm(a : Int) = if (a < 0) -a else a
    def toDouble(a : Int) = a
    def isNaN(a : Int) = false
    val manifest = implicitly[ClassTag[Int]]
    val defaultArrayValue = implicitly[DefaultArrayValue[Int]]


  }

  implicit object fieldShort extends Field[Short] {
    def zero = 0.asInstanceOf[Short]
    def one = 1.asInstanceOf[Short]
    def nan = throw new ArithmeticException("Operation resulted in short-valued NaN")
    def ==(a : Short, b : Short) = a == b
    def !=(a : Short, b : Short) = a != b
    def +(a : Short, b : Short) = (a + b).asInstanceOf[Short]
    def -(a : Short, b : Short) = (a - b).asInstanceOf[Short]
    def *(a : Short, b : Short) = (a * b).asInstanceOf[Short]
    def /(a : Short, b : Short) = (a / b).asInstanceOf[Short]
    def norm(a : Short) = if (a < 0) -a else a
    def toDouble(a : Short) = a
    def isNaN(a : Short) = false
    val manifest = implicitly[ClassTag[Short]]
    val defaultArrayValue = implicitly[DefaultArrayValue[Short]]
  }

  implicit object fieldLong extends Field[Long] {
    def zero = 0l
    def one = 1l
    def nan = throw new ArithmeticException("Operation resulted in long-valued NaN")
    def ==(a : Long, b : Long) = a == b
    def !=(a : Long, b : Long) = a != b
    def +(a : Long, b : Long) = a + b
    def -(a : Long, b : Long) = a - b
    def *(a : Long, b : Long) = a * b
    def /(a : Long, b : Long) = a / b
    def norm(a : Long) = if (a < 0) -a else a
    def toDouble(a : Long) = a
    def isNaN(a : Long) = false
    val manifest = implicitly[ClassTag[Long]]
    val defaultArrayValue = implicitly[DefaultArrayValue[Long]]
  }

  implicit object fieldBigInt extends Field[BigInt] {
    def zero = 0l
    def one = 1l
    def nan = throw new ArithmeticException("Operation resulted in long-valued NaN")
    def ==(a : BigInt, b : BigInt) = a == b
    def !=(a : BigInt, b : BigInt) = a != b
    def +(a : BigInt, b : BigInt) = a + b
    def -(a : BigInt, b : BigInt) = a - b
    def *(a : BigInt, b : BigInt) = a * b
    def /(a : BigInt, b : BigInt) = a / b
    def norm(a : BigInt) = if (a < 0) (-a).toDouble else a.toDouble
    def toDouble(a : BigInt) = a
    def isNaN(a : BigInt) = false
    val manifest = implicitly[ClassTag[BigInt]]
    val defaultArrayValue = implicitly[DefaultArrayValue[BigInt]]
  }

  implicit object fieldFloat extends Field[Float] {
    def zero = 0.0f
    def one = 1.0f
    def nan = Float.NaN
    def ==(a : Float, b : Float) = a == b
    def !=(a : Float, b : Float) = a != b
    def +(a : Float, b : Float) = a + b
    def -(a : Float, b : Float) = a - b
    def *(a : Float, b : Float) = a * b
    def /(a : Float, b : Float) = a / b
    def norm(a : Float) = if (a < 0) -a else a
    def toDouble(a : Float) = a
    def isNaN(a : Float) = java.lang.Float.isNaN(a)
    val manifest = implicitly[ClassTag[Float]]
    val defaultArrayValue = implicitly[DefaultArrayValue[Float]]

    override def close(a: Float, b: Float, tolerance: Double) = (a-b).abs <= math.max(a.abs, b.abs) * tolerance
  }

  implicit object fieldD extends Field[Double] {
    def zero = 0.0
    def one = 1.0
    def nan = Double.NaN
    def ==(a : Double, b : Double) = a == b
    def !=(a : Double, b : Double) = a != b
    def +(a : Double, b : Double) = a + b
    def -(a : Double, b : Double) = a - b
    def *(a : Double, b : Double) = a * b
    def /(a : Double, b : Double) = a / b
    def norm(a : Double) = if (a < 0) -a else a
    def toDouble(a : Double) = a
    def isNaN(a : Double) = java.lang.Double.isNaN(a)
    val manifest = implicitly[ClassTag[Double]]
    val defaultArrayValue = implicitly[DefaultArrayValue[Double]]

    override def close(a: Double, b: Double, tolerance: Double) = (a-b).abs <= math.max(a.abs, b.abs) * tolerance
  }
}

