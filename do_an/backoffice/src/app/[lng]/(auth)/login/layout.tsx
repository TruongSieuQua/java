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
		<div className="grid grid-cols-12 h-screen">
			<div className="h-screen lg:col-span-7 xl:col-span-8 2xl:col-span-9 bg-[#c7f1ff] relative">
				<div className="absolute inset-0 flex justify-center items-center">
					<Image
						src="/images/JWTs-vs.-sessions.png"
						alt="Auth"
						width={800}
						height={800}
						className="w-3/5 h-auto"
					/>
				</div>
			</div>
			<div className="h-screen overflow-y-scroll lg:col-span-5 xl:col-span-4 2xl:col-span-3">
				<div className="flex flex-col px-16 pt-12 pb-6">
					<div className="flex justify-between items-center">
						<Link href="/" className="logo">Charmpy</Link>
						<div className="flex gap-1">
							<ThemeToggle />
							<LanguageSwitcher />
						</div>
					</div>
					<div className="flex-grow">
						{children}
					</div>
				</div>
			</div>
		</div>
	);
}
