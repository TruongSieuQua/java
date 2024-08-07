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
