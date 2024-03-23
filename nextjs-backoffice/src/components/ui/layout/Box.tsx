import { forwardRef } from "react";
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
type boxVariantsType = VariantProps<typeof boxVariants>
const boxVariants = tv(boxVariantsConfig);
const boxVariantsKey = ["display"].concat(
  baseVariantKeys,
);

export interface BoxProps
  extends boxVariantsType,
    React.HTMLAttributes<HTMLDivElement> {}

export const Box = forwardRef(( props : BoxProps, ref) => {
  const {tvProps, className, children, ...rest } = extractTvProps<BoxProps, boxVariantsType>(props, ...boxVariantsKey);

  return (
    <div
      className={boxVariants({ ...tvProps, className })}
      {...rest}
    >
      {children}
    </div>
  );
});
