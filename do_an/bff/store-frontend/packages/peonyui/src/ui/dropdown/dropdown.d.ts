import type { PopoverTriggerProps,UseFloatingOptions } from "@floating-ui/react";
import type { ButtonHTMLAttributes, HTMLButtonElement,HTMLDivElement, HTMLProps,ReactNode} from "react";

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
