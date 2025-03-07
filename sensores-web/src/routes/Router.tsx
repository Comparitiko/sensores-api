import { Route, Routes } from "react-router-dom";
import Login from "../pages/Login.tsx";
import Register from "../pages/Register.tsx";
import ProtectedRoutes from "./ProtectedRoutes";
import {useContext} from "react";
import {UserContext} from "../contexts/UserContext.tsx";

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
        <Route index element={<h1>Home</h1>} />
        <Route path="plantaciones" element={<h1>Plantaciones</h1>} />
        <Route path="plantaciones/:id" element={<h1>Plantaciones ID</h1>} />
        <Route path="plantaciones/:id/sensor/:sensor_id" />
      </Route>
      <Route path="*" element={<h1>404</h1>} />
    </Routes>
  );
}
