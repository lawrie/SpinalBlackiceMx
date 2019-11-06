package mylib

import spinal.core._

//Define a custom SpinalHDL configuration with boot reset instead of the default asynchronous one. This configuration can be resued everywhere
object BlackIceSpinalConfig extends SpinalConfig(defaultConfigForClockDomains = ClockDomainConfig(resetKind = BOOT))

