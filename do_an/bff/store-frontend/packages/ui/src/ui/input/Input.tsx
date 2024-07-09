import type { InputProps, InputTextLabelProps, InputIconProps } from "./input.d";
import { forwardRef } from "react";
import { input, inputWithIcon, inputWithText } from "./input-variants";


/*
 * Input is a component that renders an input element.
 * It accepts all the props that an input element accepts.
 */
const Input = forwardRef<HTMLInputElement, InputProps>(
  ({ size, color, width, className, ...rest }, ref) => {
    return (
      <input
				ref={ref}
        className={input({ size, color, width, className })}
        {...rest}
      />
    );
  },
);

/*
 * InputTextLabel is a component that renders a label element inside input.
 */
const InputTextLabel = forwardRef<HTMLInputElement, InputTextLabelProps>(({
  size,
  color,
  width,
  className,
  children,
  label,
  ...rest
}, ref) => {
  return (
    <label
      className={inputWithText({ size, color, width, className })}
    >
      {label}
      <input ref={ref} className="grow" {...rest} />
    </label>
  );
})

/*
* InputIcon is a component that renders an icon inside input.
*/
const InputIcon = forwardRef<HTMLInputElement, InputIconProps>(({
  size,
  color,
  width,
  className,
  children,
	icon,
  iconPosition="left",
  ...rest
}, ref) => {
  return (
    <label
      className={inputWithIcon({ size, color, width, className })}
    >
      {iconPosition === "left" && icon}
      <input ref={ref} className="grow" {...rest} />
			{iconPosition === "right" && icon}
    </label>
  );
})

Input.displayName = "Input";
InputTextLabel.displayName = "InputTextLabel";
InputIcon.displayName = "InputIcon";

export { Input, InputTextLabel, InputIcon};
