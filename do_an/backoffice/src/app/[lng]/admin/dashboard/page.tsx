import { LoadingText } from "@/components/loading";
import { PageProps } from "@/interface";

interface DashBoardProps extends PageProps{}

export default function DashBoard({ params: { lng } }:PageProps) {
	return <div><LoadingText text="Loading..."/></div>
}
