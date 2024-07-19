"use client";

import { useSideBarContext } from "@peonyui/context";
import { useTransition } from "@peonyui/hooks";
import { Slot } from "@peonyui/ui";
import clsx from "clsx";
import { forwardRef } from "react";
import { GoSidebarExpand } from "react-icons/go";

import type {
	SideBarLinkProps,
	SideBarProps,
	SideBarToggleProps,
} from "./sidebar.d";

export const SideBarToggle = forwardRef<HTMLButtonElement, SideBarToggleProps>(
	(
		{
			asChild = false,
			children,
			className,
			type = "button",
			...rest
		}: SideBarToggleProps,
		ref,
	) => {
		const { open, toggleSideBar } = useSideBarContext();
		const Comp = asChild ? Slot : "button";
		return (
			<Comp
				ref={ref}
				type={type}
				onClick={toggleSideBar}
				className={clsx("group", className)}
				data-state={open ? "open" : "closed"}
				{...rest}
			>
				{children}
			</Comp>
		);
	},
);

export function SideBar({ children }: SideBarProps) {
	const { open, toggleSideBar } = useSideBarContext();
	const { enter, shouldMount } = useTransition(open);
	return (
		<>
			{shouldMount && (
				<>
					<div
						className="sm:absolute sm:inset-0 md:static"
						onClick={toggleSideBar}
					/>
					<div className="z-50 overflow-x-hidden sm:fixed sm:left-0 md:static">
						<div
							className={clsx("duration-500 ease-in-out", {
								"translate-x-0 sm:right-full md:w-80": enter,
								"sm:-translate-x-full sm:right-0 md:-translate-x-80 md:w-0":
									!enter,
							})}
						>
							<div className="bg-base-100 h-screen sm:w-screen md:w-80">
								<div className="sticky top-0 mx-2">
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
			<div className="flex grow items-center">
				<a href="/" className="text-primary-500 text-2xl font-bold">
					Logo
				</a>
			</div>
			<div className="flex-none">
				<SideBarToggle className="btn">
					<GoSidebarExpand className="duration-500 group-data-[state=open]:rotate-180" />
				</SideBarToggle>
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
		<summary className="text-base-content/80 group font-semibold">
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
