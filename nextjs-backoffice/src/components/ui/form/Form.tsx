"use client";

import * as Form from "@radix-ui/react-form";
import { twMerge } from "tailwind-merge";
import { tv } from "tailwind-variants";

export function FormRoot({children, className, ...rest}: Form.FormProps){
	return <Form.Root className={twMerge("", className)} {...rest}>
		{children}
	</Form.Root>
}

export function FormField({children, className, ...rest}:  Form.FormFieldProps){
	return <Form.Field className={twMerge("grid mb-[10px]", className)} {...rest}>
		{children}
	</Form.Field>
}

export function FormLabel({children, className, ...rest}: Form.FormLabelProps){
	return <Form.Label className={twMerge("text-[15px] font-medium leading-[35px] text-heading", className)} {...rest}>
		{children}
	</Form.Label>
}

export function FormMessage({children, className, ...rest}: Form.FormMessageProps){
	return <Form.Message className={twMerge("text-[13px] opacity-[0.8] valid:text-success invalid:text-error", className)} {...rest}>
		{children}
	</Form.Message>
}

export function FormControl({children, ...rest}: Form.FormControlProps){
	return <Form.Control {...rest} asChild>
		{children}
	</Form.Control>
}
