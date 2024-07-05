"use client";
import { useRouter } from 'next/navigation'

const Header = () => {
  const router = useRouter();

  return (
      <header className="flex justify-between items-center p-4 bg-gray-100 shadow-md fixed top-0 right-0 left-0">
          <div className="text-lg font-bold">
              Forest Management System
          </div>
          <button className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-700"
            onClick={() => {router.push('/login')}}
          >
              Logout
          </button>
      </header>
  );
};

export default Header;
