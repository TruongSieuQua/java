import { useEffect, useRef, useState } from "react";

interface UseTransitionOptionsProps {
	timeUnmount?: number;
}

export function useTransition(
	open: boolean,
	{ timeUnmount = 500 }: UseTransitionOptionsProps= {},
) {
	const [enter, setEnter] = useState<boolean>(open);
	const [shouldMount, setShouldMount] = useState<boolean>(open);
	const timeoutRef = useRef<NodeJS.Timeout | null>(null);
	useEffect(() => {
		if (open) {
			setShouldMount(true);
			setEnter(false);
			timeoutRef.current = setTimeout(() => {
				setEnter(true);
			}, 30);
		} else {
			setEnter(false);
			timeoutRef.current = setTimeout(() => {
				setShouldMount(false);
			}, timeUnmount);
		}
		return () => {
			if (timeoutRef.current) {
				clearTimeout(timeoutRef.current);
			}
		};
	}, [open, timeUnmount]);
	return { enter, shouldMount };
}
