// export default async function Words({
//   searchParams,
// }: {
//   searchParams?: { [key: string]: string | string[] | undefined };
// }) {
//   const url = process.env.server_url;
//   if (!url) {
//     return <div>Env server.url is null</div>;
//   }
//   const begin = searchParams?.begin as string;
//   const end = searchParams?.end as string;

//   const response = await fetch(url + "/api/words", {
//     method: "POST",
//     headers: {
//       "Content-Type": "application/json",
//     },
//     body: JSON.stringify({ begin, end }),
//   });
//   if (response.status !== 200) {
//     return <div>Failed to fetch: {response.statusText}</div>;
//   }

//   const data = await response.json();

//   return (
//     <div>
//       <h1>Words</h1>
//       <p>Begin: {begin}</p>
//       <p>End: {end}</p>
//       <p>Failed ids: {data?.failedIds}</p>
//     </div>
//   );
// }
"use client";

import { useState } from "react";

export default function Words() {
  const [data, setData] = useState([]);

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const begin = (event.currentTarget.elements.namedItem('begin') as HTMLInputElement).value;
    const end = (event.currentTarget.elements.namedItem('end') as HTMLInputElement).value;
    

    const response = await fetch("http://localhost:3000/api/words", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ begin, end }),
  });
  if (response.status !== 200) {
    console.log('Failed to fetch:', response.statusText);
    return;}
    const body = await response.json();
    setData(body.failedIds);
  }

  return (<div>
    <form className="flex flex-col gap-2 w-96" onSubmit={handleSubmit}>
    <div className="form-control">
    <label className="label">
            <span className="label-text">Begin</span>
          </label>
      <input type="text" id="begin" name="begin" className="input input-bordered" />
      </div>
      <div className="form-control">
      <label className="label">
            <span className="label-text">End</span>
          </label>
      <input type="text" id="end" name="end" className="input input-bordered"/>
      </div>
      <button type="submit" className="btn btn-primary">Submit</button>
    </form>
    <div className="my-4"></div>
    <div className="text-xl font-bold">Failed ids: {data.join(', ')}</div>
    </div>
  );
}
