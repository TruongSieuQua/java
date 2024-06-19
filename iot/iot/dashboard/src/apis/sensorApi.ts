import { SensorData } from "@/models";

const API_URL = `${process.env.SENSOR_API || "http://localhost:8082"}/sensors`;

export const getSensor = async(id: number) => {
  try{
    const response = await fetch(`${API_URL}/${id}`, {
      method: "GET"
    });
    if(response.ok){
      return {
        success: true,
        data: await response.json() as SensorData
      }
    }else{
      return {
        success: false,
        error: await response.json()
      }
    }
  }catch(e: any){
    console.log(e);
    return {
      success: false,
      error: {message: e.message}
    }
  }
};

export const updateSensor = async(sensorData: SensorData) => {
  try{
    const response = await fetch(`${API_URL}/${sensorData.id}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(sensorData),
    })
    if(response.ok){
      return {
        success: true,
        data: await response.json()
      };
    }else{
      return {
        success: false,
        error: await response.json()
      }
    }
  }catch(e: any){
    console.log(e);
    return {
      success: false,
      error: {message: e.message}
    }
  }
}

export const sensorStream = (consumer: (sensorData: SensorData)=>void) => {
  const eventSource = new EventSource(`${API_URL}/stream`);
  eventSource.onmessage = (event: MessageEvent) => {
    const sensorData = JSON.parse(event.data);
    consumer(sensorData);
    // const second = Math.floor(sensorData.timestamp / 1000);
    // const hh_mm_ss = new Date(sensorData.timestamp + 7 * 3600 * 1000).toLocaleTimeString();
    // const forestName = sensorData.forestName;
    // const temperature = sensorData.temperature;

    // setChartData((prevChartData) => {
    //   const len = prevChartData.length;
    //   if (len === 0 || prevChartData[len - 1].second < second) {
    //     return [...prevChartData, { second, label: hh_mm_ss, [forestName]: temperature }];
    //   } else {
    //     const last = { ...prevChartData[len - 1], [forestName]: temperature };
    //     return [...prevChartData.slice(0, len - 1), last].slice(-10);
    //   }
    // });
  };
  eventSource.onerror = (e) => {
    eventSource.close();
  };

  return eventSource;
};
