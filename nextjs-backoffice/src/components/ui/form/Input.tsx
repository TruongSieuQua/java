"use client";

import { forwardRef } from "react";
import { extractTvProps } from "@/utils";
import { twMerge } from "tailwind-merge";
import { VariantProps, tv } from "tailwind-variants";

const inputVariants = tv({
  base: "block w-full border-[1px] focus-visible:outline-none \
	hover:border-hover \
	focus:border-focus \
	disabled:bg-disabled",
  variants: {
    size: {
      xs: "px-3 text-xs leading-7 rounded-sm",
			sm: "px-3 text-sm leading-8 rounded-sm",
      md: "px-5 text-base leading-9 rounded-md",
      lg: "px-5 text-lg leading-11 rounded-lg",
    },
		color: {
			default: "bg-default border-default",
			success: "bg-success border-success",
			info: "bg-info border-info",
			warning: "bg-warning border-warning",
			error: "bg-error border-error",
		}
  },
	defaultVariants: {
		size: "md",
		color: "default",
	}
});
type InputVariantsType = VariantProps<typeof inputVariants>;
const inputVariantKeys = ["size", "color"];

interface InputProps
  extends InputVariantsType,
    Omit<
      React.InputHTMLAttributes<HTMLInputElement>,
      keyof InputVariantsType
    > {}

export const Input = forwardRef((props: InputProps, ref) => {
  const { tvProps, className, ...rest } = extractTvProps<
    InputProps,
    InputVariantsType
  >(props, ...inputVariantKeys);

  return (
    <input className={inputVariants({ ...tvProps, className })} {...rest} />
  );
})
