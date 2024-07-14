import type { ReactNode, HTMLProps,HTMLDivElement, ButtonHTMLAttributes, HTMLButtonElement} from "react";
import type { UseFloatingOptions, PopoverTriggerProps } from "@floating-ui/react";

interface DropdownOptions extends UseFloatingOptions {
    initialOpen?: boolean;
    modal?: boolean;
    open?: boolean;
    onOpenChange?: (open: boolean) => void;
}

type ContextType = ReturnType<typeof useDropdown> | null;

interface DropdownProps extends DropdownOptions {
	children: ReactNode;
}

interface DropdownTriggerProps extends ButtonHTMLAttributes<HTMLButtonElement>{
	children: ReactNode | DetailedHTMLProps<ButtonHTMLAttributes<HTMLButtonElement>>;
	asChild?: boolean;
}

interface DropdownContentProps extends HTMLProps<HTMLDivElement> {}
