"use client";
import {
  createContext,
  useContext,
  ReactNode,
  useState,
  HTMLAttributes,
  useRef,
  useMemo,
  forwardRef,
  isValidElement,
  cloneElement,
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
  FloatingArrowProps,
} from "@floating-ui/react";
import clsx from "clsx";
import { Button } from "../button";
import { IoIosArrowDown } from "react-icons/io";
import { ScaleAnimation } from "@/components/animations";
import { AnimatePresence } from "framer-motion";

interface Navigation extends HTMLAttributes<HTMLDivElement> {}
export function Navigation({ children, ...rest }: Navigation) {
  return <div {...rest}>{children}</div>;
}

interface NavigationList extends HTMLAttributes<HTMLUListElement> {}
export function NavigationList({
  children,
  className,
  ...rest
}: NavigationList) {
  return (
    <ul
      className={clsx(
        // "menu menu-vertical rounded-box lg:menu-horizontal",
        className,
      )}
      {...rest}
    >
      {children}
    </ul>
  );
}

interface NavigationListItem extends HTMLAttributes<HTMLLIElement> {}
export function NavigationListItem({ children, ...rest }: NavigationListItem) {
  return <li {...rest}>{children}</li>;
}

/* *********************************************************************************
 * Global variables
 * *********************************************************************************
 */
const GAP = 2;
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
interface NavigationMenuProps extends UseFloatingOptions {
  children: ReactNode;
}
export const NavigationMenu = ({
  children,
  ...options
}: NavigationMenuProps) => {
  const navigationMenu = useNavigationMenu(options);

  return (
    <NavigationMenuContext.Provider value={navigationMenu}>
      {children}
    </NavigationMenuContext.Provider>
  );
};

/*
 * *********************************************************************************
 * MenuTrigger
 * *********************************************************************************
 */
interface NavigationMenuTriggerProps extends HTMLAttributes<HTMLButtonElement> {
  asChild?: boolean;
}
export const NavigationMenuTrigger = forwardRef<
  HTMLElement,
  NavigationMenuTriggerProps
>(({ asChild, children, ...props }: NavigationMenuTriggerProps, propRef) => {
  if (!children) {
    throw new Error("A ref must be provided to the child of a MenuTrigger");
  }

  const { open, refs, getReferenceProps } = useNaviagtionContext();
  const childrenRef = (children as any).ref;
  const ref = useMergeRefs([refs.setReference, propRef, childrenRef]);

  if (asChild && isValidElement(children)) {
    return cloneElement(
      children,
      getReferenceProps({
        ref,
        ...props,
        ...children.props,
        "data-state": open ? "open" : "closed",
      }),
    );
  }

  return (
    <Button
      className="group"
      ref={ref}
      {...getReferenceProps(props)}
      data-state={open ? "open" : "closed"}
    >
      {children}
      <IoIosArrowDown className="transition-[rotate_250ms_ease] group-data-[state=open]:rotate-180" />
    </Button>
  );
});

NavigationMenuTrigger.displayName = "NavigationMenuTrigger";
/*
 * *********************************************************************************
 * MenuContent
 * *********************************************************************************
 */
interface NavigationMenuContentProps extends HTMLAttributes<HTMLDivElement> {
  arrowClassName?: string;
}
export const NavigationMenuContent = forwardRef<
  HTMLDivElement,
  NavigationMenuContentProps
>(
  (
    { children, arrowClassName, style, ...props }: NavigationMenuContentProps,
    propRef,
  ) => {
    const { open, getFloatingProps, floatingStyles, arrowRef, context, refs } =
      useNaviagtionContext();
    const ref = useMergeRefs([refs.setFloating, propRef]);

    return (
      <AnimatePresence>
        {open && (
          <FloatingPortal>
            <div
              ref={ref}
              {...getFloatingProps(props)}
              style={{ ...floatingStyles, ...style }}
              {...props}
            >
              <ScaleAnimation show={open}>{children}</ScaleAnimation>
            </div>
          </FloatingPortal>
        )}
      </AnimatePresence>
    );
  },
);
NavigationMenuContent.displayName = "NavigationMenuContent";

export function NavigationMenuArrow(props: { className: string }) {
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
}
