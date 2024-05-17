"use client";

import { usePathname, useRouter } from "next/navigation";

interface LoadingTextProps {
  text: string;
}

export function LoadingText({ text }: LoadingTextProps) {

	return (
    <div className="z-50 absolute bg-base-100 inset-0 w-screen h-screen flex justify-center items-center">
      <div className="classic-9">{text}</div>
    </div>
  );
}
