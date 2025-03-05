import { Navigate, Outlet } from "react-router-dom";

interface ProtectedRoutesProps {
  callback: () => boolean;
  navigateTo: string;
}

const ProtectedRoutes = ({ callback, navigateTo }: ProtectedRoutesProps) => {
  // Check if user is logged in
  if (!callback()) {
    return <Navigate to={navigateTo} />;
  }

  return <Outlet />;
};

export default ProtectedRoutes;
