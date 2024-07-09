import type {HTMLProps, ButtonHTMLAttributes, Dispatch, ReactNode, SetStateAction} from "react";

export interface DialogOptions {
    initialOpen?: boolean;
    open?: boolean;
    onOpenChange?: (open: boolean) => void;
}

export interface DialogContextType {
    setLabelId: Dispatch<SetStateAction<string | undefined>>;
    setDescriptionId: Dispatch<SetStateAction<string | undefined>>;
}

export interface DialogProps extends DialogOptions{
    children: ReactNode;
};


export interface DialogTriggerProps extends HTMLProps<HTMLElement> {
    children: ReactNode;
    asChild?: boolean;
}

export interface DialogContentProps extends  HTMLProps<HTMLDivElement>{}

export interface DialogHeadingProps extends HTMLProps<HTMLHeadingElement> {}

export interface DialogDescriptionProps extends HTMLProps<HTMLParagraphElement> {}

export interface DialogCloseButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {}