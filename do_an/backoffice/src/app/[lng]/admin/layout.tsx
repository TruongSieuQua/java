import { AdminSideBar, SideBarToggle } from "@/components/sidebar";

import { LayoutProps } from "@/interface";

interface AdminLayoutProps extends LayoutProps {}

export default async function AdminLayout({
  children,
  params: { lng },
}: Readonly<AdminLayoutProps>) {
  return ( <div className="flex w-screen min-h-screen items-center justify-between">
	<div>
		<AdminSideBar />
	</div>
	<div className="flex-grow">
	<SideBarToggle />
			<div className="max-w-[1170px]">
			<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Culpa quod a explicabo cum! Ipsum molestiae deserunt fuga blanditiis doloremque id cumque et. Dolorem deleniti sequi cupiditate nemo eius, inventore ex?</p>
	</div>
</div>
</div>
  );
}
