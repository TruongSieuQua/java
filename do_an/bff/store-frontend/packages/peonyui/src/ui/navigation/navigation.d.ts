import type {	ButtonHTMLAttributes, DetailedHTMLProps, HTMLAttributes, HTMLButtonElement,ReactNode } from "react";

interface NavigationProps extends HTMLAttributes<HTMLDivElement> {}

interface NavigationListProps extends HTMLAttributes<HTMLUListElement> {}

interface NavigationListItemProps extends HTMLAttributes<HTMLLIElement> {}

interface NavigationMenuProps extends UseFloatingOptions {
	children: ReactNode;
}

interface NavigationMenuTriggerProps extends ButtonHTMLAttributes<HTMLButtonElement> {
	children: ReactNode | DetailedHTMLProps<ButtonHTMLAttributes<HTMLButtonElement>>;
	asChild?: boolean;
}

interface NavigationMenuPortalProps extends HTMLAttributes<HTMLDivElement> {
	arrowClassName?: string;
}

interface NavigationMenuContentProps extends HTMLAttributes<HTMLDivElement> {}

interface NavigationMenuContentProps extends HTMLAttributes<HTMLDivElement> {}
