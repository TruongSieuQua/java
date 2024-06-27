"use client";

import { extractTvProps } from "@/utils";
import * as Form from "@radix-ui/react-form";
import { twMerge } from "tailwind-merge";
import { VariantProps, tv } from "tailwind-variants";

/* -----------------------------------------------------------------------------------------
 * Form Root
 * ----------------------------------------------------------------------------------------*/
function FormRoot({children, className, ...rest}: Form.FormProps){
	return <Form.Root className={twMerge("", className)} {...rest}>
		{children}
	</Form.Root>
}
export {FormRoot as Form};
/* -----------------------------------------------------------------------------------------
 * Form Field
 * ----------------------------------------------------------------------------------------*/
export function FormField({children, className, ...rest}:  Form.FormFieldProps){
	return <Form.Field className={twMerge("grid mb-[10px]", className)} {...rest}>
		{children}
	</Form.Field>
}
/* -----------------------------------------------------------------------------------------
 * Form Label
 * ---------------------------------------------------------------------------------------*/
const labelVariants = tv({
	base: "font-medium text-heading",
	variants: {
		size: {
			xs: "text-xs leading-7",
			sm: "text-sm leading-8",
			md: "text-base leading-9",
			lg: "text-lg leading-11",
		},
	},
	defaultVariants:  {
		size: "md",
	}
});
const labelVariantKeys = ["size"];
type LabelVariantsType = VariantProps<typeof labelVariants>;
interface FormLabelProps extends LabelVariantsType, Omit<Form.FormLabelProps, keyof LabelVariantsType> {}

export function FormLabel(props: FormLabelProps){
	const {tvProps, className, children, ...rest} = extractTvProps<FormLabelProps, LabelVariantsType>(props, ...labelVariantKeys);

	return <Form.Label className={labelVariants({...tvProps, className})} {...rest}>
		{children}
	</Form.Label>
}

/* -----------------------------------------------------------------------------------------
 * Form Message
 * ----------------------------------------------------------------------------------------*/
const messageVariants = tv({
	base: "text-[13px] opacity-[0.8]",
	variants: {
		size:{
			xs: "text-[0.625rem] leading-3",
			sm: "text-xs leading-4",
			md: "text-sm leading-5",
			lg: "text-base leading-6",
		},
		color: {
			default: "text-muted",
			primary: "text-muted-primary",
			secondary: "text-muted-secondary",
			success: "text-muted-success",
			info: "text-muted-info",
			warning: "text-muted-warning",
			danger: "text-muted-error",
		}
	},
	defaultVariants: {
		size: "md",
		color: "default",
	},
});
type MessageVariantsType = VariantProps<typeof messageVariants>;
const messageVariantKeys = ["size", "color"];
interface FormMessageProps extends MessageVariantsType, Omit<Form.FormMessageProps, keyof MessageVariantsType> {}

export function FormMessage(props: FormMessageProps){
	const {tvProps, className, children, ...rest} = extractTvProps<FormMessageProps, MessageVariantsType>(props, ...messageVariantKeys);

	return <Form.Message className={messageVariants({...tvProps, className})} {...rest}>
		{children}
	</Form.Message>
}

/* -----------------------------------------------------------------------------------------
 * Form Control
 * ----------------------------------------------------------------------------------------*/
export function FormControl({children, ...rest}: Form.FormControlProps){
	return <Form.Control {...rest} asChild>
		{children}
	</Form.Control>
}

/* -----------------------------------------------------------------------------------------
 * Form Submit
 * ----------------------------------------------------------------------------------------*/
export function FormSubmit({children, className, ...rest}: Form.FormSubmitProps){
	return <Form.Submit className={twMerge("w-full inline-flex items-center justify-center", className)} {...rest}>
		{children}
	</Form.Submit>
}