"use client";

import { Bounce, ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

interface ClientProviderProps {
  children: React.ReactNode;
}

export function ClientProvider({children}: ClientProviderProps){
  return (
    <div>
      {children}
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
    </div>
  );
}
