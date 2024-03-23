import { extractTvProps } from "@/utils";
import { VariantProps, tv } from "tailwind-variants";

const buttonVariants = tv({
  base: "flex items-center justify-center",
  variants: {
    variant: {
			fill: "",
			outline: "",

		},
		color: {
      default: "bg-gray-500 hover:bg-gray-600",
      primary: "bg-blue-600 hover:bg-blue-700 text-blue-100 hover:text-white",
      success: "bg-green-600 hover:bg-green-700 text-teal-100 hover:text-white",
      info: "bg-blue-500 hover:bg-blue-600 text-blue-100 hover:text-white",
      warning:
        "bg-yellow-500 hover:bg-yellow-600 text-orange-100 hover:text-white",
      danger: "bg-red-500 hover:bg-red-600 text-orange-100 hover:text-white",
    },
  },
  defaultVariants: {
    color: "default", // will be used if we won't pass any prop for intent
  },
});
type ButtonVariantsType = VariantProps<typeof buttonVariants>;
const buttonVariantKeys = ["intent"];
export interface ButtonProps
  extends ButtonVariantsType,
    Omit<
      React.ButtonHTMLAttributes<HTMLButtonElement>,
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
