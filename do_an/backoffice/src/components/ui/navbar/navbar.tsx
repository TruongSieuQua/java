"use client";
import * as NavigationMenu from "@radix-ui/react-navigation-menu";

export function Navigation({ children }: NavigationMenu.NavigationMenuProps) {
  return (
    <NavigationMenu.Root className="relative z-10 flex w-full justify-center">
      {children}
    </NavigationMenu.Root>
  );
}

export function NavigationList({
  children,
}: NavigationMenu.NavigationMenuProps) {
  return (
    <NavigationMenu.List className="m-0 flex list-none bg-base-100 p-1 hover:bg-red-300">
      {children}
			<NavigationMenu.Indicator className="top-full w-96 h-3 z-10 flex items-end justify-center overflow-hidden transition-[width,transform_250ms_ease]">
				<div className="relative top-[20%] h-3 w-3 -left-1/4 rotate-[45deg] border-l border-t border-neutral-content bg-base-100" />
      </NavigationMenu.Indicator>
    </NavigationMenu.List>
  );
}

export function NavigationItem({
  children,
}: NavigationMenu.NavigationMenuItemProps) {
  return (
    <NavigationMenu.Item className="relative">
      {children}
    </NavigationMenu.Item>
  );
}

export function NavigationItemTrigger({
  children,
}: NavigationMenu.NavigationMenuTriggerProps) {
  return (
    <NavigationMenu.Trigger
      asChild
      className="group flex items-center justify-between"
    >
      {children}
    </NavigationMenu.Trigger>
  );
}

export function NavigationItemContent({
  children,
}: NavigationMenu.NavigationMenuContentProps) {
  return (
    <NavigationMenu.Content className="absolute left-1/2 -translate-x-1/2 top-full mt-3 w-full rounded-lg border border-neutral-content bg-base-100 p-5 sm:w-auto">
      {children}
    </NavigationMenu.Content>
  );
}

// interface ListItemProps extends React.AnchorHTMLAttributes<HTMLAnchorElement> {
//   children: React.ReactNode;
// }

// const ListItem = forwardRef<HTMLAnchorElement, ListItemProps>(
//   ({ className, children, title, ...props }, forwardedRef) => (
//     <li>
//       <NavigationMenu.Link asChild>
//         <a
//           className={clsx(
//             "focus:shadow-violet7 hover:bg-mauve3 block select-none rounded-[6px] p-3 text-[15px] leading-none no-underline outline-none transition-colors focus:shadow-[0_0_0_2px]",
//             className,
//           )}
//           {...props}
//           ref={forwardedRef}
//         >
//           <div className="text-violet12 mb-[5px] font-medium leading-[1.2]">
//             {title}
//           </div>
//           <p className="text-mauve11 leading-[1.4]">{children}</p>
//         </a>
//       </NavigationMenu.Link>
//     </li>
//   ),
// );
// ListItem.displayName = "ListItem";
