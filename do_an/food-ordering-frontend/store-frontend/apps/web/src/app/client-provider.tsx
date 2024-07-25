"use client";
import { UIStateProvider } from "@repo/peonyui";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import { ReactNode } from "react";

const queryClient = new QueryClient()

export const ClientProvider = ({ children }: { children: ReactNode }) => {
  return (
		<UIStateProvider>
			<QueryClientProvider client={queryClient}>
				{children}
				<ReactQueryDevtools />
			</QueryClientProvider>
		</UIStateProvider>);
};
