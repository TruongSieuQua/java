import clsx from "clsx";
import { ButtonHTMLAttributes, DetailedHTMLProps, forwardRef, ForwardedRef } from "react";

interface ButtonProps extends DetailedHTMLProps<ButtonHTMLAttributes<HTMLButtonElement>, HTMLButtonElement> {
  isOn?: boolean;
  children: React.ReactNode;
}

export const Button = ({ children, isOn, ...rest }: ButtonProps) => {
  return (
    <button
      className={clsx("py-2 px-4 rounded font-semibold ",
        {
          "text-white bg-blue-500 hover:bg-blue-600": isOn,
          "bg-transparent hover:bg-blue-200 border border-blue-500 hover:border-transparent": !isOn
        }
      )}
      {...rest}
    >
      {children}
    </button>
  );
};
