"use client"
import { extractTvProps } from "@/utils";
import { forwardRef } from "react";
import { VariantProps, tv } from "tailwind-variants";

const buttonVariants = tv({
  base: "btn min-w-max",
  variants: {
		size: {
			xs: "btn-xs",
			sm: "btn-sm",
			md: "btn-md",
			lg: "btn-lg",
		},
		rounded:{
			none: "",
			sm: "rounded-sm",
			md: "rounded-md",
			lg: "rounded-lg",
			xl: "rounded-xl",
		},
		width:{
			fit: "w-fit",
			full: "w-full",
		},
		color: {
      active: "btn-active",
			neutral: "btn-active btn-neutral",
			primary: "btn-active btn-primary",
			secondary: "btn-active btn-secondary",
			accent: "btn-active btn-accent",
			success: "btn-active btn-success",
			info: "btn-active btn-info",
			warning: "btn-active btn-warning",
			danger: "btn-active btn-error",
			ghost : "btn-active btn-ghost",
			link: "btn-active btn-link",
    },
		variant: {
			fill: "",
			outline: "btn-outline",
		},

  },
  defaultVariants: {
		width: "full",
		size: "md",
  },
});
type ButtonVariantsType = VariantProps<typeof buttonVariants>;
const buttonVariantKeys = ["size", "rounded", "width", "color", "variant"];
export interface ButtonProps
  extends ButtonVariantsType,
    Omit<
      React.ButtonHTMLAttributes<HTMLButtonElement>,
			// React.ComponentPropsWithoutRef<"button">,
      keyof ButtonVariantsType
    > {}
export const Button = forwardRef<HTMLButtonElement, ButtonProps>(({
	size, rounded, width, color, variant, className, children, type, ...rest
}, ref) => {

	return (
    <button ref={ref} className={buttonVariants({size, rounded, width, color, variant, className})} {...rest} type={type}>
      {children}
    </button>
  );
});
Button.displayName = "Button";
