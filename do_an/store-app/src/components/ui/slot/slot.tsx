"use client";
import { cloneElement, forwardRef, isValidElement, useCallback } from "react";

interface SlotProps {
  children?: React.ReactNode;
}

export const Slot = forwardRef<HTMLElement, SlotProps>(
  (props, forwardedRef) => {
    const {children, ...slotProps} = props;

    if (!isValidElement(children)) {
      return null;
    }
		return cloneElement(children, {
      ...mergeReactProps(slotProps, children.props),
      ref: combinedRef([forwardedRef, (children as any).ref]),
    } as any);
});

type AnyProps = Record<string, any>;

function mergeReactProps(parentProps: AnyProps, childProps: AnyProps) {
	// All child props should override.
	const overrideProps = {...childProps};

	for (const propName in childProps) {
		const parentPropValue = parentProps[propName];
		const childPropValue = childProps[propName];

		const isHandler = /^on[A-Z]/.test(propName);
		// If it's a handler, modify the override by composing the base handler.
		if (isHandler) {
			// Only compose the handlers if both exist.
			if (childPropValue && parentPropValue) {
				overrideProps[propName] = (...args: unknown[]) => {
					childPropValue?.(...args);
					parentPropValue?.(...args);
				};
				// Otherwise, avoid creating an unnecessary callback.
			} else if (parentPropValue) {
				overrideProps[propName] = parentPropValue;
			}
		} else if (propName === 'style') {
			overrideProps[propName] = {...parentPropValue, ...childPropValue};
		} else if (propName === 'className') {
			overrideProps[propName] = [parentPropValue, childPropValue]
				.filter(Boolean)
				.join(' ');
		}
	}

	return {...parentProps, ...overrideProps};
}
Slot.displayName = "Slot"

function setRef<TInstance>(ref: React.Ref<TInstance>, instance: TInstance) {
	if (ref instanceof Function) {
		ref(instance);
	} else if (ref != null) {
		(ref as React.MutableRefObject<TInstance>).current = instance;
	}
}

function combinedRef<TInstance>(refs: React.Ref<TInstance>[]) {
	return (instance: TInstance | null) =>
		refs.forEach((ref) => setRef(ref, instance));
}

// CREDIT https://github.com/radix-ui/primitives/blob/main/packages/react/compose-refs/src/composeRefs.tsx
/**
 * Create a ref that passes its instance to multiple refs.
 * @param refs The refs that should receive the instance.
 * @returns The combined ref.
 */
// function useMultipleRefs<TInstance>(...refs: React.Ref<TInstance>[]) {
// 	return useCallback(combinedRef(refs), refs);
// }
