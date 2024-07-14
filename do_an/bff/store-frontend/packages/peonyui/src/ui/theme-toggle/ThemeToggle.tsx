"use client";
import { set, startCase } from 'lodash';
import { useEffect } from 'react';
import { useCookies } from 'react-cookie';

const themeCookie = "th";

export function ThemeToggle() {
	const [cookies, setCookie] = useCookies(["th"]);

	useEffect(() => {
		const theme = cookies.th;
		if (theme == undefined) {
			setCookie(themeCookie, document.documentElement.getAttribute("data-theme"), { path: "/" });
			return;
		};

		document.documentElement.setAttribute("data-theme", theme);
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	function changeTheme(theme: string) {
		document.documentElement.setAttribute("data-theme", theme);
		setCookie(themeCookie, theme, { path: "/" });
	}

	return (
		<div className="dropdown dropdown-end">
			<div tabIndex={0} role="button" className="btn btn-ghost">
				Themes
			</div>
			<ul
				tabIndex={0}
				className="dropdown-content menu p-2 shadow bg-base-100 rounded-box w-fit"
			>
				{["light", "dark", "cupcake"].map((theme, index) => (
					<li key={index}>
						<div
							className="btn btn-ghost"
							onClick={() => changeTheme(theme)}
						>
							{startCase(theme)}
						</div>
					</li>
				))}
			</ul>
		</div>
	);
}
