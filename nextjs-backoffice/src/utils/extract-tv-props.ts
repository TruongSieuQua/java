// export function extractTvProps<
//   P extends { [key: string]: any }
// >(
//   props: P,
//   ...keys: string[]
// ) {
//   const rest: Omit<P, keys> = {};
// 	const tvProps: Partial<keys> = {};
//   for (const key in props) {
//     if (keys.includes(key as string)) {
//       tvProps[key] = props[key];
//     } else {
//       rest[key] = props[key];
//     }
//   }
//   return { tvProps, ...rest };
// }

function tuple<T extends string[]>(...o: T) {
	return o;
}

export function extractTvProps<P extends { [key: string]: any }, K extends {[key: string]: any}>(
  props: P,
  ...keys: string[]
) {
	const tvProps = {} as any;
	const rest = {} as any;
	for (const key in props) {
		if (keys.includes(key as string)) {
			tvProps[key] = props[key];
		} else {
			rest[key] = props[key];
		}
	}
	return { tvProps, ...rest } as Omit<P, keyof K> & {tvProps: K};
}
