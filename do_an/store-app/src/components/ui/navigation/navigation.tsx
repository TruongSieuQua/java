import type {
	NavigationProps,
	NavigationListProps,
	NavigationListItemProps,
	NavigationMenuProps,
	NavigationMenuTriggerProps,
	NavigationMenuPortalProps,
	NavigationMenuContentProps,
} from "./navigation.d";
import {
	createContext,
	useContext,
	useState,
	useRef,
	useMemo,
	forwardRef,
	useEffect,
} from "react";
import {
	autoUpdate,
	useFloating,
	useHover,
	useInteractions,
	UseFloatingOptions,
	offset,
	safePolygon,
	FloatingArrow,
	arrow,
	shift,
	flip,
	useFocus,
	useDismiss,
	useRole,
	useMergeRefs,
	FloatingPortal,
} from "@floating-ui/react";
import clsx from "clsx";
import { Slot } from "../slot";

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
				"flex flex-row gap-1.5 p-2 bg-white rounded-lg shadow-md border border-gray-200 z-10",
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
const GAP = 3;
const ARROW_WIDTH = 7;
const ARROW_HEIGHT = 7;

function useNavigationMenu({ ...props }: UseFloatingOptions) {
	const [open, setIsOpen] = useState<boolean>(false);

	const arrowRef = useRef(null);
	const data = useFloating({
		open: open,
		onOpenChange: setIsOpen,
		middleware: [
			offset(ARROW_HEIGHT + GAP),
			flip({ crossAxis: false }),
			shift(),
			arrow({ element: arrowRef }),
		],
		whileElementsMounted: autoUpdate,
		...props,
	});
	const context = data.context;
	const hover = useHover(context, {
		move: false,
		handleClose: safePolygon(),
	});
	const focus = useFocus(context, {});
	const dismiss = useDismiss(context);
	const role = useRole(context, { role: "menu" });
	const interactions = useInteractions([hover, focus, dismiss, role]);

	return useMemo(
		() => ({
			open,
			setIsOpen,
			...interactions,
			...data,
			arrowRef,
		}),
		[open, setIsOpen, interactions, data, arrowRef],
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
  const { open, refs, getReferenceProps } = useNaviagtionContext();
	const childrenRef = (children as any).ref;
  const ref = useMergeRefs([refs.setReference, propRef, childrenRef]);
	const Comp = asChild ? Slot : 'button';

  return (
    <Comp
			type="button"
			{...getReferenceProps({
        ref,
				className: clsx("group", className, children.props.className),
        ...props,
        ...children.props,
        "data-state": open ? "open" : "closed",
      })}
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
const NavigationMenuPortal = forwardRef<
	HTMLDivElement,
	NavigationMenuPortalProps
>(({ children, arrowClassName, style, ...props }, propRef) => {
	const { open, getFloatingProps, floatingStyles, refs } =
		useNaviagtionContext();
	const ref = useMergeRefs([refs.setFloating, propRef]);
	const [shouldRender, setRender] = useState(false);

	useEffect(() => {
		if (open) setRender(true);
	}, [open]);

	const handleonAnimationEnd = () => {
		if (!open) setRender(false);
	};

	return (
		<>
			{shouldRender && (
				<FloatingPortal>
					<div
						ref={ref}
						{...getFloatingProps(props)}
						style={{ ...floatingStyles, ...style }}
						{...props}
					>
						{shouldRender && (
							<div
								className={clsx("animate-duration-500", {
									"animate-jump-in": open,
									"animate-jump-out": !open,
								})}
								onAnimationEnd={handleonAnimationEnd}
							>
								{children}
							</div>
						)}
					</div>
				</FloatingPortal>
			)}
		</>
	);
});

/*
 * *********************************************************************************
 * NavigationMenuContent
 * *********************************************************************************
 */
const NavigationMenuContent = ({
	children,
	className,
	...rest
}: NavigationMenuContentProps) => {
	return (
		<div className={clsx("", className)} {...rest}>{children}</div>
	);
};

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
NavigationMenuPortal.displayName = "NavigationMenuPortal";
NavigationMenuContent.displayName = "NavigationMenuContent";
NavigationMenuArrow.displayName = "NavigationMenuArrow";

export {
	Navigation,
	NavigationList,
	NavigationListItem,
	NavigationMenu,
	NavigationMenuTrigger,
	NavigationMenuPortal,
	NavigationMenuContent,
	NavigationMenuArrow,
};
