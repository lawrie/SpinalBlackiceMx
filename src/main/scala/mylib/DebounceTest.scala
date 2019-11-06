package mylib

import spinal.core._
import spinal.lib._

class DebounceTest(width:Int = 15) extends Component {
  val io = new Bundle {
    val button1 = in Bool
    val leds = out UInt(3 bits)
  }

  val debounce = new Debounce(width)
  debounce.io.pb := io.button1
  
  val leds = Reg(UInt(3 bits))
  io.leds := ~leds // LEDs on on when low

  when (debounce.io.pbDown) {
    leds := leds + 1
  }
}

class Debounce(width:Int = 15) extends Component {
  val io = new Bundle {
    val pb = in Bool
    val pbState = out(Reg(Bool))
    val pbDown = out Bool
    val pbUp = out Bool
  }

  val pbSync0, pbSync1 = Reg(Bool)
  val pbCnt = Reg(UInt(width bits))
  val pbIdle = (io.pbState === pbSync1)
  val pbCntMax = pbCnt.andR

  pbSync0 := ~io.pb
  pbSync1 := pbSync0

  io.pbDown := (~pbIdle & pbCntMax & ~io.pbState)
  io.pbUp := (~pbIdle & pbCntMax & io.pbState)

  when (pbIdle) {
    pbCnt := 0;
  } otherwise {
    pbCnt := pbCnt + 1
    when (pbCntMax) {
      io.pbState := ~io.pbState
    }
  }
}

object DebounceTest {
  def main(args: Array[String]) {
    BlackIceSpinalConfig.generateVerilog(new DebounceTest(16))
  }
}

