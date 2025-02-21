import React, { createContext, useState, ReactNode } from 'react';
import { User } from '../interfaces/User';
import {Navigate, useNavigate} from "react-router-dom";

// Define the context type
interface UserContextType {
  user: User | null;
  isLoggedIn: boolean;
  login: (userData: User) => void;
  logout: () => void;
}

// Create the context with a default value
const UserContext = createContext<UserContextType | undefined>(undefined);

// Define the provider component
export function UserProvider({ children }: { children: ReactNode }) {
  // Initialize state with user data from sessionStorage (if it exists)
  const [user, setUser] = useState<User | null>(() => {
    const storedUser = sessionStorage.getItem('user');
    return storedUser ? JSON.parse(storedUser) : null; // If there is no user then return null
  });

  // If null false and if non-null the true
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(() => {
    return !!sessionStorage.getItem('user');
  });

  // Login
  const login = (userData: User) => {
    sessionStorage.setItem('user', JSON.stringify(userData));

    setUser(userData);
    setIsLoggedIn(true);

    return <Navigate to={"/"}/>
  };

  // Logout
  const logout = () => {
    sessionStorage.removeItem('user');
    setUser(null);
    setIsLoggedIn(false);

    return <Navigate to={"/auth/login"}/>
  }



  // Provide the context value
  const value = {
    user,
    isLoggedIn,
    login,
    logout,
  };

  return (
      <UserContext.Provider value={value}>
        {children}
      </UserContext.Provider>
  );
}

// Export the context for use in components
export { UserContext };