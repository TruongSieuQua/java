import { IoLanguageOutline } from "react-icons/io5";
import { useRouter } from "next/navigation";
import { usePathname } from 'next/navigation'

export function LanguageSwitcher() {
	const router = useRouter();
	const pathname = usePathname();
	function changeLanguage(lng: string) {
		const locale = pathname.split("/")[1];
		if(locale == lng) {
			return;
		}
		router.push(`/${lng}${pathname.replace(`/${locale}`, "")}`);
	}

	return (
		<div className="dropdown dropdown-end">
			<div tabIndex={0} className="btn btn-ghost">
				<IoLanguageOutline />
			</div>
			<ul tabIndex={0} className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-fit">
				<li>
					<div className="btn btn-ghost" onClick={()=>changeLanguage("en")}>En</div>
				</li>
				<li>
					<div className="btn btn-ghost" onClick={()=>changeLanguage("vi")}>Vi</div>
				</li>
			</ul>
		</div>
	);
}
