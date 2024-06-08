"use client";
import * as React from "react";
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
  UseFloatingOptions,
} from "@floating-ui/react";
import { AnimatePresence } from "framer-motion";
import { ScaleAnimation } from "@/components/animations";
import { Button } from "../button";

interface DropdownOptions extends UseFloatingOptions {
  initialOpen?: boolean;
  modal?: boolean;
  open?: boolean;
  onOpenChange?: (open: boolean) => void;
}

export function useDropdown({
  initialOpen = false,
  placement = "bottom",
  modal,
  open: controlledOpen,
  onOpenChange: setControlledOpen,
}: DropdownOptions = {}) {
  const [uncontrolledOpen, setUncontrolledOpen] = React.useState(initialOpen);

  const open = controlledOpen ?? uncontrolledOpen;
  const setOpen = setControlledOpen ?? setUncontrolledOpen;

  const data = useFloating({
    placement,
    open,
    onOpenChange: setOpen,
    whileElementsMounted: autoUpdate,
    middleware: [
      offset(5),
      flip({
        crossAxis: placement.includes("-"),
        fallbackAxisSideDirection: "end",
        padding: 5,
      }),
      shift({ padding: 5 }),
    ],
  });

  const context = data.context;

  const click = useClick(context, {
    enabled: controlledOpen == null,
  });
  const dismiss = useDismiss(context);
  const role = useRole(context);

  const interactions = useInteractions([click, dismiss, role]);

  return React.useMemo(
    () => ({
      open,
      setOpen,
      ...interactions,
      ...data,
      modal,
    }),
    [open, setOpen, interactions, data, modal],
  );
}

type ContextType = ReturnType<typeof useDropdown> | null;

const DropdownContext = React.createContext<ContextType>(null);

export const useDropdownContext = () => {
  const context = React.useContext(DropdownContext);

  if (context == null) {
    throw new Error("Dropdown components must be wrapped in <Dropdown />");
  }

  return context;
};

export function Dropdown({
  children,
  modal = false,
  ...restOptions
}: {
  children: React.ReactNode;
} & DropdownOptions) {
  const dropdown = useDropdown({ modal, ...restOptions });
  return (
    <DropdownContext.Provider value={dropdown}>
      {children}
    </DropdownContext.Provider>
  );
}

interface PopoverTriggerProps {
  children: React.ReactNode;
  asChild?: boolean;
}

export const DropdownTrigger = React.forwardRef<
  HTMLElement,
  React.HTMLProps<HTMLElement> & PopoverTriggerProps
>(function DropdownTrigger({ children, asChild = false, ...props }, propRef) {
  const { open, getReferenceProps, refs } = useDropdownContext();
  const childrenRef = (children as any).ref;
  const ref = useMergeRefs([refs.setReference, propRef, childrenRef]);

  // `asChild` allows the user to pass any element as the anchor
  if (asChild && React.isValidElement(children)) {
    return React.cloneElement(
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
      ref={ref}
      color={open ? "primary" : undefined}
      data-state={open ? "open" : "closed"}
      {...getReferenceProps(props)}
    >
      {children}
    </Button>
  );
});

export const DropdownContent = React.forwardRef<
  HTMLDivElement,
  React.HTMLProps<HTMLDivElement>
>(function DropdownContent({ style, ...props }, propRef) {
  const { open, context: floatingContext, ...context } = useDropdownContext();
  const ref = useMergeRefs([context.refs.setFloating, propRef]);

  return (
    <AnimatePresence>
      {open && (
        <FloatingPortal>
          <FloatingFocusManager context={floatingContext} modal={context.modal}>
            <div
              ref={ref}
              style={{ ...context.floatingStyles, ...style }}
              {...context.getFloatingProps(props)}
            >
              <ScaleAnimation show={open}>{props.children}</ScaleAnimation>
            </div>
          </FloatingFocusManager>
        </FloatingPortal>
      )}
    </AnimatePresence>
  );
});

export const DropdownClose = React.forwardRef<
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
