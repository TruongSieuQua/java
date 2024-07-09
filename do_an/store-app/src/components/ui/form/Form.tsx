import type {
	FormControlProps,
	FormFieldProps,
	FormLabelProps,
	FormMessageProps,
	FormRootProps,
	FormSubmitProps,
} from "./form";
import {
	formControlVariants,
	formFieldVariants,
	labelVariants,
	messageVariants,
} from "./form-variants";
import clsx from "clsx";

/* -----------------------------------------------------------------------------------------
 * Form Root
 * ----------------------------------------------------------------------------------------*/
function FormRoot({ children, ...rest }: FormRootProps) {
  return <form {...rest}>{children}</form>;
}
export { FormRoot as Form };
/* -----------------------------------------------------------------------------------------
 * Form Field
 * ----------------------------------------------------------------------------------------*/
export function FormField({
  display,
  className,
  children,
  ...rest
}: FormFieldProps) {
  return (
    <div className={formFieldVariants({ display, className })} {...rest}>
      {children}
    </div>
  );
}
/* -----------------------------------------------------------------------------------------
 * Form Label
 * ---------------------------------------------------------------------------------------*/
export function FormLabel({
  size,
  className,
  children,
  ...rest
}: FormLabelProps) {
  return (
    <label className={labelVariants({ size, className })} {...rest}>
      {children}
    </label>
  );
}

/* -----------------------------------------------------------------------------------------
 * Form Message
 * ----------------------------------------------------------------------------------------*/
export function FormMessage({
  size,
  color,
  className,
  children,
  ...rest
}: FormMessageProps) {
  return (
    <div className={messageVariants({ size, color, className })} {...rest}>
      {children}
    </div>
  );
}

/* -----------------------------------------------------------------------------------------
 * Form Control
 * ----------------------------------------------------------------------------------------*/
export function FormControl({
  className,
  children,
  ...rest
}: FormControlProps) {
  return (
    <div className={formControlVariants({ className })} {...rest}>
      {children}
    </div>
  );
}

/* -----------------------------------------------------------------------------------------
 * Form Submit
 * ----------------------------------------------------------------------------------------*/
export function FormSubmit({
  asChild,
  children,
  className,
  ...rest
}: FormSubmitProps) {
  if (asChild) {
    return <>{children}</>;
  }

  return (
    <button
      className={clsx(
        "w-full inline-flex items-center justify-center",
        className
      )}
      {...rest}
    >
      {children}
    </button>
  );
}
