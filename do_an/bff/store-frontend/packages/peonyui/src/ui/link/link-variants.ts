import { tv } from "tailwind-variants";

export const link = tv({
  base: "link link-hover",
  variants: {
    color: {
      default: "",
      primary: "link-primary",
      neutral: "link-neutral",
      success: "link-success",
      info: "link-info",
      warning: "link-warning",
      error: "link-error",
    },
		display: {
			block: "block",
			inline: "inline",
		}
  },
  defaultVariants: {
    color: "primary",
		display: "inline",
  },
});
