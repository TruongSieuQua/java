
"use client"

import { useState } from "react";
import { Button } from "./button";
import { notify } from "./toasify";
import { SensorButton } from "./sensor-button";

export function ControlBoard(){
  return <div className="flex gap-4 justify-center">
   <SensorButton sensorId={1} />
   <SensorButton sensorId={2} />
   <SensorButton sensorId={3} />
    <div className="w-6"> _ </div>
    {/* <Button onClick={()=> {updateActuatorState(1, !controlState.actuator1)}}>Actuator 1</Button>
    <Button onClick={()=> {updateActuatorState(2, !controlState.actuator2)}}>Actuator 2</Button>
    <Button onClick={()=> {updateActuatorState(3, !controlState.actuator3)}}>Actuator 3</Button> */}
  </div>
}
