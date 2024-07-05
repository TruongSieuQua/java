"use client";
import { login } from '@/apis/authApi';
import { notify } from '@/components/toasify';
import { useRouter } from 'next/navigation'
import { FormEvent, useEffect, useState } from 'react';
import { useCookies } from 'react-cookie';

export function LoginForm() {
  const router = useRouter()
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [cookies, setCookie, remove] = useCookies(['Bearer']);

  const handleSubmit = async(event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const response = await login({ email, password });
    if(response.success){
      setCookie('Bearer', response.data.token, {path: '/'});
      router.push('/');
    }else{
      // notify(response.error.message);
      notify('Incorrect email or password!');
    }
  };

  useEffect(() => {
    remove('Bearer');
  }, [])

  return (
    <div className="flex justify-center items-center min-h-screen">
      <div className="p-8 bg-white shadow-md rounded-md border border-gray-300">
        <h1 className="text-2xl font-bold mb-6 text-center">Login</h1>
        <form onSubmit={handleSubmit} className="flex flex-col gap-4">
          <div>
            <label htmlFor="email" className="block text-sm font-medium">
              Email
            </label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="mt-1 block w-full rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-500 focus:ring-opacity-50 h-8 px-3"
              required
            />
          </div>
          <div>
            <label htmlFor="password" className="block text-sm font-medium">
              Password
            </label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="mt-1 block w-full rounded-md border-2 border-gray-400 shadow-sm focus:border-indigo-500 focus:ring focus:ring-indigo-500 focus:ring-opacity-50 h-8 px-3"
              required
            />
          </div>
          <button
            type="submit"
            className="mt-4 w-full rounded-md bg-indigo-600 px-4 py-2 text-white hover:bg-indigo-700"
          >
            Login
          </button>
        </form>
      </div>
    </div>
  );
}
