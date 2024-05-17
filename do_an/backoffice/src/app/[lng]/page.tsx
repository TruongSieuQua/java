import Image from "next/image";
import {useTranslation} from '@/i18n';
import { PageProps } from "@/interface";
import { NavBar } from "@/components/navigation-menu";

interface HomePageProps extends PageProps {}

export default async function Home({params: {lng}}: HomePageProps) {
  const {t} = await useTranslation(lng, ["hello-world-page"]);

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <NavBar />
    </main>
  );
}
