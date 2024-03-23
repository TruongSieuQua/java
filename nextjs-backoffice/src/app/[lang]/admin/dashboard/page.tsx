import { redirect } from "next/navigation"

const isAuth = false;

export default function Dashboard(){
	if(!isAuth){
		redirect("/login") //server side redirect so it will refresh page. Can't use in client side component
		// router.push("/login") // from useRouter hook, client side redirect so it will not refresh page
	}

	return <div>Dashboard</div>
}
