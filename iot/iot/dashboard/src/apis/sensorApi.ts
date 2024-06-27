import { SensorData } from "@/models";

const API_URL = `${process.env.SENSOR_API || "http://localhost:8082"}/sensors`;

export const fetchSensor = async(id: number) => {
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
  };
  eventSource.onerror = (e) => {
    eventSource.close();
  };

  return eventSource;
};
