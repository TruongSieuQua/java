import type {ButtonHTMLAttributes, HTMLButtonElement, DetailedHTMLProps, HTMLAttributes, ReactNode, HTMLLinkElement, AnchorHTMLAttributes} from 'react';

interface SideBarProps {
  children: ReactNode;
}

interface SideBarToggleProps extends ButtonHTMLAttributes<HTMLButtonElement> {
	children?: ReactNode | DetailedHTMLProps<ButtonHTMLAttributes<HTMLButtonElement>>;
	asChild?: boolean;
}

interface SideBarLinkProps extends AnchorHTMLAttributes<HTMLAnchorElement> {
  children: React.ReactNode;
}
