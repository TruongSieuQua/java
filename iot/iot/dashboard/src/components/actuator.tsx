"use client";
import React, { useState, useEffect } from "react";
import { Button } from "./button";
import { notify } from "./toasify";
import { useSprinkler } from "@/hooks";
import clsx from "clsx";

type ActuatorData = {
  id: number;
  forestName: string;
  state: boolean;
};

export function Actuator() {
  const { actuatorsData, getAll, update } = useSprinkler();

  return (
    <div className="">
      <div className="flex justify-center items-center gap-4">
        <h1 className="text-2xl font-bold text-center">Actuator</h1>
        <Button onClick={getAll}>Refresh</Button>
      </div>
      <div className="mb-6"></div>
      <div className="flex justify-center gap-6">
        {Object.entries(actuatorsData).map(([id, sprinklerData]) => (
          <div key={id} className={clsx("flex flex-col w-64 h-80 border-2 border-solid p-4", {
            "border-green-500": sprinklerData.state,
            "border-blue-200": !sprinklerData.state
          })}>
          <div key={id} className="flex-grow flex flex-col justify-center items-center gap-4">
            <span className="text-xl font-bold">
              Sprinkler {sprinklerData.id}
            </span>

            <div
              className={clsx("rounded-full w-24 h-24 flex justify-center items-center", {
                "bg-gradient-to-t from-[#08AEEA] to-[#2AF598]":
                  sprinklerData.state,
                "bg-gradient-to-t from-[#F12711] to-[#F5AF19]":
                  !sprinklerData.state,
              })}
            >
              <div className="text-xl font-semibold">
                {sprinklerData.state ? "ON" : "OFF"}
              </div>
            </div>
          </div>
          <Button
          onClick={() =>
            update({ id: sprinklerData.id, state: !sprinklerData.state })
          }
        >
          Turn {sprinklerData.state ? "OFF" : "ON"}
        </Button>
        </div>
        ))}
      </div>
    </div>
  );
}
