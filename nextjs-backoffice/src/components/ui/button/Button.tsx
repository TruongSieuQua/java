import { extractTvProps } from "@/utils";
import { VariantProps, tv } from "tailwind-variants";

const buttonVariants = tv({
  base: "btn",
  variants: {
		size: {
			xs: "btn-xs px-3 py-2",
			sm: "btn-sm px-3 py-2",
			md: "px-5 py-2.5",
			lg: "btn-lg px-5 py-3",
		},
		rounded:{
			none: "",
			sm: "rounded-sm",
			md: "rounded-md",
			lg: "rounded-lg",
			xl: "rounded-xl",
		},
		width:{
			fit: "",
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
		size: "md",
		width: "fit",
		rounded: "md",
    color: "active",
		variant: "outline",
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
