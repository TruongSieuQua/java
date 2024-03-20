import { extractTvProps } from "@/utils";
import { twMerge } from "tailwind-merge";
import { VariantProps, tv } from "tailwind-variants";

const inputVariantsConfig = {
  base: "block w-full bg-primary border-2 border-primary \
	hover:border-hover \
	focus:border-focus \
	disabled:bg-disabled \
	invalid:bg-error invalid:border-error \
	valid:bg-success valid:border-success",
  variants: {
    size: {
      sm: "py-2.5 px-3 rounded-sm",
      md: "py-3.5 px-4 rounded-md",
      lg: "py-4.5 px-5 rounded-lg",
    },
  },
};
const inputVariants = tv(inputVariantsConfig);
type InputVariantsType = VariantProps<typeof inputVariants>;
const inputVariantKeys = Object.keys(inputVariantsConfig.variants);

interface InputProps
  extends InputVariantsType,
    Omit<
      React.InputHTMLAttributes<HTMLInputElement>,
      keyof InputVariantsType
    > {}

export function Input(props: InputProps) {
  const { tvProps, className, ...rest } = extractTvProps<
    InputProps,
    InputVariantsType
  >(props, ...inputVariantKeys);

  return (
    <input className={inputVariants({ ...tvProps, className })} {...rest} />
  );
}
