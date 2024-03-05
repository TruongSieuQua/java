import { VariantProps, cva } from "cva";

export type BaseVariantsProps = VariantProps<typeof baseVariants>;
export const baseVariants = cva({
	base: '',
	variants:{
		position: {
			static: 'static',
			absolute: 'absolute',
			relative: 'relative',
			fixed: 'fixed',
			sticky: 'sticky'
		},
		shrink: {
			"0": 'flex-shrink-0',
			"1": 'flex-shrink-1'
		},
		grow: {
			"0": 'flex-grow-0',
			"1": 'flex-grow-1'
		},
		width: {
			auto: 'w-auto',
			full: 'w-full',
			screen: 'w-screen',
			min: 'w-min',
			fit: 'w-fit',
			max: 'w-max'
		},
		height: {
			auto: 'h-auto',
			full: 'h-full',
			screen: 'h-screen',
			min: 'h-min',
			fit: 'h-fit',
			max: 'h-max'
		},
	}
})
