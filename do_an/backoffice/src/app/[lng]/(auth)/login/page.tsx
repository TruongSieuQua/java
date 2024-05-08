import { useTranslation } from "@/i18n";
import { PageProps } from "@/interface";
import LoginForm from "./login-form";
import { fallbackLng, languages } from "@/i18n/settings";
import Link from "next/link";
import { Button } from "@/components/ui/button";
import { OAuthLoginForm } from "./oauth-login-form";

interface LoginPage extends PageProps {}

export async function generateStaticParams() {
	return languages.map((lng) => ({ lng }));
}

export async function generateMetadata({ params: { lng } }: LoginPage) {
	if (languages.indexOf(lng) < 0) lng = fallbackLng;
	const { t } = await useTranslation(lng, "login-page");
	return {
		title: t("title"),
	};
}

export default async function LoginPage({ params: { lng } }: LoginPage) {
	const { t } = await useTranslation(lng, ["login-page", "errors"]);

	return (
		<>
			<div>
				<h3 className="mt-12 text-center text-xl font-semibold lg:mt-18">
					{t("login")}
				</h3>
				<h3 className="mt-2 text-center text-sm text-base-content/70">
					{t("tagline")}
				</h3>
			</div>
			<div className="mt-10">
				<OAuthLoginForm  params={{lng}}/>
				<div className="line"/>
				<LoginForm params={{lng}}/>
			</div>
		</>
	);
}
