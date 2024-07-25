"use client";
import type {
	ArrowOptions,
	FlipOptions,
	OffsetOptions,
	ShiftOptions,
	UseFloatingOptions,
	UseHoverProps,
	UseTransitionStylesProps,
} from "@floating-ui/react";
import {
	arrow,
	flip,
	FloatingArrow,
	offset,
	safePolygon,
	shift,
	useFloating,
	useHover,
	useInteractions,
	useMergeRefs,
	useTransitionStyles,
} from "@floating-ui/react";
import clsx from "clsx";
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

interface NavigationProps extends HTMLAttributes<HTMLDivElement> {}

interface NavigationListProps extends HTMLAttributes<HTMLUListElement> {}

interface NavigationListItemProps extends HTMLAttributes<HTMLLIElement> {}

interface NavigationMenuOptions {
	initialOpen?: boolean;
	floatingOptions?: UseFloatingOptions;
	offSetOptions?: OffsetOptions;
	flipOptions?: FlipOptions;
	shiftOptions?: ShiftOptions;
	arrowOptions?: ArrowOptions;
	hoverOptions?: UseHoverProps;
	transitionStylesOptions?: UseTransitionStylesProps;
}

interface NavigationMenuProps extends NavigationMenuOptions {
	children: ReactNode;
}

interface NavigationMenuTriggerProps extends HTMLAttributes<HTMLButtonElement> {
	children: ReactNode;
	asChild?: boolean;
}

interface NavigationMenuContentProps extends HTMLAttributes<HTMLDivElement> {
	parentProps?: HTMLProps<HTMLDivElement>;
	classOptions?: {
		open?: string;
		close?: string;
	};
}

interface NavigationMenuContentProps extends HTMLAttributes<HTMLDivElement> {}

const Navigation = ({ children, ...rest }: NavigationProps) => {
	return <div {...rest}>{children}</div>;
};

const NavigationList = ({
	children,
	className,
	...rest
}: NavigationListProps) => {
	return (
		<ul
			className={clsx(
				"flex flex-row gap-1.5 rounded-lg border border-gray-200 bg-white p-2 shadow-md",
				className,
			)}
			{...rest}
		>
			{children}
		</ul>
	);
};

const NavigationListItem = ({ children, ...rest }: NavigationListItemProps) => {
	return <li {...rest}>{children}</li>;
};

/* *********************************************************************************
 * Global variables
 * *********************************************************************************
 */
const GAP = 2;
const ARROW_WIDTH = 8;
const ARROW_HEIGHT = 8;

function useNavigationMenu({
	floatingOptions,
	offSetOptions = GAP + ARROW_HEIGHT,
	flipOptions,
	shiftOptions,
	hoverOptions,
	transitionStylesOptions,
}: NavigationMenuOptions) {
	const [open, setIsOpen] = useState<boolean>(false);
	const arrowRef = useRef(null);
	const data = useFloating({
		...floatingOptions,
		open: open,
		onOpenChange: setIsOpen,
		middleware: [
			offset(offSetOptions),
			flip(flipOptions),
			shift(shiftOptions),
			arrow({ element: arrowRef }),
		],
	});
	const context = data.context;
	const hover = useHover(context, {
		move: false,
		handleClose: safePolygon(),
		...hoverOptions,
	});
	const interactions = useInteractions([hover]);
	const { isMounted, styles } = useTransitionStyles(
		context,
		transitionStylesOptions,
	);
	return useMemo(
		() => ({
			isMounted,
			styles,
			open,
			setIsOpen,
			...interactions,
			...data,
			arrowRef,
		}),
		[isMounted, open, interactions, data, styles, arrowRef],
	);
}
type NavigationMenuContextType = ReturnType<typeof useNavigationMenu> | null;
const NavigationMenuContext = createContext<
	NavigationMenuContextType | undefined
>(undefined);
const useNaviagtionContext = () => {
	const context = useContext(NavigationMenuContext);
	if (context == null) {
		throw new Error(
			"NavigationMenu components must be wrapped in <NavigationMenu />",
		);
	}
	return context;
};

/*
 * *********************************************************************************
 *  NavigationMenu
 * *********************************************************************************
 */
const NavigationMenu = ({ children, ...options }: NavigationMenuProps) => {
	const navigationMenu = useNavigationMenu(options);
	return (
		<NavigationMenuContext.Provider value={navigationMenu}>
			{children}
		</NavigationMenuContext.Provider>
	);
};

/*
 * *********************************************************************************
 * NavigationMenuTrigger
 * *********************************************************************************
 */
const NavigationMenuTrigger = forwardRef<
	HTMLElement,
	NavigationMenuTriggerProps
>(({ asChild, children, className, ...props }, propRef) => {
	const { open, refs } = useNaviagtionContext();
	const childrenRef = (children as any).ref;
	const ref = useMergeRefs([refs.setReference, propRef, childrenRef]);
	const Comp = asChild ? Slot : "button";
	return (
		<Comp
			type="button"
			ref={ref}
			{...props}
			className={clsx("group", className)}
			data-state={open ? "open" : "closed"}
		>
			{children}
		</Comp>
	);
});

/*
 * *********************************************************************************
 * NavigationMenuPortal
 * *********************************************************************************
 */
const NavigationMenuContent = forwardRef<
	HTMLDivElement,
	NavigationMenuContentProps
>(({ children, style, className, classOptions, ...props }, propRef) => {
	const { isMounted, styles, open, getFloatingProps, floatingStyles, refs } =
		useNaviagtionContext();
	const ref = useMergeRefs([refs.setFloating, propRef]);
	return (
		<>
			{isMounted && (
				<div ref={ref} style={floatingStyles} {...getFloatingProps()}>
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
						{children}
					</div>
				</div>
			)}
		</>
	);
});

/*
 * *********************************************************************************
 * NavigationMenuArrow
 * *********************************************************************************
 */
const NavigationMenuArrow = (props: { className?: string }) => {
	const { arrowRef, context } = useNaviagtionContext();
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

Navigation.displayName = "Navigation";
NavigationList.displayName = "NavigationList";
NavigationListItem.displayName = "NavigationListItem";
NavigationMenu.displayName = "NavigationMenu";
NavigationMenuTrigger.displayName = "NavigationMenuTrigger";
NavigationMenuContent.displayName = "NavigationMenuContent";
NavigationMenuArrow.displayName = "NavigationMenuArrow";

export {
	Navigation,
	NavigationList,
	NavigationListItem,
	NavigationMenu,
	NavigationMenuArrow,
	NavigationMenuContent,
	NavigationMenuTrigger,
};
