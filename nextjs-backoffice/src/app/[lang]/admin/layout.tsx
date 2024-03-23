import { Box, Flex } from '@/components/ui/layout';
import {
  SideBar,
  SideBarContent,
  SideBarFooter,
  SideBarHeader,
  SideBarMenu,
  SideBarMenuItem,
} from "@/components/sidebar";
import Image from 'next/image'
import Link from 'next/link';
import { FaHome } from "react-icons/fa";

import { Suspense } from "react";

interface AdminLayoutProps {
	children: React.ReactNode;
}

export default function AdminLayout({children}: AdminLayoutProps){
	return <Flex>
	<AdminSideBar />
	<Box className="flex-grow">
		<Suspense>
			<main>{children}</main>
		</Suspense>
	</Box>
</Flex>
}

function AdminSideBar() {
  return (
    <SideBar className="bg-secondary">
      <SideBarHeader>
        <Link href={"/admin/dashboard"} className="inline-block">
          <Flex direction="row" align="center" gap={"2"}>
            <Image
              src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png"
              alt="Logo"
              width={32}
              height={32}
            ></Image>
            <h1 className="text-[28px] leading-9 font-bold text-heading">
              Charmpy
            </h1>
          </Flex>
        </Link>
      </SideBarHeader>
      <SideBarContent>
        <SideBarMenu>
          <SideBarMenuItem href="/admin/dashboard">
            <FaHome />
            <span>Dashboard</span>
          </SideBarMenuItem>
          <SideBarMenuItem href="/admin/customers">
            <FaHome />
            <span>Customers</span>
          </SideBarMenuItem>
          <SideBarMenuItem href="/admin/products">
            <FaHome />
            <span>Product</span>
          </SideBarMenuItem>
          <SideBarMenuItem href="/admin/orders">
            <FaHome />
            <span>Orders</span>
          </SideBarMenuItem>
        </SideBarMenu>
      </SideBarContent>
      <SideBarFooter>
        <SideBarMenu>
          <SideBarMenuItem href="/admin/profile">
            <FaHome />
            <span>Profile</span>
          </SideBarMenuItem>
          <SideBarMenuItem href="/admin/settings">
            <FaHome />
            <span>Settings</span>
          </SideBarMenuItem>
        </SideBarMenu>
      </SideBarFooter>
    </SideBar>
  );
}
