import NextImage, {type ImageProps as NextImageProps} from "next/image";

interface ImageProps extends NextImageProps {
}

// export default function Image(props: ImageProps){
// 	return <NextImage
// 		src={props.src}
// 		alt={props.alt}
// 	/>
// }

export function ImageUC1(props: ImageProps){
	return <NextImage
		{...props}
		title="Logo"
		alt="Logo"
		width={32}
		height={32}
		quality={75}
	/>
}
