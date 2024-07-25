import { combineComponents } from "../utils";
import { SideBarProvider } from "./sidebarContext";

export {useSideBarContext} from "./sidebarContext";

export const UIStateProvider = combineComponents(
	SideBarProvider
);
