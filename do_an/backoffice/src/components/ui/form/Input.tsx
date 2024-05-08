"use client";

import React, { forwardRef } from "react";
import { extractTvProps } from "@/utils";
import { twMerge } from "tailwind-merge";
import { VariantProps, tv } from "tailwind-variants";

const inputVariants = tv({
  base: "input",
  variants: {
    size: {
      xs: "input-xs px-3 leading-7",
			sm: "input-sm leading-8",
      md: "px-5 leading-9",
      lg: "input-lg px-5 leading-11",
    },
		color: {
			none: "",
			default: "input-bordered",
			primary: "input-primary",
			accent: "input-accent",
			success: "input-success",
			info: "input-info",
			warning: "input-warning",
			error: "input-error",
			ghost: "input-ghost",
		},
		width:{
			fit: "w-fit",
			full: "w-full",
		}
  },
	defaultVariants: {
		size: "md",
		color: "default",
		width: "full",
	}
});
type InputVariantsType = VariantProps<typeof inputVariants>;
const inputVariantKeys = ["size", "color"];

interface InputWrapperProps extends React.LabelHTMLAttributes<HTMLLabelElement> {}

interface InputProps
  extends InputVariantsType,
    Omit<
      React.InputHTMLAttributes<HTMLInputElement>,
      keyof InputVariantsType
    > {}

	function InputWrapper({children}:InputWrapperProps){

		return (<label className={inputVariants({ ...tvProps, className })}>{children}</label>)
	}

const Input = forwardRef((props: InputProps, ref) => {
  const { tvProps, className, children, ...rest } = extractTvProps<
    InputProps,
    InputVariantsType
  >(props, ...inputVariantKeys);

	if(children){
		return (<label className={inputVariants({ ...tvProps, className })}>{children}</label>)
	}

  return (
    <input className={inputVariants({ ...tvProps, className })} {...rest} />
  );
})

Input.displayName = "Input";

export { Input };
