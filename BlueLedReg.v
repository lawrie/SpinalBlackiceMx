// Generator : SpinalHDL v1.3.5    git head : f0505d24810c8661a24530409359554b7cfa271a
// Date      : 06/11/2019, 17:47:48
// Component : BlueLedReg


module BlueLedReg (
      output reg  io_blueLed,
      input   clk);
  always @ (posedge clk) begin
    io_blueLed <= (! 1'b1);
  end

endmodule

