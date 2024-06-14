"use client";

import { useEffect, useState } from "react";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";
import { Button } from "./button";
import { notify } from "./toasify";

type DataSet = {
  second: number;
  label: string;
  [key: string]: number | string;
};

interface SensorData {
  timestamp: number;
  forestName: string;
  temperature: number;
}
interface ChartData {
  second: number;
  label: string;
  [key: string]: any;
}

export const MuiLineChart = () => {
  const [chartData, setChartData] = useState<DataSet[]>([]);

  const temperatureStream = (setChartData: React.Dispatch<React.SetStateAction<ChartData[]>>): EventSource => {
    const eventSource = new EventSource("http://localhost:8082/sensors");

    eventSource.onmessage = (event: MessageEvent) => {
      const sensorData: SensorData = JSON.parse(event.data);
      const second = Math.floor(sensorData.timestamp / 1000);
      const hh_mm_ss = new Date(sensorData.timestamp + 7 * 3600 * 1000).toLocaleTimeString();
      const forestName = sensorData.forestName;
      const temperature = sensorData.temperature;

      setChartData((prevChartData) => {
        const len = prevChartData.length;
        if (len === 0 || prevChartData[len - 1].second < second) {
          return [...prevChartData, { second, label: hh_mm_ss, [forestName]: temperature }];
        } else {
          const last = { ...prevChartData[len - 1], [forestName]: temperature };
          return [...prevChartData.slice(0, len - 1), last].slice(-10);
        }
      });
    };
    eventSource.onerror = (e) => {
      notify("Error fetching temperature stream! Try again.");
      eventSource.close();
    };

    return eventSource;
  };

  const tryAgain = () => {
    temperatureStream(setChartData);
  }

  useEffect(() => {
    const eventSource = temperatureStream(setChartData);
    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <div>
      <div className="my-4 flex items-center gap-4 justify-center">
      <h1 className=" text-2xl font-bold text-center">Temperature Chart</h1>
      <Button onClick={tryAgain}>Try Again</Button>
      </div>
      <ChartComponent data={chartData} />
    </div>
  );
};

interface ChartComponentProps {
  data: ChartData[];
}

const ChartComponent: React.FC<ChartComponentProps> = ({ data }) => {
  return (
    <LineChart
      id="line-chart"
      width={1440}
      height={1080}
      data={data}
      margin={{
        top: 5,
        right: 30,
        left: 20,
        bottom: 5,
      }}
    >
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="label" className="text-sm" />
      <YAxis />
      <Tooltip />
      <Legend />
      <Line type="monotone" dataKey="f1" stroke="#8884d8" fill="#8884d8" activeDot={{ r: 8 }}  isAnimationActive={false}/>
      <Line type="monotone" dataKey="f2" stroke="#82ca9d" fill="#82ca9d" activeDot={{ r: 8 }}  isAnimationActive={false}/>
      <Line type="monotone" dataKey="f3" stroke="#ffc658" fill="#ffc658" activeDot={{ r: 8 }}  isAnimationActive={false}/>
    </LineChart>
  );
};
