"use client";
import { AuthProvider, UIStateProvider } from "@/context";
import { ReactNode } from "react";

export const ClientProvider = ({ children }: { children: ReactNode }) => {
  return (
    <AuthProvider>
      <UIStateProvider>{children}</UIStateProvider>
    </AuthProvider>
  );
};
