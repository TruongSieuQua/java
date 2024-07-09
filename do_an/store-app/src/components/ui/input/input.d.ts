import type { VariantProps } from "tailwind-variants";
import { input, inputWithIcon, inputWithText } from "./input-variants";

type InputVariantsType = VariantProps<typeof input>;
export interface InputProps
  extends Omit<
      React.InputHTMLAttributes<HTMLInputElement>,
      keyof InputVariantsType
    >,
    InputVariantsType {}

export interface InputTextLabelProps extends InputProps {
  label: string;
}

export interface InputIconProps extends InputProps {
  icon: React.ReactNode;
  iconPosition?: "left" | "right";
}
