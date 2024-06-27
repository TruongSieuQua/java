import { useCallback, useEffect, useState } from "react";
import { notify } from "./toasify";
import { Button } from "./button";
import clsx from "clsx";
import { useSensor } from "@/hooks";
import { fetchSensor, updateSensor } from "@/apis";
import { SensorData } from "@/models";
interface SensorProps {
  sensorId: number;
}

export function Sensor({ sensorId }: SensorProps) {
  const [sensor, setSensor] = useState<SensorData>({ id: 0, state: false });
  const { chartData } = useSensor();

  const getSensorState = useCallback(async (id: number) => {
    const response = await fetchSensor(id);
    if (response.success) {
      if (response.data?.state === undefined) {
        notify("Sensor Response missing state");
        return;
      }
      setSensor(response.data);
    } else {
      notify(response.error.message);
    }
  }, []);

  const updateSensorState = useCallback((sensorId: number, state: boolean) => {
    updateSensor({ id: sensorId, state }).then((response) => {
      if (response.success) {
        setSensor((prevSensor) => ({ ...prevSensor, state }));
      } else {
        notify(response.error.message);
      }
    });
  }, []);

  useEffect(() => {
    getSensorState(sensorId);
  }, []);

  useEffect(() => {
    if(sensor.forestName && chartData &&
      chartData.at(-1)){
        let temp = chartData[chartData.length - 1][sensor.forestName];
        if(temp && !Number.isNaN(temp)){
          console.log(temp);
          setSensor((prevSensor) => ({ ...prevSensor, temperature: Number(temp)}));
        }
    }
  }, [chartData])

  return (
    <div
      className={clsx("w-40 p-2 border-2 border-solid", {
        "border-green-500": sensor.state,
        "border-red-400": !sensor.state,
      })}
    >
      <div>
        <h3>Id: {sensorId}</h3>
      </div>
      <div className="mb-1">
        <div>
          <h4>State: {sensor.state ? "ON" : "OFF"}</h4>
          <h4>Forest: {sensor.forestName}</h4>
          <h4>
            Temp: {sensor.temperature?.toFixed(2) || "?"} °C
          </h4>
        </div>
      </div>
      <Button
        className="w-full"
        isOn={sensor.state}
        onClick={() => {
          updateSensorState(sensorId, !sensor.state);
        }}
      >
        Sensor {sensorId}
      </Button>
    </div>
  );
}
