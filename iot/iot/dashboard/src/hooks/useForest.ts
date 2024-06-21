import { forestStream, getAll, update } from "@/apis";
import { ForestData } from "@/models";
import { useCallback, useEffect, useState } from "react";

export function useForest(){
  const [forestData, setForestData] = useState<ForestData[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  const getForest = useCallback(async () => {
    const response = await getAll();
    if(response.success){
      const data = response.data as ForestData[];
      setForestData(data);
    }else{
      throw new Error(response.error.message);
    }
    setLoading(false);
  }, []);

  const updateForest = useCallback(async (forest: ForestData) => {
    const response = await update(forest);
    if(response.success){
      setForestData((prevForestData) => {
        const index = prevForestData.findIndex((f) => f.name === forest.name);
        if(index === -1){
          return [...prevForestData, forest];
        }else{
          return [...prevForestData.slice(0, index), forest, ...prevForestData.slice(index+1)];
        }
      });
    }
  }, []);

  const handleForestStream = useCallback((forest: ForestData) => {
    setForestData((prevForestData) => {
      const index = prevForestData.findIndex((f) => f.name === forest.name);
      if(index === -1){
        return [...prevForestData, forest];
      }else{
        return [...prevForestData.slice(0, index), forest, ...prevForestData.slice(index+1)];
      }
    });
  }, []);

  useEffect(() => {
    getForest();
  }, []);

  useEffect(() => {
    const eventSource = forestStream(handleForestStream);
    return () => {
      eventSource.close();
    };
  }, []);

  return {forestData, loading, updateForest};
}
