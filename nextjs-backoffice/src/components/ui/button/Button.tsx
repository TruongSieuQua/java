import { tv } from "tailwind-variants";

// intent will be used as a prop which will only accepts these // values
type Intent =
  | "primary"
  | "warning"
  | "danger"
  | "inverse"
  | "success"
  | "purple"
  | "default";

type ButtonVariantsProps = {
  intent?: Intent;

};

type ButtonProps = ButtonVariantsProps & {
  customClassNames?: string;
  children: React.ReactNode;
  [key: string]: any;
};

type ButtonVariantsFunction = (props: ButtonVariantsProps) => string;

const ButtonVariants: ButtonVariantsFunction = tv(
  {
		base: "transition-colors duration-150 cursor-pointer disabled:opacity-50 disabled:cursor-not-allowed focus:outline-none",
    variants: {
      intent: {
        primary: "bg-blue-600 hover:bg-blue-700 text-blue-100 hover:text-white",
        warning:
          "bg-yellow-500 hover:bg-yellow-600 text-orange-100 hover:text-white",
        danger: "bg-red-500 hover:bg-red-600 text-orange-100 hover:text-white",
        inverse: "bg-gray-600 hover:bg-gray-700 text-blue-100 hover:text-white",
        success:
          "bg-green-600 hover:bg-green-700 text-teal-100 hover:text-white",
        purple: "bg-indigo-700 hover:bg-indigo-800 text-white",
        default: "bg-gray-500 hover:bg-gray-600",
      },
    },

    defaultVariants: {
      intent: "default", // will be used if we won't pass any prop for intent
    },
  },
);

export default function Button({
  intent,
  className = "",
  children,
  ...props
}: ButtonProps): JSX.Element {
  return (
    <button className={`${ButtonVariants({ intent })} ${className}`} {...props}>
      {children}
    </button>
  );
}
