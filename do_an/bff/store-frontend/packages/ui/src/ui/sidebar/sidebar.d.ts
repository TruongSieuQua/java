import type {DetailedHTMLProps, HTMLAttributes, ReactNode, HTMLLinkElement, AnchorHTMLAttributes} from 'react';

interface SideBarProps {
  children: ReactNode;
}

interface SideBarToggleProps extends DetailedHTMLProps<React.HTMLAttributes<HTMLButtonElement>, HTMLButtonElement> {
	asChild?: boolean;
}

interface SideBarLinkProps extends AnchorHTMLAttributes<HTMLAnchorElement> {
  children: React.ReactNode;
}
