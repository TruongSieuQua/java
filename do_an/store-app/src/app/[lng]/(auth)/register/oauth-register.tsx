"use client";

import { useTranslation } from "@/i18n/client";
import { PageProps } from "@/types";
import { FaFacebook } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";

interface OAuthLoginFormProps extends PageProps {}

export function OAuthLoginForm({ params: { lng } }: OAuthLoginFormProps) {
  const { t } = useTranslation(lng, "register-page");

  return (
    <div className="flex flex-col gap-3">
      <div className="btn btn-outline">
        <FcGoogle className="h-6 w-6" />
        <span>{t("google-register")}</span>
      </div>
      <div className="btn btn-outline">
        <FaFacebook className="h-6 w-6" />
        <span>{t("facebook-register")}</span>
      </div>
    </div>
  );
}
