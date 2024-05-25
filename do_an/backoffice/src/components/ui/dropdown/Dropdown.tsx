"use client";
import {
  ExtendedRefs,
  FloatingArrow,
  FloatingContext,
  FloatingOverlay,
  ReferenceType,
  UseFloatingOptions,
  arrow,
  autoUpdate,
  flip,
  offset,
  shift,
  useFloating,
  useTransitionStyles,
} from "@floating-ui/react";
import { Slot, Slottable } from "@radix-ui/react-slot";
import {
  CSSProperties,
  HTMLAttributes,
  MutableRefObject,
  createContext,
  useContext,
  useRef,
  useState,
} from "react";
declare type Prettify<T> = {
  [K in keyof T]: T[K];
} & {};
type DropDownContextType = {
  isOpen: boolean;
  setIsOpen: (isOpen: boolean) => void;
  refs: ExtendedRefs<ReferenceType>;
  floatingStyles: CSSProperties;
  context: Prettify<FloatingContext<ReferenceType>>;
  arrowRef: MutableRefObject<SVGSVGElement | null>;
};
/*
 * *********************************************************************************
 * Global variables
 * *********************************************************************************
 */
const DropdownContext = createContext<DropDownContextType | undefined>(
  undefined,
);
const OFFSET = 12;
const ARROW_WIDTH = 8;
const ARROW_HEIGHT = 8;
const useDropdownContext = () => {
  const context = useContext(DropdownContext);
  if (!context) {
    throw new Error(
      "Dropdown compound components cannot be rendered outside the Dropdown component",
    );
  }
  return context;
};

/*
 * *********************************************************************************
 * DropdownProvider
 * *********************************************************************************
 */
interface DropDownProviderProps extends UseFloatingOptions {
  children: React.ReactNode;
}
const DropdownProvider = ({ children, ...rest }: DropDownProviderProps) => {
  const [isOpen, setIsOpen] = useState(false);
  const arrowRef = useRef(null);
  const { refs, floatingStyles, context, middlewareData } = useFloating({
    open: isOpen,
    onOpenChange: setIsOpen,
    middleware: [offset(OFFSET), flip(), shift(), arrow({ element: arrowRef })],
    whileElementsMounted: autoUpdate,
    ...rest,
  });

  return (
    <DropdownContext.Provider
      value={{
        isOpen,
        setIsOpen,
        refs,
        floatingStyles,
        context,
        arrowRef,
      }}
    >
      {children}
    </DropdownContext.Provider>
  );
};

/*
 * *********************************************************************************
 * Dropdown
 * *********************************************************************************
 */
interface DropdownProps extends DropDownProviderProps {}
export const Dropdown = ({ children, ...rest }: DropdownProps) => {
  return <DropdownProvider {...rest}>{children}</DropdownProvider>;
};

/*
 * *********************************************************************************
 *  DropdownTrigger
 * *********************************************************************************
 */
interface DropdownTriggerProps extends HTMLAttributes<HTMLButtonElement> {
  asChild?: boolean;
}
export const DropdownTrigger = ({
  asChild,
  children,
  ...rest
}: DropdownTriggerProps) => {
  const { setIsOpen, isOpen, refs } = useDropdownContext();
  const Comp = asChild ? Slot : "button";

  return (
    <Comp onClick={() => setIsOpen(!isOpen)} ref={refs.setReference} {...rest}>
      <Slottable>{children}</Slottable>
    </Comp>
  );
};

/*
 * *********************************************************************************
 * DropdownContent
 * *********************************************************************************
 */
interface DropdownContentProps extends HTMLAttributes<HTMLDivElement> {}
export const DropdownContent = ({ children, ...rest }: DropdownContentProps) => {
	const {refs, floatingStyles, context, arrowRef, isOpen, setIsOpen} = useDropdownContext();
	return (isOpen && <>
		<FloatingOverlay onClick={()=>setIsOpen(false)} />
		<div ref={refs.setFloating} style={floatingStyles} {...rest}>
				{children}
				<FloatingArrow
              ref={arrowRef}
              context={context}
              width={ARROW_WIDTH}
              height={ARROW_HEIGHT}
          />
		</div>
		</>
	);
}
