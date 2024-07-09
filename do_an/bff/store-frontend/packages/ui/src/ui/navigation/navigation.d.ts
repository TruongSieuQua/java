import type {	HTMLAttributes, ReactNode, DetailedHTMLProps, ButtonHTMLAttributes, HTMLButtonElement } from "react";

export interface NavigationProps extends HTMLAttributes<HTMLDivElement> {}

export interface NavigationListProps extends HTMLAttributes<HTMLUListElement> {}

export interface NavigationListItemProps extends HTMLAttributes<HTMLLIElement> {}

export interface NavigationMenuProps extends UseFloatingOptions {
	children: ReactNode;
}

export interface NavigationMenuTriggerProps extends ButtonHTMLAttributes<HTMLButtonElement> {
	children: ReactNode | DetailedHTMLProps<ButtonHTMLAttributes<HTMLButtonElement>>;
	asChild?: boolean;
}

export interface NavigationMenuPortalProps extends HTMLAttributes<HTMLDivElement> {
	arrowClassName?: string;
}

export interface NavigationMenuContentProps extends HTMLAttributes<HTMLDivElement> {}

export interface NavigationMenuContentProps extends HTMLAttributes<HTMLDivElement> {}
