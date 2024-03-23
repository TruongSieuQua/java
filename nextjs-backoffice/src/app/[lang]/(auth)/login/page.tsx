import { LanguageProps, useTranslation } from "@/i18n";
import { TranslationsProvider } from "@/components/translations";
import { fallbackLng, languages } from "@/i18n/settings";
import LoginForm from "./login-form";
import {Flex} from "@/components/ui/layout";

const namespaces = ["error", "login-page"];

export async function generateStaticParams() {
  return languages.map((lang) => ({ lang }))
}

export async function generateMetadata({ params: { lng } }: {
  params: {
    lng: string;
  };
}) {
  if (languages.indexOf(lng) < 0) lng = fallbackLng
  // eslint-disable-next-line react-hooks/rules-of-hooks
  const { t } = await useTranslation(lng, namespaces)
  return {
    title: t('title')
  }
}

interface LoginProps extends LanguageProps {}

export default async function Login({ params: { lang } }: LoginProps) {
  const { t } = await useTranslation(lang, namespaces);

  return (
    <TranslationsProvider namespaces={namespaces} lang={lang}>
      <Flex>
        <LoginForm />
      </Flex>
    </TranslationsProvider>
  );
}
