import {add, answer, load, save, list, SMItem} from "@/app/[lng]/test/sm15";

const saved = new Set();

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
