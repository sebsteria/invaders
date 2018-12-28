package invaders

import scala.scalajs.js
import scala.scalajs.js.annotation._

object Sounds {
  private def sound(src: String): Howl = new Howl(js.Dictionary("src" -> js.Array(src)))

  val shoot: Howl = sound("sounds/shoot.wav")

  val explosion: Howl = sound("sounds/explosion.wav")
  val invaderKilled: Howl = sound("sounds/invaderkilled.wav")
}

@js.native
@JSGlobal
class Howl(params: js.Dictionary[js.Any]) extends js.Object {
  def play(): Unit = js.native
}