import { ForestData } from "@/models";

const API_URL = `${process.env.FOREST_API || "http://localhost:8080"}/forests`;

export const getAll = async() => {
  try{
    const response = await fetch(API_URL, {
      method: "GET",

    });
    if(response.ok){
      console.log(response);
      return {
        success: true,
        data: await response.json() as ForestData[]
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
}

export const update = async({name, state}: {name: string, state:string}) => {
  try{
    const response = await fetch(`${API_URL}/${name}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({state}),
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

export const forestStream = (consumer: (forest: ForestData)=>void) => {
  const eventSource = new EventSource(`${API_URL}/stream`);
  eventSource.onmessage = (event: MessageEvent) => {
    const forest = JSON.parse(event.data);
    consumer(forest);
  };
  eventSource.onerror = (e) => {
    eventSource.close();
  };

  return eventSource;
};
