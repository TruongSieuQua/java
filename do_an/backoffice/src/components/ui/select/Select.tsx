"use client";

import ReactSelect from 'react-select';
import {type Props as ReactSelectProps} from 'react-select';
import makeAnimated from 'react-select/animated';

const animatedComponents = makeAnimated();
interface SelectProps extends ReactSelectProps{}
export function Select({...rest}:SelectProps){
	return <ReactSelect components={animatedComponents} {...rest}/>
}

// import { clsx } from "clsx";
// import * as Select from "@radix-ui/react-select";
// import { VariantProps, tv } from "tailwind-variants";
// import { FaArrowDown, FaArrowUp } from "react-icons/fa";
// import { IoIosArrowDown, IoIosCheckmark } from "react-icons/io";
// import { ForwardedRef, forwardRef } from "react";

// //className="inline-flex items-center justify-center rounded px-[15px] text-[13px] leading-none h-[35px] gap-[5px] bg-white text-violet11 shadow-[0_2px_10px] shadow-black/10 hover:bg-mauve3 focus:shadow-[0_0_0_2px] focus:shadow-black data-[placeholder]:text-violet9 outline-none"
// const select = tv({
//   base: "select select-bordered block",
//   variants: {
//     width: {
//       default: "w-fit",
//       full: "w-full max-w-xs",
//     },
//     color: {
//       default: "",
//       primary: "select-primary",
//       secondary: "select-secondary",
//       accent: "select-accent",
//       info: "select-info",
//       success: "select-success",
//       warning: "select-warning",
//       danger: "select-danger",
//     },
//     size: {
//       xs: "select-xs",
//       sm: "select-sm",
//       md: "",
//       lg: "select-lg",
//     },
//   },
//   defaultVariants: {
//     width: "default",
//     color: "default",
//     size: "md",
//   },
// });
// const selectLabel = tv({
// 	base: "text-[18px] leading-[25px] text-center font-semibold",
// });

// const selectSeperator = tv({
// 	base: "h-[1px] bg-neutral-content m-1"
// })

// const selectItem = tv({
//   base: "text-[16px] leading-none text-violet11 rounded-[3px] flex items-center h-[25px] pr-[35px] pl-[25px] relative select-none data-[disabled]:text-mauve8 data-[disabled]:pointer-events-none data-[highlighted]:outline-none data-[highlighted]:bg-violet9 data-[highlighted]:text-violet1",
// });
// type SelectVariantsType = VariantProps<typeof select>;

// /*
//  * SelectRoot Component
//  */
// interface SelectRootProps {
//   children: React.ReactNode;
// }
// export function SelectRoot({ children }: SelectRootProps) {
//   return <Select.Root>{children}</Select.Root>;
// }

// /*
//  * SelectTrigger Component
//  */
// interface SelectTriggerProps
//   extends SelectVariantsType,
//     Omit<Select.SelectTriggerProps, keyof SelectVariantsType> {}
// {
// }
// export function SelectTrigger({
//   width,
//   color,
//   size,
//   className,
//   children,
//   ...rest
// }: SelectTriggerProps) {
//   return (
//     <Select.Trigger
//       className={select({ width, color, size, className })}
//       {...rest}
//     >
//       <Select.Value placeholder="Select a fruit…" />
//     </Select.Trigger>
//   );
// }

// /*
//  * SelectContent Component
//  */
// interface SelectContentProps extends Select.SelectViewportProps {}
// export function SelectContent({ children, ...rest }: SelectContentProps) {
//   return (
//     <Select.Portal>
//       <Select.Content className="overflow-hidden bg-white rounded-md shadow-[0px_10px_38px_-10px_rgba(22,_23,_24,_0.35),0px_10px_20px_-15px_rgba(22,_23,_24,_0.2)]">
//         <Select.ScrollUpButton className="flex items-center justify-center h-[25px] bg-white text-violet11 cursor-default">
//           <FaArrowUp />
//         </Select.ScrollUpButton>
//         <Select.Viewport {...rest}>{children}</Select.Viewport>
//         <Select.ScrollDownButton className="flex items-center justify-center h-[25px] bg-white text-violet11 cursor-default">
//           <FaArrowDown />
//         </Select.ScrollDownButton>
//       </Select.Content>
//     </Select.Portal>
//   );
// }

// /*
//  * SelectGroup component
//  */
// interface SelectGroupProps extends Select.SelectGroupProps {}
// export function SelectGroup({ children, ...rest }: SelectGroupProps) {
//   return <Select.Group {...rest}>{children}</Select.Group>;
// }

// /*
//  * SelectLabel
//  */
// interface SelectLabelProps extends Select.SelectLabelProps {}
// export function SelectLabel({ children, className, ...rest }: SelectLabelProps) {
//   return (
//     <Select.Label className={selectLabel({})}>
//       {children}
//     </Select.Label>
//   );
// }

// /*
// * Select Separator
// */
// interface SelectSeparatorProps extends Select.SelectSeparatorProps {}
// export function SelectSeparator({className}: SelectSeparatorProps) {
// 	return <Select.Separator className={selectSeperator({className})}/>;
// }

// /*
//  * SelectItem component
//  */
// interface SelectItemProps extends Select.SelectItemProps {}
// export const SelectItem = forwardRef(
//   (
//     { children, className, ...rest }: SelectItemProps,
//     forwardedRef: ForwardedRef<HTMLInputElement>,
//   ) => {
//     return (
//       <Select.Item className={selectItem({})} {...rest} ref={forwardedRef}>
//         <Select.ItemText>{children}</Select.ItemText>
//         <Select.ItemIndicator className="absolute left-0 w-[25px] inline-flex items-center justify-center">
//           <IoIosCheckmark />
//         </Select.ItemIndicator>
//       </Select.Item>
//     );
//   },
// );

