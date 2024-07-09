"use client";

import type {SideBarProps} from './sidebar.d'; 
import { useSideBarContext } from "@/context";
import {useMount} from "@/hooks";
import { GoSidebarCollapse, GoSidebarExpand } from "react-icons/go";
import Link, { LinkProps } from "next/link";



export function SideBarToggle() {
  const { isSideBarOpen, toggleSideBar } = useSideBarContext();
	const Comp = asChild ? Slot : 'button';
  return (
    <Button onClick={toggleSideBar}>
      {isSideBarOpen ? <GoSidebarExpand /> : <GoSidebarCollapse />}
    </Button>
  );
}

export function SideBar({ children }: SideBarProps) {
  const { isSideBarOpen, toggleSideBar } = useSideBarContext();
	const { isMounted, handleAnimationEnd } = useMount(isSideBarOpen);
  return (
    <>
      {isMounted && (
        <>
          <div
            className="sm:absolute sm:inset-0 md:static"
            onClick={toggleSideBar}
          />
          <div className="z-50 overflow-x-hidden sm:fixed md:static">
            {
							
						}
						<motion.div
              layout
              id="sb1"
              initial="hide"
              animate={isSideBarOpen ? "show" : "hide"}
              exit={"hide"}
              variants={{
                show: { transform: "translateX(0px)" },
                hide: { transform: "translateX(-320px)" },
              }}
            >
              <div className="h-screen w-80 bg-base-100 ">
                <div className="sticky top-0 z-10 mx-2">
                  <SideBarHeader />
                </div>
                <div className="h-4" />
                <ul className="menu py-0">{children}</ul>
              </div>
            </motion.div>
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

interface SideBarLinkProps extends LinkProps {
  children: React.ReactNode;
}
export function SideBarLink({ children, ...rest }: SideBarLinkProps) {
  return (
    <li>
      <Link {...rest}>
        <div className="group flex items-center gap-2">{children}</div>
      </Link>
    </li>
  );
}
