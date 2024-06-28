"use client";

import React, { forwardRef } from "react";
import { VariantProps, tv } from "tailwind-variants";

const input = tv({
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
      false: "",
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
const inputWithText = tv({
	extend: input,
	base: "input input-bordered flex items-center gap-2"
})
const inputWithIcon = tv({
	extend: input,
	base: "input input-bordered flex items-center gap-2"
})

type InputVariantsType = VariantProps<typeof input>;
interface InputProps
  extends Omit<
      React.InputHTMLAttributes<HTMLInputElement>,
      keyof InputVariantsType
    >,
    InputVariantsType {}

/*
 * Input is a component that renders an input element.
 * It accepts all the props that an input element accepts.
 */
const Input = forwardRef<HTMLInputElement, InputProps>(
  ({ size, color, width, className, ...rest }, ref) => {
    return (
      <input
				ref={ref}
        className={input({ size, color, width, className })}
        {...rest}
      />
    );
  },
);

/*
 * InputTextLabel is a component that renders a label element inside input.
 */
interface InputTextLabelProps extends InputProps {
  label: string;
}

const InputTextLabel = forwardRef<HTMLInputElement, InputTextLabelProps>(({
  size,
  color,
  width,
  className,
  children,
  label,
  ...rest
}, ref) => {
  return (
    <label
      className={inputWithText({ size, color, width, className })}
    >
      {label}
      <input ref={ref} className="grow" {...rest} />
    </label>
  );
})

/*
* InputIcon is a component that renders an icon inside input.
*/
interface InputIconProps extends InputProps {
	icon: React.ReactNode;
	iconPosition?: "left" | "right";
}
const InputIcon = forwardRef<HTMLInputElement, InputIconProps>(({
  size,
  color,
  width,
  className,
  children,
	icon,
  iconPosition="left",
  ...rest
}, ref) => {
  return (
    <label
      className={inputWithIcon({ size, color, width, className })}
    >
      {iconPosition === "left" && icon}
      <input ref={ref} className="grow" {...rest} />
			{iconPosition === "right" && icon}
    </label>
  );
})

Input.displayName = "Input";
InputTextLabel.displayName = "InputTextLabel";
InputIcon.displayName = "InputIcon";

export { Input, InputTextLabel, InputIcon};
