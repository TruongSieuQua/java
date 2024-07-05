"use client";

import { useRouter } from "next/navigation";
import { useEffect } from "react";
import { useCookies } from "react-cookie";

export function Authentication({children}: {children: React.ReactNode}) {
  const router = useRouter();
  const [cookies] = useCookies(['Bearer']);

  useEffect(() => {
    if(!cookies.Bearer){
      console.log('Redirecting to login page');
      router.push('/login');
    }
  }, []);

  return <>{children}</>;
}
