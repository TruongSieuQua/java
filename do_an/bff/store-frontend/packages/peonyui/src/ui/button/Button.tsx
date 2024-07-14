import type { ButtonProps } from "./button.d";
import { forwardRef } from "react";
import { buttonVariants } from "./button-variants";

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
