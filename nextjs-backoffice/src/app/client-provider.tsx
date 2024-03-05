"use client";

import * as React from "react";

interface ClientProviderProps {
  children: React.ReactNode;
}

function ThemeProvider({ children }: ClientProviderProps) {
  return (<>
      {children}
    </>
  );
}

export default function ClientProvider({ children }: ClientProviderProps) {
  return <ThemeProvider>{children}</ThemeProvider>;
}
