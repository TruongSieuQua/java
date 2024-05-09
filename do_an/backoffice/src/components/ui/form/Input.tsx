"use client";

import React, { forwardRef } from "react";
import { extractTvProps } from "@/utils";
import { twMerge } from "tailwind-merge";
import { VariantProps, tv } from "tailwind-variants";
import clsx from "clsx";

const inputVariants = tv({
  base: "input",
  variants: {
    size: {
      xs: "input-xs",
      sm: "input-sm",
      md: "input-md",
      lg: "input-lg",
    },
		border: {
			true: "input-bordered",
			false: ""
		},
    color: {
      default: "",
      primary: "input-primary",
      secondary: "input-secondary",
      accent: "input-accent",
      success: "input-success",
      info: "input-info",
      warning: "input-warning",
      error: "input-error",
      ghost: "input-ghost",
    },
    width: {
      fit: "w-fit",
      full: "w-full",
    },
  },
  defaultVariants: {
    size: "md",
    border: true,
		color: "default",
    width: "full",
  },
});
type InputVariantsType = VariantProps<typeof inputVariants>;

/*
 * InputWrapper is a wrapper component for input elements.
 * It is used to style the input element with the correct border and padding.
*/
interface InputWrapperProps
  extends Omit<
      React.LabelHTMLAttributes<HTMLLabelElement>,
      keyof InputVariantsType
    >,
    InputVariantsType {
  children: React.ReactNode;
}

function InputWrapper({
  size,
  color,
  width,
  className,
  children,
  ...rest
}: InputWrapperProps) {
  return (
    <label className={inputVariants({ size, color, width, className })} {...rest}>
      {children}
    </label>
  );
}

/*
 * Input is a component that renders an input element.
 * It accepts all the props that an input element accepts.
*/
interface InputProps
  extends Omit<
      React.InputHTMLAttributes<HTMLInputElement>,
      keyof InputVariantsType
    >,
    InputVariantsType {
			asChild?: boolean;
		}

const Input = forwardRef(
  ({ size, color, width, className, asChild, ...rest }: InputProps, ref) => {
    if(asChild){
			return <input className={clsx("grow", className)} {...rest} />
		}
		return (
      <input
        className={inputVariants({ size, color, width, className })}
        {...rest}
      />
    );
  },
);

Input.displayName = "Input";

export { Input };
