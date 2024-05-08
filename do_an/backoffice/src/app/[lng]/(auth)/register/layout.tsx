import { LanguageSwitcher } from "@/components/lang-switcher";
import { ThemeToggle } from "@/components/theme-toggle";
import { PageProps } from "@/interface";
import Image from "next/image";
import Link from "next/link";

interface LayoutProps extends PageProps {
	children: React.ReactNode;
}

export default async function Layout({
	params: { lng },
	children,
}: LayoutProps) {

	return (
		<div className="flex justify-center items-center h-screen bg-[url('/images/register-bg.png')] bg-cover relative">
			<div className="bg-base-100">
				<div className="px-16 py-12">
					<div className="flex justify-between items-center">
						<Link href="/" className="logo">Charmpy</Link>
						<div className="flex gap-1">
							<ThemeToggle />
							<LanguageSwitcher />
						</div>
					</div>
					{children}
				</div>
			</div>
		</div>
	);
}