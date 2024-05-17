import { Inter } from "next/font/google";
import "./globals.scss";
import { PageProps } from "@/interface";
import { dir, t } from 'i18next'
import { languages, fallbackLng } from '@/i18n/settings'
import { useTranslation } from '@/i18n'
import { Suspense } from "react";
import Loading from "./loading";

const inter = Inter({ subsets: ["latin"] });


export async function generateStaticParams() {
  return languages.map((lng) => ({ lng }))
}

export async function generateMetadata({ params: { lng } }:PageProps) {
  if (languages.indexOf(lng) < 0) lng = fallbackLng
  // eslint-disable-next-line react-hooks/rules-of-hooks
	const { t } = await useTranslation(lng, 'layout')
  return {
    title: t('title'),
    content: t('description'),
  }
}
interface RootLayoutProps extends PageProps{
  children: React.ReactNode;
}

export default async function RootLayout({
  children,
  params: {lng}
}: Readonly<RootLayoutProps>) {
	const { t } = await useTranslation(lng, 'layout')
  return (
    <html lang={lng} dir={dir(lng)} data-theme="light">
      <body className={inter.className}>
		<Suspense fallback={<Loading/>} >
			{children}
		</Suspense>
		</body>
    </html>
  );
}
