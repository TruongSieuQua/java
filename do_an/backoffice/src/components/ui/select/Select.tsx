import { clsx } from "clsx";
import { VariantProps, tv } from "tailwind-variants";

const select = tv({
  base: "select select-bordered",
  variants: {
    width: {
      default: "w-fit",
      full: "w-full max-w-xs",
    },
    color: {
      default: "",
      primary: "select-primary",
      secondary: "select-secondary",
      accent: "select-accent",
      info: "select-info",
      success: "select-success",
      warning: "select-warning",
      danger: "select-danger",
    },
    size: {
      xs: "select-xs",
      sm: "select-sm",
      md: "",
      lg: "select-lg",
    },
  },
  defaultVariants: {
    width: "default",
    color: "default",
    size: "md",
  },
});
type SelectVariantsType = VariantProps<typeof select>;

/*
* Select Wrapper Component
*/
interface SelectWrapperProps extends React.LabelHTMLAttributes<HTMLLabelElement>{}
export function SelectWrapper({children, className, ...rest}: SelectWrapperProps){
	return <label className={clsx("form-control w-full max-w-xs", className)}>{children}</label>
}
/*
 * Label component
 */
// interface LabelProps extends React. <HTMLLabelElement> {}
// export function Label({ children, label, ...rest }: LabelProps) {
//   return (
//       <div className="label">
//         <span className={"label-text"}>{label}</span>
//       </div>
//   );
// }

/*
* LabelAlt component
*/
interface LabelAltProps extends React.LabelHTMLAttributes<HTMLLabelElement> {}

/*
 * Select component
 */
interface SelectProps
  extends SelectVariantsType,
    Omit<
      React.SelectHTMLAttributes<HTMLSelectElement>,
      keyof SelectVariantsType
    > {}
{
}
export function Select({
  color,
  size,
  className,
  children,
  ...rest
}: SelectProps) {
  return (
    <select className={select({ color, size, className })} {...rest}>
      {children}
    </select>
  );
}

/*
 * Option component
 */
interface OptionProps extends React.OptionHTMLAttributes<HTMLOptionElement> {}
export function Option({ children, ...rest }: OptionProps) {
  return <option {...rest}>{children}</option>;
}
