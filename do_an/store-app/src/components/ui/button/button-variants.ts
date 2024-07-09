import { tv } from "tailwind-variants";

export const buttonVariants = tv({
  base: "btn min-w-max",
  variants: {
    size: {
      xs: "btn-xs",
      sm: "btn-sm",
      md: "btn-md",
      lg: "btn-lg",
    },
    rounded: {
      none: "",
      sm: "rounded-sm",
      md: "rounded-md",
      lg: "rounded-lg",
      xl: "rounded-xl",
    },
    width: {
      fit: "w-fit",
      full: "w-full",
    },
    color: {
      active: "btn-active",
      neutral: "btn-active btn-neutral",
      primary: "btn-active btn-primary",
      secondary: "btn-active btn-secondary",
      accent: "btn-active btn-accent",
      success: "btn-active btn-success",
      info: "btn-active btn-info",
      warning: "btn-active btn-warning",
      danger: "btn-active btn-error",
      ghost: "btn-active btn-ghost",
      link: "btn-active btn-link",
    },
    variant: {
      fill: "",
      outline: "btn-outline",
    },
  },
  defaultVariants: {
    width: "full",
    size: "md",
  },
});
