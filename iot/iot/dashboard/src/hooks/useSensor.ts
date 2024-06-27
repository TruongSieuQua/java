"use client";
import { useCallback, useEffect, useState } from "react";
import {fetchSensor, updateSensor as update, sensorStream} from "@/apis";
import { SensorData } from "@/models";

export type DataSet = {
  second: number;
  label: string;
  [key: string]: number | string;
};

export function useSensor(){
  const [chartData, setChartData] = useState<DataSet[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [info, setInfo] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);

  const getSensor = useCallback(async(id: number) => {
    const response = await fetchSensor(id);
    if(response.success){
      return await response.data as SensorData;
    }else{
      setError(response.error.message);
    }
  }, []);

  const updateSensor = useCallback((sensorId: number, state: boolean) => {
    update({id: sensorId, state}).then((response) => {
      if(response.success){
        setInfo("Sensor updated successfully");
      }else {
        setError(response.error.message);
      }
    });
  }, []);
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

  return {chartData, getSensor, update, updateSensor, error, info};
}
