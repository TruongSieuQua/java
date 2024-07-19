import type { HTMLAttributes, LabelHTMLAttributes  } from "react";
import type { VariantProps } from "tailwind-variants";

import {formFieldVariants} from "./form-variants";

interface FormRootProps extends HTMLAttributes<HTMLFormElement> {}

type FormFieldVariantsType = VariantProps<typeof formFieldVariants>;
interface FormFieldProps extends FormFieldVariantsType, Omit<HTMLAttributes<HTMLDivElement>, keyof FormFieldVariantsType> {}

type LabelVariantsType = VariantProps<typeof labelVariants>;
interface FormLabelProps extends LabelVariantsType, Omit<LabelHTMLAttributes<HTMLLabelElement>, keyof LabelVariantsType> {}

type FormControlType = VariantProps<typeof formControlVariants>;
interface FormControlProps extends FormControlType, Omit<HTMLAttributes<HTMLDivElement>, keyof FormControlType >{}

type MessageVariantsType = VariantProps<typeof messageVariants>;
interface FormMessageProps extends MessageVariantsType, Omit<HTMLAttributes<HTMLDivElement>, keyof MessageVariantsType> {}

interface FormSubmitProps extends HTMLAttributes<HTMLButtonElement> {
	asChild: boolean;
}
