import { type VariantProps } from "tailwind-variants";

import { buttonVariants } from "./button-variants";

type ButtonVariantsType = VariantProps<typeof buttonVariants>;
interface ButtonProps
  extends ButtonVariantsType,
    Omit<
      React.ButtonHTMLAttributes<HTMLButtonElement>,
      keyof ButtonVariantsType
    > {}
