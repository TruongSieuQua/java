import { login } from '@/apis/authApi';
import { FormEvent, useState } from 'react';
import { LoginForm } from './login-form';

export default function LoginPage() {
  return (
    <main className="flex h-screen flex-col items-center">
      <LoginForm />
    </main>
  );
}
