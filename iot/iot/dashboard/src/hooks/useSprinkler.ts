"use client";
import {getAllSprinklers,updateSprinkler, sprinklerStream} from "@/apis";
import { SprinklerData } from "@/models";
import { useCallback, useEffect, useState } from "react";

type SprinklersData = {
  [key: number]: SprinklerData;
};

export const useSprinkler = (initial?: SprinklersData) => {
  const [actuatorsData, setActuatorsData] = useState<SprinklersData>(initial||{});

  const setSrpinklerData = (sprinklerData: SprinklerData) => {
    setActuatorsData((prevActuatorsData) => {
      return { ...prevActuatorsData, [sprinklerData.id]: sprinklerData };
    });
  }

  const handleSprinklerStream = useCallback((sprinklerData: SprinklerData) => {
    setActuatorsData((prevActuatorsData) => {
      return { ...prevActuatorsData, [sprinklerData.id]: sprinklerData };
    });
  }, []);

  const getAll = async() => {
    const response = await getAllSprinklers();
    if(response.success){
      const data = response.data as SprinklerData[];
      setActuatorsData(data.reduce((acc, sprinklerData) => {
        acc[sprinklerData.id] = sprinklerData;
        return acc;
      }, {} as SprinklersData));
    }else{
      throw new Error(response.error.message);
    }
  }

  const update = async (sprinklerData: SprinklerData) => {
    const response = await updateSprinkler(sprinklerData);
    if(response.success){
      setSrpinklerData(sprinklerData);
    }
  };

  useEffect(() => {
    const eventSource = sprinklerStream(handleSprinklerStream);
    return () => {
      eventSource.close();
    };
  }, []);

  return {
    actuatorsData,
    getAll,
    update
  };
}
