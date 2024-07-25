import {
	Dropdown,
	DropdownArrow,
	DropdownContent,
	DropdownTrigger,
} from "@repo/peonyui";
import { Link } from "@repo/peonyui";
import {
	Navigation,
	NavigationList,
	NavigationListItem,
	NavigationMenu,
	NavigationMenuArrow,
	NavigationMenuContent,
	NavigationMenuTrigger,
} from "@repo/peonyui";
import { SideBarToggle } from "@repo/peonyui";
import { Button } from "@repo/peonyui";
import { GoSidebarExpand } from "react-icons/go";
import { IoIosArrowDown } from "react-icons/io";

import { AdminSideBar } from "../../components/sidebar/Sidebar";

export default function TestPage() {
	console.log("TestPage");

	return (
		<div className="flex p-2">
			<AdminSideBar />
			<div className="relative mb-96 flex grow flex-row justify-start">
				<Navigation>
					<NavigationList>
						<NavigationListItem>
							<Dropdown
								floatingOptions={{placement: "bottom-start"}}
								transitionStylesOptions={{ duration: 1000 }}>
								<DropdownTrigger asChild={true}>
									<Button className="data-[state=open]:btn-primary">
										Trigger
									</Button>
								</DropdownTrigger>
								<DropdownContent
									className="animate__animated duration-500"
									classOptions={{
										open: "animate__bounceIn",
										close: "animate__zoomOut",
									}}
								>
									<LongContent />
									<DropdownArrow className="fill-green-300" />
								</DropdownContent>
							</Dropdown>
						</NavigationListItem>
						<NavigationListItem>
							<NavigationMenu
								hoverOptions={{ delay: {open: 200} }}
								transitionStylesOptions={{ duration: 1000 }}>
								<NavigationMenuTrigger asChild={true}>
									<Button>
										Trigger
										<IoIosArrowDown className="duration-500 group-data-[state=open]:rotate-180" />
									</Button>
								</NavigationMenuTrigger>
								<NavigationMenuContent
									className="animate__animated duration-500"
									classOptions={{
										open: "animate__bounceIn",
										close: "animate__zoomOut",
									}}
								>
									<LongContent />
									<NavigationMenuArrow className="fill-green-300" />
								</NavigationMenuContent>
							</NavigationMenu>
						</NavigationListItem>
						<NavigationListItem>
						<NavigationMenu
								hoverOptions={{ delay: {open: 200} }}
								transitionStylesOptions={{ duration: 1000 }}>
								<NavigationMenuTrigger asChild={true}>
									<Button>
										Trigger
										<IoIosArrowDown className="duration-500 group-data-[state=open]:rotate-180" />
									</Button>
								</NavigationMenuTrigger>
								<NavigationMenuContent
									className="animate__animated duration-500"
									classOptions={{
										open: "animate__bounceIn",
										close: "animate__zoomOut",
									}}
								>
									<LongContent />
									<NavigationMenuArrow className="fill-green-300" />
								</NavigationMenuContent>
							</NavigationMenu>
						</NavigationListItem>
						<NavigationListItem>
						<NavigationMenu
								hoverOptions={{ delay: {open: 200} }}
								transitionStylesOptions={{ duration: 1000 }}>
								<NavigationMenuTrigger asChild={true}>
									<Button>
										Trigger
										<IoIosArrowDown className="duration-500 group-data-[state=open]:rotate-180" />
									</Button>
								</NavigationMenuTrigger>
								<NavigationMenuContent
									className="animate__animated duration-500"
									classOptions={{
										open: "animate__bounceIn",
										close: "animate__zoomOut",
									}}
								>
									<LongContent />
									<NavigationMenuArrow className="fill-green-300" />
								</NavigationMenuContent>
							</NavigationMenu>
						</NavigationListItem>
						<NavigationListItem>
							<SideBarToggle className="btn">
								<GoSidebarExpand className="duration-500 group-data-[state=open]:rotate-180" />
							</SideBarToggle>
						</NavigationListItem>
					</NavigationList>
				</Navigation>
			</div>
		</div>
	);
}

function ShortContent() {
	return <div>Navigation Content 3</div>;
}

function LongContent() {
	return (
		<ul className="one m-0 grid list-none gap-x-[10px] bg-green-300 p-[22px] sm:w-[300px] sm:grid-cols-[0.75fr_1fr]">
			<li className="row-span-3 grid">
				<Link href={""}>
					<div
						className="focus:shadow-violet7 from-purple9 to-indigo9 flex
				size-full select-none flex-col justify-end rounded-[6px] bg-gradient-to-b p-[25px] no-underline outline-none focus:shadow-[0_0_0_2px]"
					>
						<svg
							aria-hidden
							width="38"
							height="38"
							viewBox="0 0 25 25"
							fill="white"
						>
							<path d="M12 25C7.58173 25 4 21.4183 4 17C4 12.5817 7.58173 9 12 9V25Z"></path>
							<path d="M12 0H4V8H12V0Z"></path>
							<path d="M17 8C19.2091 8 21 6.20914 21 4C21 1.79086 19.2091 0 17 0C14.7909 0 13 1.79086 13 4C13 6.20914 14.7909 8 17 8Z"></path>
						</svg>
						<div className="mb-[7px] mt-4 text-[18px] font-medium leading-[1.2] text-white">
							Radix Primitives
						</div>
						<p className="text-mauve4 text-[14px] leading-[1.3]">
							Unstyled, accessible components for React.
						</p>
					</div>
				</Link>
			</li>

			<Link href="https://stitches.dev/" title="Stitches">
				CSS-in-JS with best-in-class developer experience.
			</Link>
			<Link href="/colors" title="Colors">
				Beautiful, thought-out palettes with auto dark mode.
			</Link>
			<Link href="https://icons.radix-ui.com/" title="Icons">
				A crisp set of 15x15 icons, balanced and consistent.
			</Link>
		</ul>
	);
}
