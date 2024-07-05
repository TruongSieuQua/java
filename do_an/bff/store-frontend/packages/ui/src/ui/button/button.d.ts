import { VariantProps } from "tailwind-variants";
import { buttonVariants } from "./Button";

export type ButtonVariantsType = VariantProps<typeof buttonVariants>;

export interface ButtonProps
  extends ButtonVariantsType,
    Omit<
      React.ButtonHTMLAttributes<HTMLButtonElement>,
      // React.ComponentPropsWithoutRef<"button">,
      keyof ButtonVariantsType
    > {}
