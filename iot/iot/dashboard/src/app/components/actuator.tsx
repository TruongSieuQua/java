"use client";
import React, { useState, useEffect } from "react";
import { Button } from "./button";
import { notify } from "./toasify";

type ActuatorData = {
  id: number;
  forestName: string;
  state: boolean;
};

type ActuatorsData = {
  [key: number]: ActuatorData;
};

export function Actuator() {
  const [actuatorsData, setActuatorsData] = useState<ActuatorsData>({});

  const actuatorStream = () => {
    const eventSource = new EventSource("http://localhost:8081/sprinklers");
    eventSource.onmessage = (event: MessageEvent) => {
      const actuatorData: ActuatorData = JSON.parse(event.data);
      setActuatorsData((prevActuatorsData) => {
        console.log(actuatorData);
        return { ...prevActuatorsData, [actuatorData.id]: actuatorData };
      });
    };
    eventSource.onerror = () => {
      notify("Error fetching actuator stream! Try again.");
      eventSource.close();
    };
    return eventSource;
  };

  const tryAgain = () => {
    actuatorStream();
  }

  useEffect(() => {
    const eventSource = actuatorStream();
    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <div className="h-80">
      <div className="flex items-center gap-4">
        <h1 className="text-2xl font-bold text-center">Actuator</h1>
        <Button onClick={tryAgain}>Try Again</Button>
      </div>
      <div className="flex gap-6">
        {Object.entries(actuatorsData).map(([id, actuatorData]) => (
          <div key={id} className="flex flex-col items-center">
            <div className="text-xl font-bold">{actuatorData.forestName}</div>
            <div className="text-xl font-bold">{actuatorData.state ? "ON" : "OFF"}</div>
          </div>
        ))}
      </div>
    </div>
  );
}
