"use client";

import { useTranslation } from "@/i18n/client";
import { PageProps } from "@/interface";
import { FaFacebook } from "react-icons/fa";
import { FcGoogle } from "react-icons/fc";

interface OAuthLoginFormProps extends PageProps {}

export function OAuthLoginForm({ params: { lng } }: OAuthLoginFormProps) {
	const { t } = useTranslation(lng, "login-page");

	return (
		<div className="flex flex-col gap-3">
			<div className="btn btn-outline">
				<FcGoogle className="w-6 h-6" />
				<span>{t("google-login")}</span>
			</div>
			<div className="btn btn-outline">
				<FaFacebook className="w-6 h-6" />
				<span>{t("facebook-login")}</span>
			</div>
		</div>
	);
}
