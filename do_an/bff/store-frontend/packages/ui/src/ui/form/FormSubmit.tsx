"use client";

import { clsx } from "clsx";
import { HTMLAttributes } from "react";
/* -----------------------------------------------------------------------------------------
 * Form Submit
 * ----------------------------------------------------------------------------------------*/
interface FormSubmitProps extends HTMLAttributes<HTMLButtonElement> {
	asChild: boolean;
}
export function FormSubmit({asChild, children, className, ...rest}: FormSubmitProps){
	if(asChild){
		return <>{children}</>
	}

	return <button className={clsx("w-full inline-flex items-center justify-center", className)} {...rest}>
		{children}
	</button>
}
