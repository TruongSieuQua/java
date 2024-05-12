import { Output, array, date, number, object, string } from "valibot";
import SM from "./sm15-lib";

const SMItemSchema = object({
  value: string(), // id
  repetition: number(),
  lapse: number(),
  of: number(),
  optimumInterval: number(),
  dueDate: date(),
  previousDate: date(),
  _afs: array(number()),
});
export type SMItem = Output<typeof SMItemSchema>;

if (SM === undefined) {
  throw new Error("SM is not defined");
}

let sm = new SM();

export function add(value: string) {
	return sm.addItem(value);
}

// export function nextAdv(){
// 	data = sm.nextItem(mode[1] === '_adv');
// }

export function answer(score: number, item: SMItem) {
  if (0 <= score && score <= 5) {
    sm.answer(score, item);
  }else{
		throw new Error("Score must be between 0 and 5");
	}
}

export function save(callback: (items: SMItem[])=>void, items: SMItem[]) {
	callback(items);
}

export function load(callback: (itemIds: string[])=> SMItem[], ids: string[]) {
  const items: SMItem[] = callback(ids);
  if(SM === undefined){
		throw new Error("SM is not defined");
	}
	sm = SM.load(items);
}

export function list():SMItem[] {
  var _i, _len, _ref, _results;
  _ref = sm.q;
  _results = [];
  for (_i = 0, _len = _ref.length; _i < _len; _i++) {
    const item = _ref[_i];
    _results.push(item);
  }
  return _results;
}

