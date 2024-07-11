"use client";
import type {
	ContextType,
	DropdownContentProps,
	DropdownOptions,
	DropdownProps,
	DropdownTriggerProps,
} from "./dropdown";
import { Slot } from "@peonyui/ui";
import {
	createContext,
	forwardRef,
	useContext,
	useMemo,
	useRef,
	useState,
} from "react";
import {
	useFloating,
	autoUpdate,
	offset,
	flip,
	shift,
	useClick,
	useDismiss,
	useRole,
	useInteractions,
	useMergeRefs,
	FloatingPortal,
	FloatingFocusManager,
	arrow,
	FloatingArrow,
} from "@floating-ui/react";
import clsx from "clsx";

/* *********************************************************************************
 * Global variables
 * *********************************************************************************
 */
const GAP = 3;
const ARROW_WIDTH = 7;
const ARROW_HEIGHT = 7;
const DropdownContext = createContext<ContextType>(null);

function useDropdown({
	initialOpen = false,
	placement = "bottom",
	modal,
	open: controlledOpen,
	onOpenChange: setControlledOpen,
}: DropdownOptions = {}) {
	const [uncontrolledOpen, setUncontrolledOpen] = useState(initialOpen);
	const open = controlledOpen ?? uncontrolledOpen;
	const setOpen = setControlledOpen ?? setUncontrolledOpen;
	const arrowRef = useRef(null);

	const data = useFloating({
		placement,
		open,
		onOpenChange: setOpen,
		whileElementsMounted: autoUpdate,
		middleware: [
			offset(GAP+ARROW_HEIGHT),
			flip({
				crossAxis: placement.includes("-"),
				fallbackAxisSideDirection: "end",
				padding: 5,
			}),
			shift({ padding: 5 }),
			arrow({ element: arrowRef }),
		],
	});

	const context = data.context;

	const click = useClick(context, {
		enabled: controlledOpen == null,
	});
	const dismiss = useDismiss(context);
	const role = useRole(context);

	const interactions = useInteractions([click, dismiss, role]);

	return useMemo(
		() => ({
			open,
			setOpen,
			...interactions,
			...data,
			modal,
			arrowRef,
		}),
		[open, setOpen, interactions, data, modal, arrowRef],
	);
}


const useDropdownContext = () => {
	const context = useContext(DropdownContext);

	if (context == null) {
		throw new Error("Dropdown components must be wrapped in <Dropdown />");
	}

	return context;
};

const Dropdown = ({
	children,
	modal = false,
	...restOptions
}: DropdownProps) => {
	const dropdown = useDropdown({ modal, ...restOptions });
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
				{...getReferenceProps({
					ref,
					...props,
					...children.props,
					className: clsx("group", className),
					"data-state": open ? "open" : "closed",
				})}
			>
				{children}
			</Comp>
		);
	},
);

const DropdownPortal = forwardRef<
HTMLDivElement,
React.HTMLProps<HTMLDivElement>
>(function DropdownContent({ children, style, ...props }, propRef) {
const { open, context: floatingContext, ...context } = useDropdownContext();
const ref = useMergeRefs([context.refs.setFloating, propRef]);

return (<>
		{open && (
			<FloatingPortal>
				<FloatingFocusManager context={floatingContext} modal={context.modal}>
					<div
						ref={ref}
						style={{ ...context.floatingStyles, ...style }}
						{...context.getFloatingProps(props)}
					>
						{children}
					</div>
				</FloatingFocusManager>
			</FloatingPortal>
		)}
	</>
);
});

const DropdownContent = ({children, ...props}: DropdownContentProps) => {
	return (<div {...props}>
		<>{children}</>
	</div>
	);
};

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
DropdownPortal.displayName = "DropdownPortal";
DropdownContent.displayName = "DropdownContent";
DropdownClose.displayName = "DropdownClose";
DropdownArrow.displayName = "DropdownArrow";

export { Dropdown, DropdownTrigger, DropdownPortal, DropdownContent, DropdownClose, DropdownArrow };
