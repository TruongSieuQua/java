"use client";
import {
  createContext,
  useContext,
  ReactNode,
  CSSProperties,
  useState,
  HTMLAttributes,
  DetailedHTMLProps,
  useRef,
  MutableRefObject,
} from "react";
import {
  ExtendedRefs,
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
  ReferenceType,
  FloatingContext,
  FloatingArrowProps,
} from "@floating-ui/react";
import { Slot, Slottable } from "@radix-ui/react-slot";
import { createPortal } from "react-dom";
import clsx from "clsx";

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
        "menu menu-vertical rounded-box lg:menu-horizontal",
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


declare type Prettify<T> = {
  [K in keyof T]: T[K];
} & {};
interface NavigationMenuContextType {
  isOpen: boolean;
  refs: ExtendedRefs<ReferenceType>;
  floatingStyles: CSSProperties;
  context: Prettify<FloatingContext<ReferenceType>>;
  getReferenceProps: (
    userProps?: React.HTMLProps<Element>,
  ) => Record<string, unknown>;
  getFloatingProps: (
    userProps?: React.HTMLProps<HTMLElement>,
  ) => Record<string, unknown>;
  arrowRef: MutableRefObject<SVGSVGElement | null>;
}

/* *********************************************************************************
 * Global variables
 * *********************************************************************************
 */
const NavigationMenuContext = createContext<NavigationMenuContextType | undefined>(undefined);
const OFFSET = 10;
const ARROW_WIDTH = 10;
const ARROW_HEIGHT = 10;

const useNavigationMenuContext = () => {
  const context = useContext(NavigationMenuContext);
  if (!context) {
    throw new Error("useMenuContext must be used within a MenuProvider");
  }
  return context;
};

/*
 * *********************************************************************************
 * MenuProvider
 * *********************************************************************************
 */
interface MenuProviderProps extends UseFloatingOptions {
  children: ReactNode;
}
const MenuProvider = ({ children, ...rest }: MenuProviderProps) => {
  const [isOpen, setIsOpen] = useState(false);
  const arrowRef = useRef(null);
  const { refs, floatingStyles, context } = useFloating({
    open: isOpen,
    onOpenChange: setIsOpen,
    middleware: [
      offset(OFFSET),
      shift(),
      arrow({element: arrowRef,})
    ],
    whileElementsMounted: autoUpdate,
    ...rest,
  });
  const hover = useHover(context, {
    handleClose: safePolygon()
  });
  const { getReferenceProps, getFloatingProps } = useInteractions([hover]);

  return (
    <NavigationMenuContext.Provider
      value={{
        isOpen,
        refs,
        floatingStyles,
        context,
        getReferenceProps,
        getFloatingProps,
        arrowRef,
      }}
    >
      {children}
    </NavigationMenuContext.Provider>
  );
};

/*
 * *********************************************************************************
 *  Menu
 * *********************************************************************************
 */
interface NavigationMenuProps extends MenuProviderProps {}
export const NavigationMenu = ({ children, ...rest }: NavigationMenuProps) => {
  return <MenuProvider {...rest}>{children}</MenuProvider>;
};

/*
 * *********************************************************************************
 * MenuTrigger
 * *********************************************************************************
 */
interface NavigationMenuTriggerProps extends HTMLAttributes<HTMLButtonElement> {
  asChild?: boolean;
}
export const NavigationMenuTrigger = ({
  asChild,
  children,
  ...rest
}: NavigationMenuTriggerProps) => {
  const { refs, getReferenceProps } = useNavigationMenuContext();
  const Comp = asChild ? Slot : "button";
  return (
    <Comp ref={refs.setReference} {...getReferenceProps()} {...rest}>
      <Slottable>{children}</Slottable>
    </Comp>
  );
};

/*
 * *********************************************************************************
 * MenuContent
 * *********************************************************************************
 */
interface NavigationMenuContentProps extends HTMLAttributes<HTMLDivElement> {}
export const NavigationMenuContent = ({ ...rest }: NavigationMenuContentProps) => {
  const { refs, floatingStyles, getFloatingProps, isOpen, arrowRef, context } =
    useNavigationMenuContext();

  return (
    isOpen &&
    PortalContentWrapper({
      ref: refs.setFloating,
      style: floatingStyles,
      context,
      ...getFloatingProps(),
      arrowRef,
      ...rest,
    })
  );
};

interface PortalContentWrapperProps
  extends DetailedHTMLProps<HTMLAttributes<HTMLDivElement>, HTMLDivElement> {
  context: Prettify<FloatingContext<ReferenceType>>;
  arrowRef: MutableRefObject<SVGSVGElement | null>;
  arrowClassName?: string;
}
function PortalContentWrapper({
  children,
  ...rest
}: PortalContentWrapperProps) {
  const popupElement = document.getElementById("mn");
  return popupElement
    ? createPortal(<div {...rest}>{children}</div>, popupElement)
    : null;
}

/*
 * *********************************************************************************
 * MenuArrow
 * *********************************************************************************
 */
interface ArrowProps extends Omit<FloatingArrowProps, "context"> {}
export const Arrow = ({
  width = ARROW_WIDTH,
  height = ARROW_HEIGHT,
  ...rest
}: ArrowProps) => {
  const { arrowRef, context } = useNavigationMenuContext();
  return <FloatingArrow ref={arrowRef} context={context} {...rest} />;
};
