import { LanguageSwitcher } from "@/components/lang-switcher";
import { ThemeToggle } from "@/components/ui/theme-toggle";
import { PageProps } from "@/types";
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
    <div className="relative flex h-screen items-center justify-center">
      <div className="h-screen absolute top-0 left-0 right-0 z-[-1]">
				<Image
								src="https://website.vaadin.com/hubfs/jwt-authentication-with-vaadin-flow.png"
								alt="Register page image"
								objectFit="cover"
								fill={true}
							/>
			</div>
			<div className="h-fit max-h-[90vh] overflow-y-auto rounded-2xl bg-base-100 px-16 pb-6 pt-12">
        <div className="flex items-center justify-between">
          <Link href="/" className="logo">
            Charmpy
          </Link>
          <div className="flex gap-1">
            <ThemeToggle />
            <LanguageSwitcher />
          </div>
        </div>
        {children}
      </div>
    </div>
  );
}
