// AuthContext.tsx
import React, { createContext, useState, ReactNode } from 'react';
// import { AuthContextType } from './types';

interface AuthContextType{
	user: { name: string } | null;
	login: (userData: { name: string }) => void;
	logout: () => void;
}

/*
 * The AuthContext is used to manage the state of the user.
 * The AuthProvider is used to wrap the components that need to access the user state.
 * The useAuthContext hook is used to access the user state and login/logout functions.
*/
export const AuthContext = createContext<AuthContextType | undefined>(undefined);
export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<{ name: string } | null>(null);

  const login = (userData: { name: string }) => {
    setUser(userData);
  };

  const logout = () => {
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
export const useAuthContext = () => {
	const context = React.useContext(AuthContext);
	if (context === undefined) {
		throw new Error('useAuthContext must be used within a AuthProvider');
	}
	return context;
}
