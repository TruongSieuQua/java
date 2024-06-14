
"use client"

import { useState } from "react";
import { Button } from "./button";
import { notify } from "./toasify";

export function ControlBoard(){
  const [controlState, setControlState] = useState({
    sensor1: false,
    sensor2: false,
    sensor3: false,
    actuator1: false,
    actuator2: false,
    actuator3: false,
  })
  const updateSensorState = (sensorId: number, state: boolean) => {
    console.log(`http://localhost:8082/sensors/${sensorId}`, state)
    fetch(`http://localhost:8082/sensors/${sensorId}`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ state }),
    })
    .then((res) => res.status == 200 ? res.json() : null)
    .then((sensor) => {
      if(sensor){
        setControlState({
          ...controlState,
          [`sensor${sensorId}`]: sensor.state,
        })
        notify(`Sensor ${sensorId} is now ${sensor.state ? 'on' : 'off'}`)
      }else{
        notify("Error updating sensor state")
      }
    }).catch((e) => {
      notify(e.message);
    })
  }

  const updateActuatorState = (actuatorId: number, state: boolean) => {
    console.log(`http://localhost:8082/sprinklers/${actuatorId}`, state)
    fetch(`http://localhost:8082/sprinklers/${actuatorId}`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ state }),
    }).then((res) => res.status == 200 ? res.json() : null)
    .then(data => {
      if(data){
        setControlState({
          ...controlState,
          [`actuator${actuatorId}`]: data.state,
        })
        notify(`Actuator ${actuatorId} is now ${data.state ? 'on' : 'off'}`)
      }else{
        notify("Error updating actuator state")
      }
    })
    .catch((e) => {
      notify(e.message);
    })
  }

  return <div className="flex gap-4 justify-center">
    <Button onClick={()=> {updateSensorState(1, !controlState.sensor1)}}>Sensor 1</Button>
    <Button onClick={()=> {updateSensorState(2, !controlState.sensor2)}}>Sensor 2</Button>
    <Button onClick={()=> {updateSensorState(3, !controlState.sensor3)}}>Sensor 3</Button>
    <div className="w-6"> _ </div>
    <Button onClick={()=> {updateActuatorState(1, !controlState.actuator1)}}>Actuator 1</Button>
    <Button onClick={()=> {updateActuatorState(2, !controlState.actuator2)}}>Actuator 2</Button>
    <Button onClick={()=> {updateActuatorState(3, !controlState.actuator3)}}>Actuator 3</Button>
  </div>
}
