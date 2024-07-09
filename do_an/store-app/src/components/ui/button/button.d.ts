import { buttonVariants } from "./button-variants";
import { type VariantProps } from "tailwind-variants";

type ButtonVariantsType = VariantProps<typeof buttonVariants>;
interface ButtonProps
  extends ButtonVariantsType,
    Omit<
      React.ButtonHTMLAttributes<HTMLButtonElement>,
      keyof ButtonVariantsType
    > {}

export type { ButtonProps };