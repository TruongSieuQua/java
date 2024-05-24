import { AdminSideBar, SideBarToggle } from "@/components/sidebar";

import { LayoutProps } from "@/interface";

interface AdminLayoutProps extends LayoutProps {}

export default async function AdminLayout({
  children,
  params: { lng },
}: Readonly<AdminLayoutProps>) {
  return (
    <div className="flex min-h-screen w-screen items-center justify-between">
      <div id="side-bar">
        <AdminSideBar />
      </div>
      <div className="flex-grow">
        <SideBarToggle />
      </div>
    </div>
  );
}
