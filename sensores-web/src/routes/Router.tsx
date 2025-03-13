import { Route, Routes } from "react-router-dom";
import Login from "../pages/Login.tsx";
import Register from "../pages/Register.tsx";
import ProtectedRoutes from "./ProtectedRoutes";
import { useContext } from "react";
import { UserContext } from "../contexts/UserContext.tsx";
import Sensors from "../pages/Sensors.tsx";
import SensorData from "../pages/SensorData.tsx";
import Plantations from "../pages/Plantations.tsx";

export default function Router() {
  const userContext = useContext(UserContext);
  const canAccessAuthRoutes = () => !userContext!.isLoggedIn;
  const canAccessPrivateRoutes = () => userContext!.isLoggedIn;

  return (
    <Routes>
      <Route
        path="/auth"
        element={
          <ProtectedRoutes callback={canAccessAuthRoutes} navigateTo="/" />
        }
      >
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
      </Route>
      <Route
        path="/"
        element={
          <ProtectedRoutes
            callback={canAccessPrivateRoutes}
            navigateTo="/auth/login"
          />
        }
      >
        <Route index element={<Plantations />} />
        <Route path="/plantations/:plantationId" element={<Sensors />} />
        <Route path="/plantations/sensors/:sensorId" element={<SensorData />} />
      </Route>
      <Route path="*" element={<h1>404</h1>} />
    </Routes>
  );
}
