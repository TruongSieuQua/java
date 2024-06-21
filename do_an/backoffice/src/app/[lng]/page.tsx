import Image from "next/image";
import { useTranslation } from "@/i18n";
import { PageProps } from "@/interface";

interface HomePageProps extends PageProps {}

export default async function Home({ params: { lng } }: HomePageProps) {
  const { t } = await useTranslation(lng, ["hello-world-page"]);

  return (
    <main className="flex min-h-screen w-screen items-center justify-between">
      {/* <div>
					<SideBar />
				</div>
				<div className="flex-grow">
				<SideBarToggle />
					<NavBar />
								<div className="max-w-[1170px]">
						<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Culpa quod a explicabo cum! Ipsum molestiae deserunt fuga blanditiis doloremque id cumque et. Dolorem deleniti sequi cupiditate nemo eius, inventore ex?</p>
				</div>
			</div> */}
    </main>
  );
}
