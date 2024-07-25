"use client";
import { createContext, useContext, useState } from "react";
import type { ReactNode } from "react";

type SideBarContextProps = {
	open: boolean;
	toggleSideBar: () => void;
}

type SideBarProviderProps = {
	children: ReactNode;
}

const SideBarContext = createContext<SideBarContextProps | undefined>(undefined);

const SideBarProvider = ({ children }: SideBarProviderProps) => {
  const [open, setOpen] = useState<boolean>(false);

  const toggleSideBar = () => {
		setOpen(!open);
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
    <SideBarContext.Provider value={{ open, toggleSideBar }}>
      {children}
    </SideBarContext.Provider>
  );
};

const useSideBarContext = () => {
	const context = useContext(SideBarContext);
	if(!context){
		throw new Error('useSideBarContext must be used within a SideBarProvider');
	}
	return context;
}

export {SideBarProvider, useSideBarContext};
