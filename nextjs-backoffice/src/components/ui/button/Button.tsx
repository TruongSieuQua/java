import { extractTvProps } from "@/utils";
import { VariantProps, tv } from "tailwind-variants";

const buttonVariants = tv({
  base: "space-x-2 border-[1px]",
  variants: {
		size: {
			xs: "text-xs px-3 py-2",
			sm: "text-sm px-3 py-2",
			md: "text-base px-5 py-2.5",
			lg: "text-lg px-5 py-3",
			xl: "text-xl px-6 py-3.5",
		},
		rounded:{
			none: "",
			sm: "rounded-sm",
			md: "rounded-md",
			lg: "rounded-lg",
			xl: "rounded-xl",
		},
		width:{
			default: "",
			full: "w-full",
		},
		color: {
      default: "bg-default hover:bg-default-hover shadow-default border-default text-default",
			primary: "bg-primary hover:bg-primary-hover shadow-primary border-primary text-primary",
			secondary: "bg-secondary hover:bg-secondary-hover shadow-secondary border-secondary text-secondary",
			success: "bg-success hover:bg-success-hover shadow-success border-success text-success",
			info: "bg-info hover:bg-info-hover shadow-info border-info text-info",
			warning: "bg-warning hover:bg-warning-hover shadow-warning border-warning text-warning",
			error: "bg-error hover:bg-error-hover shadow-error border-error text-error",
    },
		variant: {
			fill: "text-default",
			outline: "bg-transparent",
			ghost: "bg-transparent shadow-none border-transparent",
			link: "bg-transparent hover:bg-transparent underlined",
		},

  },
  defaultVariants: {
		size: "md",
		width: "default",
		rounded: "md",
    color: "default",
		variant: "fill",
  },
});
type ButtonVariantsType = VariantProps<typeof buttonVariants>;
const buttonVariantKeys = ["size", "rounded", "width", "color", "variant"];
export interface ButtonProps
  extends ButtonVariantsType,
    Omit<
      // React.ButtonHTMLAttributes<HTMLButtonElement>,
			React.ComponentPropsWithoutRef<"button">,
      keyof ButtonVariantsType
    > {}
export function Button(props: ButtonProps): JSX.Element {
  const { tvProps, className, children, ...rest } = extractTvProps<ButtonProps, ButtonVariantsType>(props, ...buttonVariantKeys);

	return (
    <button className={buttonVariants({...tvProps, className})} {...rest}>
      {children}
    </button>
  );
}
