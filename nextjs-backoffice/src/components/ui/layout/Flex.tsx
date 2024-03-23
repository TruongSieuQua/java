import { forwardRef } from "react";
import { VariantProps, tv } from "tailwind-variants";
import {baseVariants, baseVariantKeys} from "./Base";
import { extractTvProps } from "@/utils";

const flexVariants = tv({
	extend: baseVariants,
	base: 'flex',
	variants: {
		direction: {
			row: 'flex-row',
			column: 'flex-col',
		},
		gap: {
			"1": 'gap-1',
			"2": 'gap-2',
			"3": 'gap-3',
			"4": 'gap-4',
			"5": 'gap-5',
			"6": 'gap-6',
			"7": 'gap-7',
			"8": 'gap-8',
			"9": 'gap-9',
			"10": 'gap-10',
		},
		justify: {
			start: 'justify-start',
			end: 'justify-end',
			center: 'justify-center',
			between: 'justify-between',
			around: 'justify-around',
		},
		align: {
			start: 'items-start',
			end: 'items-end',
			center: 'items-center',
			baseline: 'items-baseline',
			stretch: 'items-stretch',
		},
		wrap: {
			none: 'flex-nowrap',
			wrap: 'flex-wrap',
			reverse: 'flex-wrap-reverse'
		}
	},
});
type FlexVariantsType = VariantProps<typeof flexVariants>;
const flexVariantsKeys = ["direction", "gap", "justify", "align", "wrap"].concat(baseVariantKeys);

export interface FlexProps extends FlexVariantsType, React.ComponentPropsWithoutRef<"div"> {}

export const Flex = (props: FlexProps) => {

	const { tvProps, className, children, ...rest } = extractTvProps<FlexProps, FlexVariantsType>(props, ...flexVariantsKeys);

  return <div className={flexVariants({...tvProps, className})} {...rest }>{children}</div>;
};
