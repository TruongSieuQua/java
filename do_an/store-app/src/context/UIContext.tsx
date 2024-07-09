import {debounce} from 'lodash';
import { ReactNode, createContext, useState, useContext, useEffect } from "react";

export const UIStateProvider = ({ children }: { children: ReactNode }) => {
	return <SideBarProvider>{children}</SideBarProvider>;
}

type SideBarContextProps = {
	isSideBarOpen: boolean;
	toggleSideBar: () => void;
}
interface SideBarProviderProps {
	children: ReactNode;
}
/*
 * The SideBarContext is used to manage the state of the sidebar.
 * The SideBarProvider is used to wrap the components that need to access the sidebar state.
 * The useSideBarContext hook is used to access the sidebar state and toggle function.
*/
export const SideBarContext = createContext<SideBarContextProps|null>(null);
const SideBarProvider = ({ children }: SideBarProviderProps) => {
  const [isSideBarOpen, setSideBarOpen] = useState<boolean>(false);

  const toggleSideBar = () => {
    console.log('toggle');
		setSideBarOpen(!isSideBarOpen);
  };

  // useEffect(() => {
  //   const handleResize = debounce(() => {
	// 		console.log('resize');
  //     setSideBarFull(window.innerWidth <= 720);
  //   }, 1000);
  //   window.addEventListener('resize', handleResize);

  //   // Cleanup event listener on component unmount
  //   return () => {
  //     window.removeEventListener('resize', handleResize);
  //   };
  // }, []);

  return (
    <SideBarContext.Provider value={{ isSideBarOpen, toggleSideBar }}>
      {children}
    </SideBarContext.Provider>
  );
};
export function useSideBarContext(){
	const context = useContext(SideBarContext);
	if(context === null){
		throw new Error('useSideBarContext must be used within a SideBarProvider');
	}
	return context;
}
