import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import ProtectedRoutes from "./ProtectedRoutes";

export const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/login" element={<h1>Login</h1>} />
      <Route path="/register" element={<h1>Register</h1>} />
      <Route path="/" element={<ProtectedRoutes />}>
        <Route path="plantaciones" element={<h1>Plantaciones</h1>} />
        <Route path="plantaciones/:id" element={<h1>Plantaciones ID</h1>} />
      </Route>
    </>
  )
);
