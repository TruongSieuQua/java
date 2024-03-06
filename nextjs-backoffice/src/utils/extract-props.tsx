export function extractClassFromProps<
  P extends { className?: string, [key: string]: any }
>(
  props: P,
  ...keys: string[]
) {
  let { className, ...rest } = props;
  const classes = Object.keys(rest).reduce((acc, key) => {
    console.log(key);
		if(keys.includes(key)) {
			const value = rest[key];
			if (typeof value === 'string') {
				acc.push(value);
			}
			if (typeof value === 'boolean') {
				if (value) {
					acc.push(key);
				}
			}
			delete rest[key];
		}
		return acc;
  }, [] as string[]);

	console.log(classes);
  className = Array.from(new Set([...classes, className])).join(' ');
  return { className, ...rest };
}
