package mylib

import spinal.core._

class BlueLedReg extends Component {
  val io = new Bundle {
    val blueLed = out(Reg(Bool))
  }

  io.blueLed := ~True // LED is on when low
}

object BlueLedReg {
  def main(args: Array[String]) {
    BlackIceSpinalConfig.generateVerilog(new BlueLedReg)
  }
}

