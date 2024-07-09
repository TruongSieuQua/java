import "./globals.css";
import { Inter } from "next/font/google";
import type { LayoutProps, PageProps } from "@/types";
import { dir } from "i18next";
import { languages, fallbackLng } from "@/i18n/settings";
import { useTranslation } from "@/i18n";
import { Suspense } from "react";
import Loading from "./loading";
import { ClientProvider } from "./client-provider";
import clsx from "clsx";

const inter = Inter({ subsets: ["latin"] });

export async function generateStaticParams() {
	return languages.map((lng) => ({ lng }));
}

export async function generateMetadata({ params: { lng } }: LayoutProps) {
	if (languages.indexOf(lng) < 0) lng = fallbackLng;
	// eslint-disable-next-line react-hooks/rules-of-hooks
	const { t } = await useTranslation(lng, "layout");
	return {
		title: t("title"),
		content: t("description"),
	};
}
interface RootLayoutProps extends PageProps {
	children: React.ReactNode;
}

export default async function RootLayout({
	children,
	params: { lng },
}: Readonly<RootLayoutProps>) {
	const { t } = await useTranslation(lng, "layout");
	return (
		<html lang={lng} dir={dir(lng)} data-theme="light">
			<body className={clsx("", inter.className)}>
				<Suspense fallback={<Loading />}>
					<ClientProvider>{children}</ClientProvider>
				</Suspense>
			</body>
		</html>
	);
}
