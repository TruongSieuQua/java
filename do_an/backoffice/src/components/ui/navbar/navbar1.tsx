"use client";
import * as NavigationMenu from "@radix-ui/react-navigation-menu";
import { useEffect, useRef, useState } from "react";
import { VariantProps, tv } from "tailwind-variants";
import { PortalContent, PortalRoot, PortalTrigger } from "../portal/Portal";
import { Portal } from "@radix-ui/react-dialog";

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
  return <PortalRoot>{children}</PortalRoot>;
}

export function NavigationItemTrigger({
  children,
}: NavigationMenu.NavigationMenuTriggerProps) {
  return (
    <PortalTrigger>
      {children}
    </PortalTrigger>
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

  return (
    <PortalContent>
      {children}
    </PortalContent>
  );
}
