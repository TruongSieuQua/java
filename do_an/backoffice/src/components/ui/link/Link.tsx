import { VariantProps, tv } from "tailwind-variants";

const link = tv({
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
type LinkType = VariantProps<typeof link>;

interface LinkProps
  extends Omit<React.AnchorHTMLAttributes<HTMLAnchorElement>, keyof LinkType>,
    LinkType {}

export function Link({ children, className, color, ...rest }: LinkProps) {
	return (
    <a className={link({ color, className })} {...rest}>
      {children}
    </a>
  );
}

/*
 * LinkText
*/
interface LinkTextProps extends Omit<React.HTMLAttributes<HTMLParagraphElement>, keyof LinkType>, LinkType{}
export function LinkText({ children, className, color, ...rest }: LinkTextProps) {
	return (
		<p className={link({ color, className })} {...rest}>
			{children}
		</p>
	);
}
