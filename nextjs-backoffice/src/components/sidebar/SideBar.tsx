"use client";

import {useState} from 'react';
import Link from "next/link";
import Image from "next/image";
import { ReactNode } from "react";
import { IconType } from "react-icons";
import { usePathname } from 'next/navigation'
import { Box, Flex } from "../ui/layout";


interface SideBarProps extends React.HTMLAttributes<HTMLDivElement> {}

interface SideBarLinkProps
  extends React.AnchorHTMLAttributes<HTMLAnchorElement> {
  Icon?: IconType;
  title?: string;
	badgeContent?: string;
}

function SideBar({ children }: SideBarProps) {
  return (
    <Flex
      direction="column"
      className="w-[19.5rem] h-screen border-r-2 py-6 relative"
    >
      <Flex direction="column" grow="1">
        <Link href={"/dashboard"} className="inline-block ml-6 mb-6">
          <Flex direction={"row"} align="center" gap={"2"}>
            <Image
              src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png"
              alt="Logo"
              width={32}
              height={32}
            ></Image>
            <Heading>Champy</Heading>
          </Flex>
        </Link>
        <Box className="flex-grow relative">
          <Flex direction="column" className="absolute top-0 bottom-0 w-full">
            {children}
          </Flex>
        </Box>
      </Flex>
    </Flex>
  );
}

function Heading({children}: {children: ReactNode}){
	return <h1 className="text-[28px] leading-9 font-bold text-heading">{children}</h1>
}

function SideBarTop({ children }: { children: ReactNode }) {
  return (
    <Box className="flex-grow px-4 overflow-y-scroll">
      <Box>{children}</Box>
    </Box>
  );
}

function SideBarBottom({ children }: { children: ReactNode }) {
  return (
    <Box className="h-fit px-4">
      <Box className="border-t-2 pt-1">
        {children}
      </Box>
    </Box>
  );
}

function SideBarMenu({children}: {children: ReactNode}){
	return <ul className="space-y-2 font-medium">
		{children}
	</ul>
}

function SideBarMenuItem({
  Icon,
  title,
	badgeContent,
  href = "/admin/articles",
}: SideBarLinkProps) {

	return (
    <li>
		<Link href={href} legacyBehavior>
      <Flex
        align="center"
        gap="3"
        className="p-2 rounded-lg hover:bg-on-action-hover-2"
      >
        {Icon && <Icon className="w-5 h-5"/>}
        <span className="flex-grow ms-3">{title}</span>
      </Flex>
    </Link>
		</li>
  );
}

function SideBarAccordion({ children }: { children: ReactNode }) {
  return (
    <Box className="">
      {children}
    </Box>
  );
}

function SideBarAccordionTrigger({Icon, title, badgeContent} : SideBarLinkProps){
	return (
		<button
			type="button"
			className="flex items-center w-full p-2"
		>
			{Icon && <Icon className="w-5 h-5 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white" />}
			<span className="flex-1 ms-3 text-left rtl:text-right whitespace-nowrap">
				{title}
			</span>
		</button>
	);
}

function SideBarAccordionContent({children} : {children: ReactNode}){
	return (
		<div className="flex flex-col">
			{children}
		</div>
	);
}

export { SideBar, SideBarTop, SideBarBottom, SideBarMenu, SideBarMenuItem };
