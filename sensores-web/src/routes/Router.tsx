import { Route, Routes } from "react-router-dom";
import ProtectedRoutes from "./ProtectedRoutes";
import Login from "../components/Login.tsx";

export default function Router() {
  // TODO: Do that with auth context

  const canAccessAuthRoutes = () => true;
  const canAccessPrivateRoutes = () => false;

  return (
    <Routes>
      <Route
        path="/auth"
        element={
          <ProtectedRoutes callback={canAccessAuthRoutes} navigateTo="/" />
        }
      >
        <Route path="login" element={<Login/>} />
        <Route path="registro" element={<h1>Registro</h1>} />
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
      </Route>
      <Route path="*" element={<h1>404</h1>} />
    </Routes>
  );
}
