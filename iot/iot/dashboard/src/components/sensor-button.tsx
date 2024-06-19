import { useCallback, useEffect, useState } from "react";
import { notify } from "./toasify";
import { Button } from "./button";
import clsx from "clsx";
import { useSensor } from "@/hooks";
interface SensorProps{
  sensorId: number;
}

export function SensorButton({sensorId}: SensorProps){
  const [sensorState, setSensorState] = useState<boolean>(false);
  const {getSensor, updateSensor} = useSensor();

  const getSensorState = useCallback(async(id: number) => {
    const response = await getSensor(id);
    if(response.success){
      if(response.data?.state===undefined){
        notify("Sensor Response missing state");
        return;
      }
      setSensorState(response.data.state);
    }else{
      notify(response.error.message);
    }
  }, []);

  const updateSensorState = useCallback((sensorId: number, state: boolean) => {
    updateSensor({id: sensorId, state}).then((response) => {
      if(response.success){
        setSensorState(state);
      }else{
        notify(response.error.message);
      }
    });
  }, []);

  useEffect(() => {
    getSensorState(sensorId);
  }, []);

  return  <Button isOn={sensorState}
  onClick={()=> {updateSensorState(sensorId, !sensorState)}}>Sensor {sensorId}</Button>
}
