import { tv } from "tailwind-variants";

export const input = tv({
  base: "input",
  variants: {
    size: {
      xs: "input-xs",
      sm: "input-sm",
      md: "input-md",
      lg: "input-lg",
    },
    border: {
      true: "input-bordered",
      false: "",
    },
    color: {
      default: "",
      primary: "input-primary",
      secondary: "input-secondary",
      accent: "input-accent",
      success: "input-success",
      info: "input-info",
      warning: "input-warning",
      error: "input-error",
      ghost: "input-ghost",
    },
    width: {
      fit: "w-fit",
      full: "w-full",
    },
  },
  defaultVariants: {
    size: "md",
    border: true,
    color: "default",
    width: "full",
  },
});

export const inputWithText = tv({
  extend: input,
  base: "input input-bordered flex items-center gap-2",
});

export const inputWithIcon = tv({
  extend: input,
  base: "input input-bordered flex items-center gap-2",
});
