import { useTranslation } from "@/i18n";
import { PageProps } from "@/interface";
import RegisterForm from "./register-form";
import { OAuthLoginForm } from "./oauth-register";

interface RegisterPageProps extends PageProps {}

export default async function RegisterPage({ params: { lng } }: PageProps) {
  const { t } = await useTranslation(lng, ["register-page", "errors"]);

  return (
    <>
      <div>
        <h3 className="mt-12 text-center text-xl font-semibold lg:mt-18">
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
      </div>
    </>
  );
}
