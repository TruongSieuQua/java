import { useState, useEffect } from 'react';

const useAnimation = (open: boolean) => {
  const [isMounted, setMounted] = useState<boolean>(false);

  useEffect(() => {
    if (open) setMounted(true);
  }, [open]);

  const handleAnimationEnd = () => {
    if (!open) setMounted(false);
  };

  return { isMounted, handleAnimationEnd };
};

export {useAnimation};
