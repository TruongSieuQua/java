import * as DropdownMenu from "@radix-ui/react-dropdown-menu";

export function Dropdown({ children }: DropdownMenu.DropdownMenuProps) {
  return <DropdownMenu.Root>{children}</DropdownMenu.Root>;
}

export function DropdownMenuTrigger({
  children,
}: DropdownMenu.DropdownMenuTriggerProps) {
  return <DropdownMenu.Trigger asChild>{children}</DropdownMenu.Trigger>;
}

export function DropdownMenuContent({
  children,
}: DropdownMenu.DropdownMenuContentProps) {
  return (
    <DropdownMenu.Portal>
      <DropdownMenu.Content
        className="data-[side=top]:animate-slideDownAndFade data-[side=right]:animate-slideLeftAndFade data-[side=bottom]:animate-slideUpAndFade data-[side=left]:animate-slideRightAndFade min-w-[220px] rounded-md bg-white p-[5px] shadow-[0px_10px_38px_-10px_rgba(22,_23,_24,_0.35),_0px_10px_20px_-15px_rgba(22,_23,_24,_0.2)] will-change-[opacity,transform]"
        sideOffset={5}
      >
        {children}
      </DropdownMenu.Content>{" "}
    </DropdownMenu.Portal>
  );
}
