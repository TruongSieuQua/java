"use client";
import { UIStateProvider } from "@peonyui/context";
import { ReactNode } from "react";

export const ClientProvider = ({ children }: { children: ReactNode }) => {
  return (<UIStateProvider>{children}</UIStateProvider>);
};
