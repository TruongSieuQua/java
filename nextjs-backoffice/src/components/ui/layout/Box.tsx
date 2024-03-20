import { VariantProps, tv } from "tailwind-variants";
import { baseVariants, baseVariantKeys } from "./Base";
import { extractTvProps } from "@/utils";

const boxVariantsConfig = {
  extend: baseVariants,
  variants: {
    display: {
      block: "block",
      inline: "inline",
      inlineBlock: "inline-block",
      flex: "flex",
      inlineFlex: "inline-flex",
      grid: "grid",
      inlineGrid: "inline-grid",
    },
  },
};
const boxVariants = tv(boxVariantsConfig);
type boxVariantsType = VariantProps<typeof boxVariants>
const boxVariantsKey = Object.keys(boxVariantsConfig.variants).concat(
  baseVariantKeys,
);

export interface BoxProps
  extends boxVariantsType,
    React.HTMLAttributes<HTMLDivElement> {}

export function Box( props : BoxProps) {
  const {tvProps, className, children, ...rest } = extractTvProps<BoxProps, boxVariantsType>(props, ...boxVariantsKey);

  return (
    <div
      className={boxVariants({ ...tvProps, className })}
      {...rest}
    >
      {children}
    </div>
  );
}
