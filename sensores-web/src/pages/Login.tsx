import { ChangeEvent, FormEvent, useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Lock from "../components/icons/Lock";
import RigthArrow from "../components/icons/RigthArrow";
import UserSVG from "../components/icons/UserSVG";
import InputText from "../components/inputs/InputText";
import Label from "../components/inputs/Label";
import { CONSTS } from "../consts.ts";
import { UserContext } from "../contexts/UserContext.tsx";
import { User } from "../interfaces/User.tsx";

export default function Login() {
  const [form, setForm] = useState({
    username: "",
    password: "",
  });

  const navigate = useNavigate();

  const userContext = useContext(UserContext);

  const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
    if (e.target.id === "username") {
      setForm((prevState) => ({ ...prevState, username: e.target.value }));
    } else {
      setForm((prevState) => ({ ...prevState, password: e.target.value }));
    }
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!form.username || !form.password) return;

    // Aquí se debe enviar la petición a la API para validar el usuario
    // y obtener el token
    const response = await fetch(CONSTS.API_URL + "/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        //Aquí cogemos las variables que tenemos arriba
        ...form,
      }),
    });

    // Luego se debe almacenar el token en localStorage
    if (response.ok) {
      const data = (await response.json()) as User;
      userContext?.login(data);

      //Redirigimos al usuario a la página de inicio
      navigate("/");
    }
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-300">
      <div className="flex flex-col bg-white shadow-md px-4 sm:px-6 md:px-8 lg:px-10 py-8 rounded-md w-full max-w-md">
        <div className="font-medium self-center text-xl sm:text-2xl uppercase text-gray-800">
          Inicio de Sesión
        </div>
        <div className="mt-10">
          <form method={"post"} onSubmit={handleSubmit}>
            <div className="flex flex-col mb-6">
              <Label id={"username"}>Nombre de usuario:</Label>
              <div className="relative">
                <div className="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400">
                  <UserSVG />
                </div>
                <InputText
                  value={form.username}
                  id="username"
                  type="text"
                  placeholder="Nombre de usuario"
                  onChange={handleInput}
                />
              </div>
            </div>
            <div className="flex flex-col mb-6">
              <Label id={"password"}>Contraseña:</Label>
              <div className="relative">
                <div className="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400">
                  <span>
                    <Lock />
                  </span>
                </div>
                <InputText
                  value={form.password}
                  id={"password"}
                  type="password"
                  placeholder="Contraseña"
                  onChange={handleInput}
                />
              </div>
            </div>
            <hr className="my-6 border-gray-400" />
            <div className="flex w-full">
              <button
                type="submit"
                className="cursor-pointer flex items-center justify-center focus:outline-none text-white text-sm sm:text-base bg-blue-600 hover:bg-blue-700 rounded py-2 w-full transition duration-150 ease-in"
              >
                <span className="mr-2 uppercase">Iniciar Sesión</span>
                <span>
                  <RigthArrow />
                </span>
              </button>
            </div>
          </form>
        </div>
        <div className="flex justify-center items-center mt-6">
          <Link
            className="inline-flex items-center font-bold text-blue-500 hover:text-blue-700 text-xs text-center"
            to="/auth/register"
          >
            <span>
              <UserSVG />
            </span>
            <span className="ml-2">¿No tienes una cuenta?</span>
          </Link>
        </div>
      </div>
    </div>
  );
}
