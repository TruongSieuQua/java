"use client";
import { MuiLineChart } from "./line-chart";
import { Sensor } from "./sensor";

export function ControlBoard() {
  return (
    <div className="flex gap-6">
      <div className="flex-grow">
        <MuiLineChart />
      </div>
      <div className="flex flex-col gap-4 mt-16">
        <Sensor sensorId={1} />
        <Sensor sensorId={2} />
        <Sensor sensorId={3} />
      </div>
    </div>
  );
}
