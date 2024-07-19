import { useEffect,useState } from 'react';

const useAnimation = (open: boolean) => {
  const [shouldMount, setShouldMount] = useState<boolean>(false);

  useEffect(() => {
    if (open) setShouldMount(true);
  }, [open]);

  const handleAnimationEnd = () => {
    if (!open) setShouldMount(false);
  };

  return { shouldMount, handleAnimationEnd };
};

export {useAnimation};
