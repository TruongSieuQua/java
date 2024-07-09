import { tv } from "tailwind-variants";

export const formFieldVariants = tv({
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

export const labelVariants = tv({
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

export const formControlVariants = tv({
	base: "join",
});

export const messageVariants = tv({
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
