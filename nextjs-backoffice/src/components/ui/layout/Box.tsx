import { VariantProps, compose, cva } from "cva";
import { baseVariants } from "./Base";

interface BoxProps extends VariantProps<typeof boxVariants>, React.HTMLAttributes<HTMLDivElement> {}
const baseBoxVariants = cva(
	{
		base: "",
		variants:{
			display: {
				block: 'block',
				inline: 'inline',
				inlineBlock: 'inline-block',
				flex: 'flex',
				inlineFlex: 'inline-flex',
				grid: 'grid',
				inlineGrid: 'inline-grid',
			},
		}})

	const boxVariants = compose(baseVariants, baseBoxVariants);

	export function Box({ className, children, ...rest }: BoxProps) {
		const boxClasses = `${boxVariants({...(rest as BoxProps)})} ${className}`;

		return <div className={boxClasses} {...(rest as React.HTMLAttributes<HTMLDivElement>)}>{children}</div>;
	}
