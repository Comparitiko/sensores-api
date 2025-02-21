import React, { createContext, useState, ReactNode } from 'react';
import { User } from '../interfaces/User';

// Define the context type
interface UserContextType {
  user: User | null;
  setUser: (user: User | null) => void;
  isLoggedIn: boolean;
  setIsLoggedIn: (isLoggedIn: boolean) => void;
}

// Create the context with a default value
const UserContext = createContext<UserContextType | undefined>(undefined);

// Define the provider component
export function UserProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<User | null>(null);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // Provide the context value
  const value = {
    user,
    setUser,
    isLoggedIn,
    setIsLoggedIn,
  };

  return (
      <UserContext.Provider value={value}>
        {children}
      </UserContext.Provider>
  );
}

// Export the context for use in components
export { UserContext };
