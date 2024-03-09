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

export const boxVariants = tv(boxVariantsConfig);
export const boxVariantsKey = Object.keys(boxVariantsConfig.variants).concat(
  baseVariantKeys,
);
interface BoxProps
  extends VariantProps<typeof boxVariants>,
    React.HTMLAttributes<HTMLDivElement> {}

export function Box( props : BoxProps) {
  const {tvProps, className, children, ...rest } = extractTvProps<BoxProps>(props, ...boxVariantsKey);

  return (
    <div
      className={boxVariants({ ...tvProps, className })}
      {...rest}
    >
      {children}
    </div>
  );
}
