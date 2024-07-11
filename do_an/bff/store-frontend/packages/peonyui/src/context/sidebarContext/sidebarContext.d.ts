import type { ReactNode } from "react";

type SideBarContextProps = {
	isOpen: boolean;
	toggleSideBar: () => void;
}

type SideBarProviderProps = {
	children: ReactNode;
}
