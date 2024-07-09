"use client";
import { UIStateProvider } from "@repo/ui";
import { ReactNode } from "react";

export const ClientProvider = ({ children }: { children: ReactNode }) => {
  return (
      <UIStateProvider>{children}</UIStateProvider>
  );
};
