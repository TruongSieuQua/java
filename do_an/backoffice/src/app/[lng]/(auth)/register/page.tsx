import { useTranslation } from "@/i18n";
import { PageProps } from "@/interface";
import RegisterForm from "./register-form";
import { OAuthLoginForm } from "./oauth-register";
import NextLink from "next/link";
import { LinkText } from "@/components/ui/link";
import { fallbackLng, languages } from "@/i18n/settings";

interface RegisterPageProps extends PageProps {}

export async function generateStaticParams() {
  return languages.map((lng) => ({ lng }));
}

export async function generateMetadata({ params: { lng } }: PageProps) {
  if (languages.indexOf(lng) < 0) lng = fallbackLng;
  // eslint-disable-next-line react-hooks/rules-of-hooks
  const { t } = await useTranslation(lng, "register-page");
  return {
    title: t("title"),
  };
}

export default async function RegisterPage({ params: { lng } }: PageProps) {
  const { t } = await useTranslation(lng, ["register-page", "errors"]);
  return (
    <>
      <div>
        <h3 className="lg:mt-18 mt-12 text-center text-xl font-semibold">
          {t("register")}
        </h3>
        <h3 className="mt-2 text-center text-sm text-base-content/70">
          {t("tagline")}
        </h3>
      </div>
      <div className="mt-10">
        <OAuthLoginForm params={{ lng }} />
        <div className="line" />
        <RegisterForm params={{ lng }} />
        <div className="mt-8 text-center">
          {t("already_have_account")}
          <NextLink href={`/${lng}/login`}>
            <LinkText className="ml-2">{t("login_now")}</LinkText>
          </NextLink>
        </div>
      </div>
    </>
  );
}
