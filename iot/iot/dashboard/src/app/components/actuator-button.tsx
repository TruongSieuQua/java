import { useCallback, useEffect, useState } from "react";
import { notify } from "./toasify";
import { Button } from "./button";
import clsx from "clsx";

interface SensorProps{
  actuatorId: number;
}

export function SensorButton({actuatorId}: SensorProps){
  const [sensorState, setSensorState] = useState<boolean>(false);

  const getSensorState = useCallback((id: number) => {
    fetch(`http://localhost:8082/sprinklers/${id}`, {
      method: "GET"
    }).then((res) => res.status == 200 ? res.json() : null).then((sensor) => {
      if(sensor){
        setSensorState(sensor.state);
      }else{
        notify("Error fetch get sensor request!");
      }
    })
  }, []);

  const updateSensorState = useCallback((sensorId: number, state: boolean) => {
    fetch(`http://localhost:8082/sprinklers/${sensorId}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ state }),
    })
      .then((res) => (res.status == 200 ? res.json() : null))
      .then((sensor) => {
        if (sensor) {
          setSensorState(sensor.state);
          notify(`Sensor ${sensorId} is now ${sensor.state ? "on" : "off"}`);
        } else {
          notify("Error updating sensor");
        }
      })
      .catch((e) => {
        notify(e.message);
      });
  }, []);

  useEffect(() => {
    getActuatorState(actuatorId);
  }, []);

  return  <Button className={clsx("", {
    "bg-blue-400 text-white": sensorState,
    "text-black bg-gray-200": !sensorState,
  })} onClick={()=> {updateSensorState(sensorId, !sensorState)}}>Sensor {sensorId}</Button>
}
