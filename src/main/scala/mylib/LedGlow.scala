package mylib

import spinal.core._

class LedGlow extends Component {
  val io = new Bundle {
    val blueLed = out Bool
  }

  val cnt = Reg(UInt(26 bits))
  val pwm = Reg(UInt(5 bits))

  cnt := cnt + 1

  val pwmInput = UInt(4 bits)

  when (cnt(25)) {
    pwmInput := cnt(24 downto 21)
  } otherwise {
    pwmInput := ~cnt(24 downto 21)
  }

  pwm := pwm(3 downto 0).resize(5) + pwmInput.resize(5)

  io.blueLed := pwm(4)  
}

object LedGlow {
  def main(args: Array[String]) {
    BlackIceSpinalConfig.generateVerilog(new LedGlow)
  }
}

