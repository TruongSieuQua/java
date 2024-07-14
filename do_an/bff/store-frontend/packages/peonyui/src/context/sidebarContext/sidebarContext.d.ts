import type { ReactNode } from "react";

type SideBarContextProps = {
	open: boolean;
	toggleSideBar: () => void;
}

type SideBarProviderProps = {
	children: ReactNode;
}
