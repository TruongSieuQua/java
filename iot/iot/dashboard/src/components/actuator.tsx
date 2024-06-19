"use client";
import React, { useState, useEffect } from "react";
import { Button } from "./button";
import { notify } from "./toasify";
import { useSprinkler } from "@/hooks";

type ActuatorData = {
  id: number;
  forestName: string;
  state: boolean;
};

type ActuatorsData = {
  [key: number]: ActuatorData;
};

export function Actuator() {
  const {actuatorsData, getAll, update} = useSprinkler();

  return (
    <div className="h-80">
      <div className="flex justify-center items-center gap-4">
        <h1 className="text-2xl font-bold text-center">Actuator</h1>
        <Button onClick={getAll}>Refresh</Button>
      </div>
      <div className="mb-6"></div>
      <div className="flex gap-6">
        {Object.entries(actuatorsData).map(([id, sprinklerData]) => (
          <div key={id} className="flex flex-col items-center">
            <div className="text-xl font-bold">{sprinklerData.forestName}</div>
            <div className="text-xl font-bold">{sprinklerData.state ? "ON" : "OFF"}</div>
            <Button onClick={()=> update({id: sprinklerData.id, state: !sprinklerData.state})}>Sprinkler {sprinklerData.id}</Button>
          </div>
        ))}
      </div>
    </div>
  );
}
