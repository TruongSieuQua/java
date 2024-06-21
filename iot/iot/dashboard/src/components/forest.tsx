"use client";
import { useForest } from "@/hooks/useForest";
import { Button } from "./button";

export function Forest() {
  const { forestData, loading, updateForest } = useForest();

  return (
    <div>
      <h1 className="text-2xl font-bold text-center">Forest</h1>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <div className="flex gap-4 w-60 h-80 p-4">
          {forestData.map((forest) => (
            <div key={forest.name} className="border-solid border-2 border-blue-400">
              <div className="flex flex-row gap-2">
                <p className="text-lg font-semibold text-center">
                  {forest.name}
                </p>
                <p className="text-lg font-semibold text-center">
                  {forest.state}
                </p>
              </div>
              <div className="flex justify-between">
                <Button
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
