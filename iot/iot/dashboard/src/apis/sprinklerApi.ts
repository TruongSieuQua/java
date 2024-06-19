import { SprinklerData } from "@/models";

const API_URL = `${process.env.APP_API || "http://localhost:8082"}/sprinklers`;

export const getAllSprinklers = async () => {
  try {
    const response = await fetch(`${API_URL}/all`, {
      method: "GET",
    });
    if (response.ok) {
      return {
        success: true,
        data: await response.json() as SprinklerData[],
      };
    } else {
      return {
        success: false,
        error: await response.json(),
      };
    }
  } catch (e: any) {
    console.log(e);
    return {
      success: false,
      error: { message: e.message },
    };
  }
};

export const updateSprinkler = async (sprinklerData: SprinklerData) => {
  try {
    const response = await fetch(`${API_URL}/${sprinklerData.id}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(sprinklerData),
    });
    if (response.ok) {
      return {
        success: true,
        data: response.json(),
      };
    } else {
      return {
        success: false,
        error: response.json(),
      };
    }
  } catch (e: any) {
    console.log(e);
    return {
      success: false,
      error: { message: e.message },
    };
  }
}

export const sprinklerStream = (consumer:(sprinklerData: SprinklerData)=> void) => {
  const eventSource = new EventSource(`${API_URL}/stream`);
  eventSource.onmessage = (event: MessageEvent) => {
    const actuatorData: SprinklerData = JSON.parse(event.data);
    consumer(actuatorData);
    // setActuatorsData((prevActuatorsData) => {
    //   console.log(actuatorData);
    //   return { ...prevActuatorsData, [actuatorData.id]: actuatorData };
    // });
  };
  eventSource.onerror = (e:any) => {
    eventSource.close();
    return {
      success: false,
      error: { message: e.message },
    };
  };
  return eventSource;
};
