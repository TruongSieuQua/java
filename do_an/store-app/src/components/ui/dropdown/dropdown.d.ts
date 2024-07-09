import { UseFloatingOptions } from "@floating-ui/react";

export interface DropdownOptions extends UseFloatingOptions {
    initialOpen?: boolean;
    modal?: boolean;
    open?: boolean;
    onOpenChange?: (open: boolean) => void;
}

type ContextType = ReturnType<typeof useDropdown> | null;

export interface PopoverTriggerProps {
    children: React.ReactNode;
    asChild?: boolean;
}