"use client";
import { motion } from "framer-motion";

interface ScaleAnimationProps {
  show: boolean;
  children: React.ReactNode;
}

export function ScaleAnimation({ children, show }: ScaleAnimationProps) {
  return (
    <motion.div
      initial={"hide"}
      exit={"hide"}
      animate={show ? "show" : "hide"}
      variants={{
        hide: {
          scale: 0,
          opacity: 0,
        },
        show: {
          scale: 1,
          opacity: 1,
        },
      }}
    >
      {children}
    </motion.div>
  );
}
