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
import { useSensor } from "@/hooks";
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
  const {chartData} = useSensor();

  return (
    <div>
      <div className="my-4 flex items-center gap-4 justify-center">
      <h1 className=" text-2xl font-bold text-center">Temperature Chart</h1>
      {/* <Button onClick={tryAgain}>Try Again</Button> */}
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
      width={1280}
      height={820}
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
      <Line type="monotone" dataKey="f1" stroke="#8884d8" fill="#8884d8" activeDot={{ r: 8 }}  isAnimationActive={false} connectNulls={true}/>
      <Line type="monotone" dataKey="f2" stroke="#82ca9d" fill="#82ca9d" activeDot={{ r: 8 }}  isAnimationActive={false} connectNulls={true}/>
      <Line type="monotone" dataKey="f3" stroke="#ffc658" fill="#ffc658" activeDot={{ r: 8 }}  isAnimationActive={false} connectNulls={true}/>
    </LineChart>
  );
};
