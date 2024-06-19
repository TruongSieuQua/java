
"use client"
import { SensorButton } from "./sensor-button";

export function ControlBoard(){
  return <div className="flex gap-4 justify-center">
   <SensorButton sensorId={1} />
   <SensorButton sensorId={2} />
   <SensorButton sensorId={3} />
    <div className="w-6"> _ </div>
  </div>
}
