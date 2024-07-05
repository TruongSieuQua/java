"use client";
import { useForest } from "@/hooks/useForest";
import { Button } from "./button";

export function Forest() {
  const { forestData, loading, updateForest } = useForest();

  return (
    <div className="">
      <h1 className="text-2xl font-bold text-center">Forest</h1>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <div className="flex justify-center gap-4 p-4">
          {forestData.map((forest) => (
            <div key={forest.name} className="border-solid border-2 border-blue-400 p-4">
              <div className="flex flex-col gap-2">
                <p className="text-lg font-semibold">
                  Name: {forest.name}
                </p>
                <p className="text-lg font-semibold">
                  State: {forest.state}
                </p>
              </div>
              <div className="flex justify-between">
                <Button
                className="w-full"
                  onClick={() =>
                    updateForest({ name: forest.name, state: "fired" })
                  }
                >
                  Fire
                </Button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
