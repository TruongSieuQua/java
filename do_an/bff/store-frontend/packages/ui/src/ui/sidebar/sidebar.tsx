"use client";

import type {SideBarLinkProps, SideBarProps, SideBarToggleProps} from './sidebar.d';
import {Slot} from "@/ui";
import {useMount} from "@/hooks";
import { useSideBarContext } from '@/context';
import { GoSidebarCollapse, GoSidebarExpand } from "react-icons/go";
import clsx from 'clsx';

export function SideBarToggle({
	asChild=false
}: SideBarToggleProps) {
  const { isOpen, toggleSideBar } = useSideBarContext();
	const Comp = asChild ? Slot : 'button';
  return (
    <Comp onClick={toggleSideBar}>
      {isOpen ? <GoSidebarExpand /> : <GoSidebarCollapse />}
    </Comp>
  );
}

export function SideBar({ children }: SideBarProps) {
  const { isOpen, toggleSideBar } = useSideBarContext();
	const { isMounted, handleAnimationEnd } = useMount(isOpen);

	return (
    <>
      {isMounted && (
        <>
          <div
            className="sm:absolute sm:inset-0 md:static"
            onClick={toggleSideBar}
          />
          <div className="overflow-x-hidden sm:fixed md:static">
            <div className={clsx("duration-500", {
							"translate-x-0": isOpen,
							"translate-x-[-320px]": !isOpen,
						})}
							onAnimationEnd={handleAnimationEnd}
						>
							<div className="h-screen w-80 bg-base-100 ">
                <div className="sticky top-0 z-10 mx-2">
                  <SideBarHeader />
                </div>
                <div className="h-4" />
                <ul className="menu py-0">{children}</ul>
              </div>
						</div>
          </div>
        </>
      )}
    </>
  );
}
function SideBarHeader() {
  return (
    <div className="flex w-full">
      <div className="flex flex-grow items-center">
        <a href="/" className="text-primary-500 text-2xl font-bold">
          Logo
        </a>
      </div>
      <div className="flex-none">
        <SideBarToggle />
      </div>
    </div>
  );
}

/*
	SideBarGroup
*/
export function SideBarGroup({ children }: SideBarProps) {
  return <li>{children}</li>;
}
export function SideBarGroupTitle({ children }: SideBarProps) {
  return (
    <h2 className="menu-title flex items-center gap-2 px-4">{children}</h2>
  );
}

export function SideBarGroupContent({ children }: SideBarProps) {
  return <ul>{children}</ul>;
}

/*
	SideBarGroupDropdown
*/
export function SideBarGroupDropdown({ children }: SideBarProps) {
  return (
    <li>
      <details className="disclosure-components">{children}</details>
    </li>
  );
}
export function SideBarGroupDropdownTrigger({ children }: SideBarProps) {
  return (
    <summary className="group font-semibold text-base-content/80">
      {children}
    </summary>
  );
}
export function SideBarGroupDropdownContent({ children }: SideBarProps) {
  return <ul>{children}</ul>;
}

export function SideBarLink({ children, ...rest }: SideBarLinkProps) {
  return (
    <li>
      <a {...rest}>
        <div className="group flex items-center gap-2">{children}</div>
      </a>
    </li>
  );
}
