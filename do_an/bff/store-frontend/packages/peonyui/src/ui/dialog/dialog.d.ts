import type {HTMLProps, ButtonHTMLAttributes, Dispatch, ReactNode, SetStateAction} from "react";

interface DialogOptions {
    initialOpen?: boolean;
    open?: boolean;
    onOpenChange?: (open: boolean) => void;
}

interface DialogContextType {
    setLabelId: Dispatch<SetStateAction<string | undefined>>;
    setDescriptionId: Dispatch<SetStateAction<string | undefined>>;
}

interface DialogProps extends DialogOptions{
    children: ReactNode;
};


interface DialogTriggerProps extends HTMLProps<HTMLElement> {
    children: ReactNode;
    asChild?: boolean;
}

interface DialogContentProps extends  HTMLProps<HTMLDivElement>{}

interface DialogHeadingProps extends HTMLProps<HTMLHeadingElement> {}

interface DialogDescriptionProps extends HTMLProps<HTMLParagraphElement> {}

interface DialogCloseButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {}
