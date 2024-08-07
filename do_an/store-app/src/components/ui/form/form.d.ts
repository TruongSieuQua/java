import {formFieldVariants} from "./form-variants";
import type { VariantProps } from "tailwind-variants";
import type { HTMLAttributes, LabelHTMLAttributes  } from "react";

export interface FormRootProps extends HTMLAttributes<HTMLFormElement> {}

type FormFieldVariantsType = VariantProps<typeof formFieldVariants>;
export interface FormFieldProps extends FormFieldVariantsType, Omit<HTMLAttributes<HTMLDivElement>, keyof FormFieldVariantsType> {}

type LabelVariantsType = VariantProps<typeof labelVariants>;
export interface FormLabelProps extends LabelVariantsType, Omit<LabelHTMLAttributes<HTMLLabelElement>, keyof LabelVariantsType> {}

type FormControlType = VariantProps<typeof formControlVariants>;
export interface FormControlProps extends FormControlType, Omit<HTMLAttributes<HTMLDivElement>, keyof FormControlType >{}

type MessageVariantsType = VariantProps<typeof messageVariants>;
interface FormMessageProps extends MessageVariantsType, Omit<HTMLAttributes<HTMLDivElement>, keyof MessageVariantsType> {}

interface FormSubmitProps extends HTMLAttributes<HTMLButtonElement> {
	asChild: boolean;
}