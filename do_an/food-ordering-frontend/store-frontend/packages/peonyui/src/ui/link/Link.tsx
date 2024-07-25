import { link } from "./link-variants";

import type { HTMLAttributes } from "react";
import type { VariantProps } from "tailwind-variants";
type LinkType = VariantProps<typeof link>;

interface LinkProps
	extends Omit<HTMLAttributes<HTMLDivElement>, keyof LinkType>,
		LinkType {
	href: string;
}

interface LinkTextProps
	extends Omit<React.HTMLAttributes<HTMLParagraphElement>, keyof LinkType>,
		LinkType {}

/*
 * Link renders a div element with link styles.
*/
export function Link({ children, className, href, color, ...rest }: LinkProps) {
	return (
			<div className={link({ color, className })} {...rest}>
				{children}
			</div>
  );
}

/*
 * LinkText renders a paragraph element with link styles.
*/
export function LinkText({ children, className, color, ...rest }: LinkTextProps) {
	return (
		<p className={link({ color, className })} {...rest}>
			{children}
		</p>
	);
}
