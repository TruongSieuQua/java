import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { PageProps } from "@/interface";
import { dir } from 'i18next'
import { languages, fallbackLng } from '@/i18n/settings'
import { useTranslation } from '@/i18n'

const inter = Inter({ subsets: ["latin"] });


export async function generateStaticParams() {
  return languages.map((lng) => ({ lng }))
}

export async function generateMetadata({ params: { lng } }:PageProps) {
  if (languages.indexOf(lng) < 0) lng = fallbackLng
  const { t } = await useTranslation(lng, 'layout')
  return {
    title: t('title'),
    content: t('description'),
  }
}
interface RootLayoutProps extends PageProps{
  children: React.ReactNode;
}

export default function RootLayout({
  children,
  params: {lng}
}: Readonly<RootLayoutProps>) {
  return (
    <html lang={lng} dir={dir(lng)} data-theme="light">
      <body className={inter.className}>
		{children}
		</body>
    </html>
  );
}
