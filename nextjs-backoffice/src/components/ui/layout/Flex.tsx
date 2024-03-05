import { compose, cva, type VariantProps } from "cva";
import clsx from 'clsx';
import {baseVariants} from "./Base";

interface FlexProps extends VariantProps<typeof flexVariants>, React.HTMLAttributes<HTMLDivElement> {}

const baseFlexVariants = cva(
  {
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
  },
);

const flexVariants = compose(baseVariants, baseFlexVariants);

// Flex component
const Flex = ({
	direction,
	gap,
	justify,
	align,
	wrap,
	position,
	shrink,
	grow,
	width,
	height,
	className
	,children,
	...rest }: FlexProps) => {

		const flexClasses = clsx(
			// Assuming you have a function called flexVariants
			flexVariants({ direction, gap, justify, align, wrap, position, shrink, grow, width, height }),
			className
		);

  return <div className={flexClasses} {...rest }>{children}</div>;
};

export {Flex};
