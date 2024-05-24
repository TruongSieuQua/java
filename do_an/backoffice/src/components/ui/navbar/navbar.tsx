"use client";
import * as NavigationMenu from "@radix-ui/react-navigation-menu";
import { useEffect, useRef, useState } from "react";
import { VariantProps, tv } from "tailwind-variants";

// sm:w-[var(--radix-navigation-menu-viewport-width)]
export function Navigation({ children }: NavigationMenu.NavigationMenuProps) {
  return (
    <NavigationMenu.Root className="relative z-[1] flex justify-center">
      {children}
    </NavigationMenu.Root>
  );
}

export function NavigationList({
  children,
}: NavigationMenu.NavigationMenuProps) {
  return (
    <NavigationMenu.List className="center shadow-blackA4 m-0 flex list-none rounded-[6px] bg-white p-1 shadow-[0_2px_10px]">
      {children}
      <NavigationMenu.Indicator className="data-[state=visible]:animate-fadeIn data-[state=hidden]:animate-fadeOut top-full z-[1] flex h-[10px] items-end justify-center overflow-hidden transition-[width,transform_250ms_ease]">
        <div className="relative top-[70%] h-[10px] w-[10px] rotate-[45deg] rounded-tl-[2px] bg-white" />
      </NavigationMenu.Indicator>
    </NavigationMenu.List>
  );
}

export function NavigationItem({
  children,
}: NavigationMenu.NavigationMenuItemProps) {
  return <NavigationMenu.Item className="relative">{children}</NavigationMenu.Item>;
}

export function NavigationItemTrigger({
  children,
}: NavigationMenu.NavigationMenuTriggerProps) {
  return (
    <NavigationMenu.Trigger
      asChild
      className="flex items-center justify-between"
    >
      {children}
    </NavigationMenu.Trigger>
  );
}

const content = tv({
	base: "absolute sm:w-auto bg-green-200",
	variants: {
		placement: {
			left: "left-0",
			center: "left-1/2 -translate-x-1/2",
			right: "right-0"
		}
	},
	defaultVariants: {}
})
export function NavigationItemContent({
  children,
	className
}: NavigationMenu.NavigationMenuContentProps & VariantProps<typeof content>) {
  const [top, setTop] = useState(0);
  const [left, setLeft] = useState(0);
  const dropdownRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    const adjustDropdownPosition = () => {
      if (!dropdownRef.current) return;

      const rect = dropdownRef.current.getBoundingClientRect();
      const viewportWidth = window.innerWidth;
      const viewportHeight = window.innerHeight;

      console.log(rect)
      console.log(viewportWidth)
      console.log(viewportHeight)

      let newTop = rect.top;
      let newLeft = rect.left;

      // Adjust if dropdown is out of viewport on the right side
      if (rect.right > viewportWidth) {
        newLeft -= (rect.right - viewportWidth);
      }

      // Adjust if dropdown is out of viewport on the left side
      if (rect.left < 0) {
        newLeft = 0;
      }

      // Adjust if dropdown is out of viewport on the bottom side
      if (rect.bottom > viewportHeight) {
        newTop -= (rect.bottom - viewportHeight);
      }

      // Adjust if dropdown is out of viewport on the top side
      if (rect.top < 0) {
        newTop = 0;
      }

      setTop(newTop);
      setLeft(newLeft);
    };

    adjustDropdownPosition();
    window.addEventListener('resize', adjustDropdownPosition);

    return () => {
      window.removeEventListener('resize', adjustDropdownPosition);
    };
  }, [dropdownRef]);

  return (
    <NavigationMenu.Content
      ref={dropdownRef}
      style={{ top: `${top}px`, left: `${left}px`}}
      className={`absolute top-full left-1/2 -translate-x-1/2 sm:w-auto bg-green-200 transition-[left,top,transform_500ms_ease] ${className}`}
    >
      {children}
    </NavigationMenu.Content>
  );
}
