"use client";
import { debounce } from "lodash";
import {
  createContext,
  useContext,
  useState,
  useRef,
  ReactNode,
  useEffect,
	RefObject,
} from "react";
import { createPortal } from "react-dom";

/*
 * PortalContextProvider
 */
interface PortalContextType {
  triggerRef: React.RefObject<HTMLDivElement>;
  location: { top: number; left: number };
  dimensions: { width: number; height: number };
  updateTriggerInfo: (triggerRef: React.RefObject<HTMLDivElement>) => void;
	isOpen: boolean;
  openPortal: () => void;
  closePortal: () => void;
}

const PortalContext = createContext<PortalContextType | undefined>(undefined);

export const usePortalContext = () => {
  const context = useContext(PortalContext);
  if (!context) {
    throw new Error("usePortalContext must be used within a PortalProvider");
  }
  return context;
};

export const PortalProvider = ({ children }: { children: ReactNode }) => {
  const [triggerRef, setTriggerRef] = useState<RefObject<HTMLDivElement>>(useRef(null));
  const [location, setLocation] = useState({ top: 0, left: 0 });
  const [dimensions, setDimensions] = useState({ width: 0, height: 0 });
  const [isOpen, setIsOpen] = useState(false);

  const updateTriggerInfo = (ref: RefObject<HTMLDivElement>) => {

		if (ref.current) {
      const rect = ref.current.getBoundingClientRect();
			setLocation({ top: rect.bottom+12, left: rect.left });
      setDimensions({ width: rect.width, height: rect.height });
      setTriggerRef(ref);
    }
  };

  const openPortal = () => setIsOpen(true);
  const closePortal = () => setIsOpen(false);

	useEffect(() => {
    if(!isOpen) return;
		const handleResize = debounce(() => {
      console.log("resize");
			if (triggerRef.current) {
        updateTriggerInfo(triggerRef);
      }
    }, 500);

    window.addEventListener('resize', handleResize);
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, [isOpen]);

	return (
		<PortalContext.Provider value={{ triggerRef, location, dimensions, updateTriggerInfo, isOpen, openPortal, closePortal }}>
		{children}
	</PortalContext.Provider>
  );
};

/*
 * PortalRoot
 */
interface PortalRootProps {
  children: ReactNode;
}

export const PortalRoot = ({ children }: PortalRootProps) => {
	console.log("Portal Root called")

	return <PortalProvider>{children}</PortalProvider>;
};

interface PortalTriggerProps {
  children: React.ReactNode;
}

/*
 * PortalTrigger
 */
export const PortalTrigger = ({ children }: PortalTriggerProps) => {
  const triggerRef = useRef<HTMLDivElement>(null);
  const { updateTriggerInfo, openPortal, isOpen } = usePortalContext();

	console.log("Portal Trigger called")
  useEffect(() => {
    if (triggerRef.current) {
      updateTriggerInfo(triggerRef);
    }
  },[]);

	const handleClick = () => {
		if(isOpen) return;
    updateTriggerInfo(triggerRef);
    openPortal();
  };

  return <div ref={triggerRef} onClick={handleClick}>{children}</div>;
};

/*
 * PortalContent
 */
interface PortalContentProps {
  children: React.ReactNode;
}

export const PortalContent = ({ children }: PortalContentProps) => {
  const { location, isOpen, closePortal } = usePortalContext();
  const [styles, setStyles] = useState({});

	console.log("Portal Content called")

  useEffect(() => {
		if (!isOpen || typeof window === 'undefined') {
      return;
    }
		setStyles({
      position: "absolute",
      top: location.top,
      left: location.left,
      // transform: "translateX(-50%)"
    });
		console.log(location)
  }, [location]);

	if(!isOpen || typeof window === 'undefined'){
		return null;
	}

	const popupElement = document.getElementById("portal");
    if (!popupElement) return null;

    return createPortal(<div style={styles} className="bg-green-300 transition-[top,left_transform_500ms_ease]">{children} <button onClick={closePortal}>Close</button></div>, popupElement);
};
