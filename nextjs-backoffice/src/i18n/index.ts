import { KeyPrefix, createInstance } from "i18next";
import resourcesToBackend from "i18next-resources-to-backend";
import LanguageDetector from "i18next-browser-languagedetector";
import {  initReactI18next } from "react-i18next";
import { getOptions } from "./settings";

export interface LanguageProps {
  params: {
    lang: string;
  };
}

export async function initI18next  (lang: string, ns: string | string[]) {
  // on server side we create a new instance for each render, because during compilation everything seems to be executed in parallel
  const i18nInstance = createInstance();

	await i18nInstance
	.use(
		resourcesToBackend(
			(language: string, namespace: string) =>
			import(`/locales/${language}/${namespace}.json`),
      ),
			)
		.use(LanguageDetector)
		// .use(initReactI18next)
    .init(getOptions(lang, ns));
  return i18nInstance;
};

export async function useTranslation(lang: string, ns?: string | string[], keyPrefix?: KeyPrefix<string>) {
	const i18nextInstance = await initI18next(
		lang,
		Array.isArray(ns) ? (ns as string[]) : (ns as string),
	);
	return {
		t: i18nextInstance.getFixedT(lang, ns, keyPrefix),
		i18n: i18nextInstance,
	};
}
