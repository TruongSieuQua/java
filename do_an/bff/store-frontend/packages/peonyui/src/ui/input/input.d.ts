import type { VariantProps } from "tailwind-variants";

import { input, inputWithIcon, inputWithText } from "./input-variants";

type InputVariantsType = VariantProps<typeof input>;
interface InputProps
  extends Omit<
      React.InputHTMLAttributes<HTMLInputElement>,
      keyof InputVariantsType
    >,
    InputVariantsType {}

interface InputTextLabelProps extends InputProps {
  label: string;
}

interface InputIconProps extends InputProps {
  icon: React.ReactNode;
  iconPosition?: "left" | "right";
}
