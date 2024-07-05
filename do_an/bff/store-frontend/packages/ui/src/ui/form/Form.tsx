
import { HTMLAttributes, LabelHTMLAttributes } from "react";
import { VariantProps, tv } from "tailwind-variants";

/* -----------------------------------------------------------------------------------------
 * Form Root
 * ----------------------------------------------------------------------------------------*/
interface FormRootProps extends HTMLAttributes<HTMLFormElement> {}
function FormRoot({children, ...rest}: FormRootProps){
	return <form {...rest}>
		{children}
	</form>
}
export {FormRoot as Form};
/* -----------------------------------------------------------------------------------------
 * Form Field
 * ----------------------------------------------------------------------------------------*/
const formFieldVariants = tv({
	base: "",
	variants: {
		display: {
			grid: "grid",
			flex: "flex flex-col",
		}
	},
	defaultVariants: {
		display: "grid",
	}
})
type FormFieldVariantsType = VariantProps<typeof formFieldVariants>;
interface FormFieldProps extends FormFieldVariantsType, Omit<HTMLAttributes<HTMLDivElement>, keyof FormFieldVariantsType> {}
export function FormField({display, className, children, ...rest}:  FormFieldProps){
	return <div className={formFieldVariants({display, className})} {...rest}>
		{children}
	</div>
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
type LabelVariantsType = VariantProps<typeof labelVariants>;
interface FormLabelProps extends LabelVariantsType, Omit<LabelHTMLAttributes<HTMLLabelElement>, keyof LabelVariantsType> {}
export function FormLabel({
	size,
	className,
	children,
    ...rest
}: FormLabelProps){
	return <label className={labelVariants({size, className})} {...rest}>
		{children}
	</label>
}

/* -----------------------------------------------------------------------------------------
 * Form Message
 * ----------------------------------------------------------------------------------------*/
const messageVariants = tv({
	base: "text-[13px] opacity-[0.8] my-1",
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
interface FormMessageProps extends MessageVariantsType, Omit<HTMLAttributes<HTMLDivElement>, keyof MessageVariantsType> {}
export function FormMessage({
	size,
	color,
	className,
	children,
	...rest
}: FormMessageProps){
	return <div className={messageVariants({size, color, className})} {...rest}>
		{children}
	</div>
}

/* -----------------------------------------------------------------------------------------
 * Form Control
 * ----------------------------------------------------------------------------------------*/
const inputVariants = tv({
	base: "join",
});
type InputVariantsType = VariantProps<typeof inputVariants>;
interface FormInputProps extends InputVariantsType, Omit<HTMLAttributes<HTMLInputElement>, keyof InputVariantsType >{}
export function FormControl({className, children, ...rest}: FormInputProps){
	return <div className={inputVariants({className})} {...rest}>
		{children}
	</div>
}
