import { LanguageSwitcher } from "@/components/ui/lang-switcher";
import { ThemeToggle } from "@/components/ui/theme-toggle";
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
    <div className="grid h-screen grid-cols-12">
      <div className="relative h-screen bg-[#c7f1ff] lg:col-span-7 xl:col-span-8 2xl:col-span-9">
        <div className="absolute inset-0 flex items-center justify-center">
          <Image
            src="/images/JWTs-vs.-sessions.png"
            alt="Auth"
            width={800}
            height={800}
            className="h-auto w-3/5"
          />
        </div>
      </div>
      <div className="h-screen overflow-y-auto lg:col-span-5 xl:col-span-4 2xl:col-span-3">
        <div className="flex flex-col px-16 pb-6 pt-12">
          <div className="flex items-center justify-between">
            <Link href="/" className="logo">
              Charmpy
            </Link>
            <div className="flex gap-1">
              <ThemeToggle />
              <LanguageSwitcher />
            </div>
          </div>
          <div className="flex-grow">{children}</div>
        </div>
      </div>
    </div>
  );
}
