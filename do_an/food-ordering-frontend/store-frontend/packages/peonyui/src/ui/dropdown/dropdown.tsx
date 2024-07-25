"use client";
import type {
	ArrowOptions,
	FlipOptions,
	OffsetOptions,
	ShiftOptions,
	UseFloatingOptions,
	UseTransitionStylesProps,
} from "@floating-ui/react";
import {
	arrow,
	flip,
	FloatingArrow,
	offset,
	shift,
	useClick,
	useDismiss,
	useFloating,
	useInteractions,
	useMergeRefs,
	useRole,
	useTransitionStyles,
} from "@floating-ui/react";
import type { HTMLAttributes, HTMLProps, ReactNode } from "react";
import {
	createContext,
	forwardRef,
	useContext,
	useMemo,
	useRef,
	useState,
} from "react";
import { Slot } from "../slot";
import clsx from "clsx";

export interface DropdownOptions {
	initialOpen?: boolean;
	floatingOptions?: UseFloatingOptions;
	offSetOptions?: OffsetOptions;
	flipOptions?: FlipOptions;
	shiftOptions?: ShiftOptions;
	arrowOptions?: ArrowOptions;
	transitionStylesOptions?: UseTransitionStylesProps;
}

export type ContextType = ReturnType<typeof useDropdown> | null;

export interface DropdownProps extends DropdownOptions {
	children: ReactNode;
}

export interface DropdownTriggerProps
	extends HTMLAttributes<HTMLButtonElement> {
	children: ReactNode;
	asChild?: boolean;
}

export interface DropdownContentProps extends HTMLProps<HTMLDivElement> {
	parentProps?: HTMLProps<HTMLDivElement>;
	classOptions?: {
		open?: string;
		close?: string;
	};
}

/* ***************************************************************************
 * Global variables
 * ***************************************************************************
 */
const GAP = 2;
const ARROW_WIDTH = 8;
const ARROW_HEIGHT = 8;
const DropdownContext = createContext<ContextType>(null);

function useDropdown({
	initialOpen,
	floatingOptions,
	offSetOptions = GAP + ARROW_HEIGHT,
	flipOptions,
	shiftOptions,
	arrowOptions,
	transitionStylesOptions,
}: DropdownOptions = {}) {
	const [uncontrolledOpen, setUncontrolledOpen] = useState(initialOpen);
	const open = floatingOptions?.open ?? uncontrolledOpen;
	const setOpen = floatingOptions?.onOpenChange ?? setUncontrolledOpen;
	const arrowRef = useRef(null);
	const data = useFloating({
		...floatingOptions,
		open,
		onOpenChange: setOpen,
		middleware: [
			offset(offSetOptions),
			flip(flipOptions),
			shift(shiftOptions),
			arrow({ ...arrowOptions, element: arrowRef }),
		],
	});
	const context = data.context;
	const click = useClick(context);
	const dismiss = useDismiss(context);
	const role = useRole(context);
	const interactions = useInteractions([click, dismiss, role]);
	const { isMounted, styles } = useTransitionStyles(context, transitionStylesOptions);

	return useMemo(
		() => ({
			isMounted,
			styles,
			open,
			setOpen,
			...interactions,
			...data,
			arrowRef,
		}),
		[isMounted, styles, open, setOpen, interactions, data, arrowRef],
	);
}
const useDropdownContext = () => {
	const context = useContext(DropdownContext);

	if (context == null) {
		throw new Error("Dropdown components must be wrapped in <Dropdown />");
	}

	return context;
};
const Dropdown = ({ children, ...props }: DropdownProps) => {
	const dropdown = useDropdown({ ...props });
	return (
		<DropdownContext.Provider value={dropdown}>
			{children}
		</DropdownContext.Provider>
	);
};
const DropdownTrigger = forwardRef<HTMLElement, DropdownTriggerProps>(
	function DropdownTrigger(
		{ children, asChild = false, className, ...props },
		propRef,
	) {
		const { open, getReferenceProps, refs } = useDropdownContext();
		const childrenRef = (children as any).ref;
		const ref = useMergeRefs([refs.setReference, propRef, childrenRef]);
		const Comp = asChild ? Slot : "button";

		return (
			<Comp
				ref={ref}
				{...getReferenceProps({ ref, ...props })}
				data-state={open ? "open" : "close"}
			>
				{children}
			</Comp>
		);
	},
);
const DropdownContent = forwardRef<HTMLDivElement, DropdownContentProps>(
	(
		{
			children,
			parentProps: { style: parentStyle, ...parentProps } = {},
			style,
			className,
			classOptions,
			...props
		},
		propRef,
	) => {
		const { isMounted, styles, open, context, getFloatingProps } =
			useDropdownContext();
		const ref = useMergeRefs([context.refs.setFloating, propRef]);
		return (
			<>
				{isMounted && (
					<div
						ref={ref}
						style={{ ...parentStyle, ...context.floatingStyles }}
						{...getFloatingProps()}
						{...parentProps}
					>
						<div
							style={{
								...style,
								...styles,
							}}
							className={clsx(
								className,
								open ? classOptions?.open : classOptions?.close,
							)}
							{...props}
						>
							<>{children}</>
						</div>
					</div>
				)}
			</>
		);
	},
);
const DropdownClose = forwardRef<
	HTMLButtonElement,
	React.ButtonHTMLAttributes<HTMLButtonElement>
>(function PopoverClose(props, ref) {
	const { setOpen } = useDropdownContext();
	return (
		<button
			type="button"
			ref={ref}
			{...props}
			onClick={(event) => {
				props.onClick?.(event);
				setOpen(false);
			}}
		/>
	);
});
const DropdownArrow = (props: { className?: string }) => {
	const { arrowRef, context } = useDropdownContext();
	return (
		<FloatingArrow
			ref={arrowRef}
			context={context}
			width={ARROW_WIDTH}
			height={ARROW_HEIGHT}
			{...props}
		/>
	);
};
Dropdown.displayName = "Dropdown";
DropdownTrigger.displayName = "DropdownTrigger";
DropdownContent.displayName = "DropdownContent";
DropdownClose.displayName = "DropdownClose";
DropdownArrow.displayName = "DropdownArrow";
export {
	Dropdown,
	DropdownArrow,
	DropdownClose,
	DropdownContent,
	DropdownTrigger,
};
