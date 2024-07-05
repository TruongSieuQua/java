import { VariantProps, tv } from "tailwind-variants";
import NextLink from "next/link";
import { HTMLAttributes } from "react";

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
  extends Omit<HTMLAttributes<HTMLDivElement>, keyof LinkType>,
    LinkType {
			href: string;
		}

export function Link({ children, className, href, color, ...rest }: LinkProps) {
	return (
		<NextLink href={href}>
			<div className={link({ color, className })} {...rest}>
				{children}
			</div>
		</NextLink>
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
