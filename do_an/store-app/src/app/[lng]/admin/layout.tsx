import { AdminSideBar, SideBarToggle } from "@/components/sidebar";

import { LayoutProps } from "@/types";

interface AdminLayoutProps extends LayoutProps {}

export default async function AdminLayout({
  children,
  params: { lng },
}: Readonly<AdminLayoutProps>) {
  return (
    <div className="flex min-h-screen w-screen">
      <div id="sb">
        <AdminSideBar />
      </div>
      <div className="flex-grow">
        <SideBarToggle />
      </div>
    </div>
  );
}
