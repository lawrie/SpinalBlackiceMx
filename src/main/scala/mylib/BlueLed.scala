package mylib

import spinal.core._

class BlueLed extends Component {
  val io = new Bundle {
    val blueLed = out Bool
  }

  io.blueLed := ~True // LED is on when low
}

object BlueLed {
  def main(args: Array[String]) {
    BlackIceSpinalConfig.generateVerilog(new BlueLed)
  }
}

