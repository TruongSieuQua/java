export function extractTvProps<
  P extends { [key: string]: any }
>(
  props: P,
  ...keys: string[]
) {
  const rest: Partial<P> = {};
  const tvProps: Partial<P> = {};
  for (const key in props) {
    if (keys.includes(key as string)) {
      tvProps[key] = props[key];
    } else {
      rest[key] = props[key];
    }
  }
  return { tvProps, ...rest };
}
