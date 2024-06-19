"use client";
import { useCallback, useEffect, useState } from "react";
import {getSensor, updateSensor, sensorStream} from "@/apis";
import { SensorData } from "@/models";

export type DataSet = {
  second: number;
  label: string;
  [key: string]: number | string;
};

export function useSensor(){
  const [chartData, setChartData] = useState<DataSet[]>([]);

  const handleSensorStream = useCallback((sensorData:SensorData) => {
    const {timestamp, forestName, temperature} = sensorData;
    if(timestamp === undefined || forestName === undefined || temperature === undefined){
      console.error("Sensor data missing timestamp, forestName or temperature");
      return;
    }
    const second = Math.floor(timestamp / 1000);
      const hh_mm_ss = new Date(timestamp + 7 * 3600 * 1000).toLocaleTimeString();

      setChartData((prevChartData) => {
        const len = prevChartData.length;
        if (len === 0 || prevChartData[len - 1].second < second) {
          return [...prevChartData, { second, label: hh_mm_ss, [forestName]: temperature }];
        } else {
          const last = { ...prevChartData[len - 1], [forestName]: temperature };
          return [...prevChartData.slice(0, len - 1), last].slice(-10);
        }
      });
  }, []);

  useEffect(() => {
    const eventSource = sensorStream(handleSensorStream);
    return () => {
      eventSource.close();
    };
  }, []);

  return {chartData, getSensor, updateSensor};
}
