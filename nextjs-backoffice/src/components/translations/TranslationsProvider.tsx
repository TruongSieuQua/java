'use client';

import { I18nextProvider } from 'react-i18next';
import { useTranslation } from '@/i18n';


interface TranslationsProviderProps {
	children: React.ReactNode;
	lang: string;
	namespaces: string | string[];
}

export async function TranslationsProvider({
  children,
  lang,
  namespaces,
}: TranslationsProviderProps) {
	const {i18n} = await useTranslation(lang, namespaces);
	i18n.options.defaultNS = namespaces; // So no need to pass parameter to function useTranslation from "react-i18next"
  return <I18nextProvider i18n={i18n}>{children}</I18nextProvider>;
}
