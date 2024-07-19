
import type {LinkProps, LinkTextProps} from "./link.d";
import { link } from "./link-variants";

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
