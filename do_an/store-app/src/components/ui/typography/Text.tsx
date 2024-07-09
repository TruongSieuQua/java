
interface TextProps extends React.ComponentPropsWithoutRef<"span"> {}

export function Text({children, className, ...rest}: TextProps) {
	return <span {...rest}>{children}</span>
}