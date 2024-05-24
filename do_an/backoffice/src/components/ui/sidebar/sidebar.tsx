"use client";

import { useSideBarContext } from "@/context";
import { GoSidebarCollapse, GoSidebarExpand } from "react-icons/go";
import { Button } from "@/components/ui/button";
import clsx from "clsx";
import Link, { LinkProps } from "next/link";

interface SideBarProps {
	children: React.ReactNode;
}

export function SideBarToggle() {
  const { isSideBarOpen, toggleSideBar } = useSideBarContext();

  return (
    <Button onClick={toggleSideBar}>
      {isSideBarOpen ? <GoSidebarExpand /> : <GoSidebarCollapse />}
    </Button>
  );
}

export function SideBar({children}: SideBarProps) {
  const { isSideBarOpen } = useSideBarContext();
  return (
    <>
      {isSideBarOpen && (
        <div
          className={clsx(
            "z-50 h-screen overflow-x-hidden bg-base-100 sm:fixed sm:inset-0 md:static md:w-80",
          )}
        >
          <div className="grid-row-2 sticky top-0 z-10 grid w-full gap-y-2 bg-base-100 bg-opacity-90 px-2 py-3 backdrop-blur ">
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
          </div>
          <div className="h-4"></div>
          <ul className="menu px-2 py-0">{children}</ul>
        </div>
      )}
    </>
  );
}
/*
	SideBarGroup
*/
export function SideBarGroup({children}: SideBarProps) {
	return <li>
			{children}
		</li>;
}
export function SideBarGroupTitle({children}: SideBarProps) {
	return <h2 className="menu-title flex items-center gap-2 px-4">{children}</h2>;
}

export function SideBarGroupContent({children}: SideBarProps) {
	return <ul>{children}</ul>;
}

/*
	SideBarGroupDropdown
*/
export function SideBarGroupDropdown({children}: SideBarProps) {
	return <li>
		<details className="disclosure-components">
				{children}
		</details>
	</li>
}
export function SideBarGroupDropdownTrigger({children}: SideBarProps) {
	return <summary className="group text-base-content/40 font-bold">{children}</summary>;
}
export function SideBarGroupDropdownContent({children}: SideBarProps) {
	return <ul>{children}</ul>;
}

interface SideBarLinkProps extends LinkProps {
	children: React.ReactNode;
}
export function SideBarLink({children, ...rest}: SideBarLinkProps) {
	return <li><Link {...rest}>
		<div className="group flex items-center gap-2">{children}</div>
		</Link>
	</li>;
}
