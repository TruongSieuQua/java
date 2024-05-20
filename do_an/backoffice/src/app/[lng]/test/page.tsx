import { Button } from "@/components/ui/button";
import { Navigation, NavigationItem, NavigationItemContent, NavigationItemTrigger, NavigationList } from "@/components/ui/navbar";
import { PiCaretDown } from "react-icons/pi";


export default function TestPage(){
	return (
		<div className="w-screen h-screen flex mt-6 justify-center items-center">
			<div className="w-[800px] flex justify-center">
			 <Navigation>
					<NavigationList>
						<NavigationItem>
							<NavigationItemTrigger>
								<Button><div>Trigger</div><PiCaretDown className="group-data-[state=open]:-rotate-180 transition-transform duration-200 ease-in"/></Button>
							</NavigationItemTrigger>
							<NavigationItemContent>
								<div>Navigation Content 1</div>
							</NavigationItemContent>
						</NavigationItem>
						<NavigationItem>
							<NavigationItemTrigger>
								<Button>Trigger</Button>
							</NavigationItemTrigger>
							<NavigationItemContent>
								<div>Navigation Content 2</div>
							</NavigationItemContent>
						</NavigationItem>
						<NavigationItem>
							<NavigationItemTrigger>
								<Button>Trigger</Button>
							</NavigationItemTrigger>
							<NavigationItemContent>
								<div>Navigation Content 3</div>
							</NavigationItemContent>
						</NavigationItem>
					</NavigationList>
			 </Navigation>
			 <Navigation>
					<NavigationList>
						<NavigationItem>
							<NavigationItemTrigger>
								<Button>Trigger</Button>
							</NavigationItemTrigger>
							<NavigationItemContent>
								<div>Navigation Content</div>
							</NavigationItemContent>
						</NavigationItem>
					</NavigationList>
			 </Navigation>
			 <Navigation>
					<NavigationList>
						<NavigationItem>
							<NavigationItemTrigger>
								<Button>Trigger</Button>
							</NavigationItemTrigger>
							<NavigationItemContent>
								<div>Navigation Content</div>
							</NavigationItemContent>
						</NavigationItem>
					</NavigationList>
			 </Navigation>
			</div>
		</div>
	)
}
