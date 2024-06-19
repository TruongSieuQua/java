import Image from "next/image";
import { MuiLineChart } from "../components/line-chart";
import { Actuator } from "../components/actuator";
import { ControlBoard } from "../components/control";
import { Bounce, ToastContainer } from "react-toastify";


export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center gap-6 p-24">
      <Actuator />
      <MuiLineChart />
      <ControlBoard/>
      <ToastContainer
          position="bottom-right"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="light"
          transition={Bounce}
        />
    </main>
  );
}
