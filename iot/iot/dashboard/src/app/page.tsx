import { Actuator } from "../components/actuator";
import { ControlBoard } from "../components/control";
import { Forest } from "@/components/forest";
import { Authentication } from "@/components/authentication";
import Header from "@/components/header";
import { ClientProvider } from "./client-provider";

export default function Home() {
  console.log("Home page");

  return (
    <main className="flex min-h-screen flex-col items-center gap-6 p-24">
      <ClientProvider>
        <Authentication>
          <Header />
          <Forest />
          <ControlBoard />
          <Actuator />
        </Authentication>
      </ClientProvider>
    </main>
  );
}
