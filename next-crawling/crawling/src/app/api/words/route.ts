import {
  client,
  connectToDatabase,
  closeDatabaseConnection,
} from "@/lib/client";
import { NextRequest } from "next/server";

function delay(time:number) {
  return new Promise(resolve => setTimeout(resolve, time));
}

export const POST = async (
  request: NextRequest
) => {
  const data = await request.json();
  const { begin, end } = data;
  if (
    !begin ||
    !end ||
    isNaN(Number(begin)) ||
    isNaN(Number(end))
  ) {
    return Response.json({ message: "Invalid input" }, { status: 400 });
  }
  let start = Number(begin);
  let stop = Number(end) + 1;
  if(stop < start){
    return Response.json({ message: "Invalid range" }, { status: 400 });
  }
  let wordsCollection;
  try {
    await connectToDatabase();
    wordsCollection = client.db("English").collection("words");
  } catch (err) {
    return Response.json({ message: "Database connection failed" }, { status: 500 });
  }
  const failedIds: number[] = [];
  let i: number;
  
  for (i = start; i < stop; i++) {
    await fetch("https://evldapi.eduhome.com.vn/wordDetail?id=" + i, {
      method: "GET",
      headers: {
        "Sec-Ch-Ua": '"Chromium";v="123", "Not:A-Brand";v="8"',
        "Sec-Ch-Ua-Mobile": "?0",
        Authorization:
          "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IlRpbmggRGVwIFRyYWkgbmhhdCB0aGUgZ2lvaSIsImlhdCI6MTUxNjIzOTAyMn0",
        // "User-Agent":
        //   "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.6312.122 Safari/537.36",
        "Sec-Ch-Ua-Platform": '"Linux"',
        Accept: "*/*",
        Origin: "https://evld.eduhome.com.vn",
        "Sec-Fetch-Site": "same-site",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Dest": "empty",
        Referer: "https://evld.eduhome.com.vn/",
        "Accept-Encoding": "gzip, deflate, br",
        "Accept-Language": "en-US,en;q=0.9",
        Priority: "u=1, i",
      },
    })
      .then((response) => {
        console.log("id " + i + " fetched");
        if (!response.ok) {
          failedIds.push(i);
        }
        return response.json();
      })
      .then(async(data) => {
        data = data[0] || data;
        
        await wordsCollection.insertMany([{
          ...data,
          _id: i,
          content: JSON.parse(data.content),
        }]);
        console.log("id " + i + " inserted");
      })
      .catch((error) => {
        failedIds.push(i);
        console.error("There was a problem with the fetch operation:", error);
      });
      await delay(80);
  }
  await closeDatabaseConnection();
  return Response.json({ failedIds }, {status: 200});
};
