import type { Metadata } from "next";
import {Roboto} from "next/font/google";
import "./globals.css";
import { ClientProvider } from "./client-provider";

// const geistSans = localFont({
//   src: "./fonts/GeistVF.woff",
//   variable: "--font-geist-sans",
// });
// const geistMono = localFont({
//   src: "./fonts/GeistMonoVF.woff",
//   variable: "--font-geist-mono",
// });

const roboto = Roboto({
	subsets: ["latin", "vietnamese"],
	weight: ["300", "400", "500", "700"],

});

export const metadata: Metadata = {
  title: "Create Next App",
  description: "Generated by create next app",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={roboto.className}>
        <ClientProvider>{children}</ClientProvider>
      </body>
    </html>
  );
}