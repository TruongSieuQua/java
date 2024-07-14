"use client";
import {
  createContext,
  useContext,
  ReactNode,
  CSSProperties,
  useState,
  HTMLAttributes,
  DetailedHTMLProps,
} from "react";
import {
  ExtendedRefs,
  autoUpdate,
  useFloating,
  useHover,
  useInteractions,
  shift,
  UseFloatingOptions,
  offset,
	safePolygon,
} from "@floating-ui/react";
import { Slot } from "@peonyui/ui";
import { createPortal } from "react-dom";
/*
 * PortalContextProvider
 */
interface PortalContextType {
  isOpen: boolean;
  refs: ExtendedRefs<Element>;
  floatingStyles: CSSProperties;
  getReferenceProps: (
    userProps?: React.HTMLProps<Element>,
  ) => Record<string, unknown>;
  getFloatingProps: (
    userProps?: React.HTMLProps<HTMLElement>,
  ) => Record<string, unknown>;
}

const PortalContext = createContext<PortalContextType | undefined>(undefined);

export const usePortalContext = () => {
  const context = useContext(PortalContext);
  if (!context) {
    throw new Error("usePortalContext must be used within a PortalProvider");
  }
  return context;
};
interface PortalProviderProps extends UseFloatingOptions {
  children: ReactNode;
}
export const PortalProvider = ({ children, ...rest }: PortalProviderProps) => {
  const [isOpen, setIsOpen] = useState(false);
  const { refs, floatingStyles, context } = useFloating({
    open: isOpen,
    onOpenChange: setIsOpen,
    middleware: [offset(10), shift()],
    whileElementsMounted: autoUpdate,
    ...rest,
  });
  const hover = useHover(context, {
		handleClose: safePolygon()
	});
  const { getReferenceProps, getFloatingProps } = useInteractions([hover]);

  return (
    <PortalContext.Provider
      value={{
        isOpen,
        refs,
        floatingStyles,
        getReferenceProps,
        getFloatingProps,
      }}
    >
      {children}
    </PortalContext.Provider>
  );
};

/*
 * PortalRoot
 */
interface PortalRootProps extends UseFloatingOptions {
  children: ReactNode;
}

export const PortalRoot = ({ children, ...rest }: PortalRootProps) => {
  return <PortalProvider {...rest}>{children}</PortalProvider>;
};

/*
 * PortalTrigger
 */
interface PortalTrigger extends React.HTMLAttributes<HTMLButtonElement> {
  asChild?: boolean;
}

// forwardRef<HTMLElement
export const PortalTrigger = ({
  asChild,
  children,
  ...rest
}: PortalTrigger) => {
  const { refs, getReferenceProps } = usePortalContext();
  const Comp = asChild ? Slot : "button";
  return (
    <Comp ref={refs.setReference} {...getReferenceProps()} {...rest}>
      {children}
    </Comp>
  );
};

/*
 * PortalContent
 */
interface PortalContentProps {
  children: React.ReactNode;
}
export const PortalContent = ({ ...rest }: PortalContentProps) => {
  const { refs, floatingStyles, getFloatingProps, isOpen } = usePortalContext();

  return (
    isOpen &&
    PortalContentWrapper({
      ref: refs.setFloating,
      style: floatingStyles,
      ...getFloatingProps(),
      ...rest,
    })
  );
};

interface PortalContentWrapperProps
  extends DetailedHTMLProps<HTMLAttributes<HTMLDivElement>, HTMLDivElement> {}
function PortalContentWrapper({
  children,
  ...rest
}: PortalContentWrapperProps) {
  const popupElement = document.getElementById("portal");
  return popupElement
    ? createPortal(<div {...rest}>{children}</div>, popupElement)
    : null;
}
