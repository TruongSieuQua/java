import { forwardRef } from "react";

import { buttonVariants } from "./button-variants";
import type { VariantProps } from "tailwind-variants";

type ButtonVariantsType = VariantProps<typeof buttonVariants>;
interface ButtonProps
  extends ButtonVariantsType,
    Omit<
      React.ButtonHTMLAttributes<HTMLButtonElement>,
      keyof ButtonVariantsType
    > {}

const Button = forwardRef<HTMLButtonElement, ButtonProps>(
  (
    {
      size,
      rounded,
      width,
      color,
      variant,
      className,
      children,
      type="button",
      ...rest
    },
    ref
  ) => {

		return (
      <button
        ref={ref}
        className={buttonVariants({
          size,
          rounded,
          width,
          color,
          variant,
          className,
        })}
        {...rest}
        type={type}
      >
        {children}
      </button>
    );
  }
);

Button.displayName = "Button";
export { Button };
