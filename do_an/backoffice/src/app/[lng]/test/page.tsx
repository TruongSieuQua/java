"use client";
import {
  Select
} from "@/components/ui/select";
import {add, answer, load, save, list, SMItem} from "@/app/[lng]/test/sm15";
import Script from 'next/script'
import { useEffect } from "react";

const options = [
  { value: 'chocolate', label: 'Chocolate' },
  { value: 'strawberry', label: 'Strawberry' },
  { value: 'vanilla', label: 'Vanilla' }
]

export default function Page() {

	useEffect(() => {
    const saved: Set<SMItem> = new Set();

		const a = add('chocolate');
		const b = add('strawberry');
		const c = add('vanilla');

		const items = list();

		answer(4, items[0]);
		answer(2, items[1]);
		answer(5, items[2]);

		answer(5, items[0]);
		answer(2, items[1]);
		answer(1, items[2]);

		answer(3, items[0]);
		answer(3, items[1]);
		answer(3, items[2]);

		answer(5, items[0]);
		answer(2, items[1]);
		answer(4, items[2]);

		const items2 = list();
		console.log(items2);

		// save((items2)=>{
		// 	console.log("Saving")
		// 	saved.push(...items2);
		// }, items2);

		// const items3 = load((ids)=>{
		// 	console.log("Loading");
		// 	const rs: SMItem[] = [];
		// 	for(const id of ids){
		// 		rs.push(saved[Number(id)]);
		// 	}
		// 	return rs;
		// }, ['0', '2'])

		// console.log(items3);
  }, []);

	return (
    <div className="flex justify-center items-center h-screen">
      <div className="w-96 h-auto">
        <div>
				<div id="asd"></div>
        </div>
      </div>
			<Script src="./script.js" strategy="afterInteractive"/>
    </div>
  );
}
