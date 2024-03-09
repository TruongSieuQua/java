import { VariantProps, tv } from 'tailwind-variants';

const baseVariantsConfig = {
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
			"0": 'shrink-0',
			"1": 'shrink'
		},
		grow: {
			"0": 'grow-0',
			"1": 'grow'
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
}

export type BaseVariantsProps = VariantProps<typeof baseVariants>;
export const baseVariants = tv(baseVariantsConfig);
export const baseVariantKeys = Object.keys(baseVariantsConfig.variants);
