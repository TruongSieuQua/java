"use client";
import {
  cloneElement,
  createContext,
  forwardRef,
  isValidElement,
  useContext,
  useMemo,
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
  FloatingFocusManager
} from "@floating-ui/react";
import { Button } from "../button";
import { ContextType, DropdownOptions, PopoverTriggerProps } from "./dropdown";



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

  return useMemo(
    () => ({
      open,
      setOpen,
      ...interactions,
      ...data,
      modal,
    }),
    [open, setOpen, interactions, data, modal]
  );
}

const DropdownContext = createContext<ContextType>(null);

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
}: {
  children: React.ReactNode;
} & DropdownOptions) => {
  const dropdown = useDropdown({ modal, ...restOptions });
  return (
    <DropdownContext.Provider value={dropdown}>
      {children}
    </DropdownContext.Provider>
  );
}

const DropdownTrigger = forwardRef<
  HTMLElement,
  React.HTMLProps<HTMLElement> & PopoverTriggerProps
>(function DropdownTrigger({ children, asChild = false, ...props }, propRef) {
  const { open, getReferenceProps, refs } = useDropdownContext();
  const childrenRef = (children as any).ref;
  const ref = useMergeRefs([refs.setReference, propRef, childrenRef]);

  // `asChild` allows the user to pass any element as the anchor
  if (asChild && isValidElement(children)) {
    return cloneElement(
      children,
      getReferenceProps({
        ref,
        ...props,
        ...children.props,
        "data-state": open ? "open" : "closed",
      })
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

const DropdownContent = forwardRef<
  HTMLDivElement,
  React.HTMLProps<HTMLDivElement>
>(function DropdownContent({ style, ...props }, propRef) {
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
              {props.children}
            </div>
          </FloatingFocusManager>
        </FloatingPortal>
      )}
    </>
  );
});

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

Dropdown.displayName = "Dropdown";
DropdownTrigger.displayName = "DropdownTrigger";
DropdownContent.displayName = "DropdownContent";
DropdownClose.displayName = "DropdownClose";

export { Dropdown, DropdownTrigger, DropdownContent, DropdownClose };