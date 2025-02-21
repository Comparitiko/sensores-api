import { Navigate, Outlet } from "react-router-dom";

const ProtectedRoutes = () => {
  // TODO - check if the user is logged in with the context
  const isLoggedIn = false;

  // Check if user is logged in
  if (!isLoggedIn) {
    return <Navigate to="/login" />;
  }

  return <Outlet />;
};

export default ProtectedRoutes;
