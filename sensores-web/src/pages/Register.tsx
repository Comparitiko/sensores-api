import { ChangeEvent, FormEvent, useContext, useId, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Atsing from "../components/icons/Atsing";
import Lock from "../components/icons/Lock";
import RigthArrow from "../components/icons/RigthArrow";
import UserSVG from "../components/icons/UserSVG";
import InputText from "../components/inputs/InputText";
import Label from "../components/inputs/Label";
import TextError from "../components/textError";
import { CONSTS } from "../consts";
import { UserContext } from "../contexts/UserContext";
import { User } from "../interfaces/User";

export default function Register() {
  const userContext = useContext(UserContext);

  const navigate = useNavigate();

  const inputsId = {
    username: useId(),
    email: useId(),
    password: useId(),
    confirmPassword: useId(),
  };

  const [userInfo, setUserInfo] = useState({
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    // Prevent the default form submission
    e.preventDefault();

    if (
      !userInfo.username ||
      !userInfo.email ||
      !userInfo.password ||
      !userInfo.confirmPassword
    ) {
      setError("Todos los campos son obligatorios");
      return;
    }

    setIsLoading(true);

    // Send the form data to the server for processing
    const response = await fetch(`${CONSTS.API_URL}/auth/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: userInfo.username,
        email: userInfo.email,
        password: userInfo.password,
        confirm_password: userInfo.confirmPassword,
      }),
    });

    if (!response.ok) {
      if (response.status === 400) {
        setError("El usuario ya existe");
      } else {
        setError("Error al registrarse, pruebe de nuevo mas tarde");
      }

      setIsLoading(false);

      return;
    }

    const user = (await response.json()) as User;

    setIsLoading(false);
    userContext?.login(user);
    navigate("/");
  };

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    switch (e.target.id) {
      case inputsId.username:
        setUserInfo((prevValue) => ({
          ...prevValue,
          username: e.target.value,
        }));
        return;
      case inputsId.email:
        setUserInfo((prevValue) => ({
          ...prevValue,
          email: e.target.value,
        }));
        return;
      case inputsId.password:
        setUserInfo((prevValue) => ({
          ...prevValue,
          password: e.target.value,
        }));
        return;
      case inputsId.confirmPassword:
        setUserInfo((prevValue) => ({
          ...prevValue,
          confirmPassword: e.target.value,
        }));
        return;
    }
  };

  return (
    <main className="min-h-screen flex flex-col items-center justify-center bg-gray-300">
      <div className="flex flex-col bg-white shadow-md px-4 sm:px-6 md:px-8 lg:px-10 py-8 rounded-md w-full max-w-md">
        <div className="font-medium self-center text-xl sm:text-2xl uppercase text-gray-800">
          Registro
        </div>
        <div className="mt-10">
          <form method="post" onSubmit={handleSubmit}>
            {error && <TextError error={error} />}
            <div className="flex flex-col mb-6">
              <Label id={inputsId.username}>Nombre de usuario</Label>
              <div className="relative">
                <div className="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400">
                  <Atsing />
                </div>
                <InputText
                  value={userInfo.username}
                  id={inputsId.username}
                  type="text"
                  placeholder="Nombre de usuario"
                  onChange={handleChange}
                />
              </div>
            </div>
            <div className="flex flex-col mb-6">
              <Label id={inputsId.email}>Dirección E-Mail:</Label>
              <div className="relative">
                <div className="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400">
                  <Atsing />
                </div>
                <InputText
                  id={inputsId.email}
                  type="email"
                  value={userInfo.email}
                  placeholder="Dirección E-Mail"
                  onChange={handleChange}
                />
              </div>
            </div>
            <div className="flex flex-col mb-6">
              <Label id={inputsId.password}>Contraseña:</Label>
              <div className="relative">
                <div className="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400">
                  <span>
                    <Lock />
                  </span>
                </div>
                <InputText
                  id={inputsId.password}
                  value={userInfo.password}
                  type="password"
                  placeholder="Contraseña"
                  onChange={handleChange}
                />
              </div>
            </div>
            <div className="flex flex-col mb-6">
              <Label id={inputsId.confirmPassword}>Confirmar contraseña</Label>
              <div className="relative">
                <div className="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400">
                  <span>
                    <Lock />
                  </span>
                </div>
                <InputText
                  id={inputsId.confirmPassword}
                  type="password"
                  value={userInfo.confirmPassword}
                  placeholder="Confirmar contraseña"
                  onChange={handleChange}
                />
              </div>
            </div>
            <hr className="my-6 border-gray-400" />
            <div className="flex w-full">
              <button
                type="submit"
                className="flex items-center justify-center focus:outline-none text-white text-sm sm:text-base bg-blue-600 hover:bg-blue-700 rounded py-2 w-full transition duration-150 ease-in"
              >
                {isLoading ? (
                  <span className="mr-2 uppercase">Cargando</span>
                ) : (
                  <>
                    <span className="mr-2 uppercase">Registrarse</span>
                    <span>
                      <RigthArrow />
                    </span>
                  </>
                )}
              </button>
            </div>
          </form>
        </div>
        <div className="flex justify-center items-center mt-6">
          <Link
            className="inline-flex items-center font-bold text-blue-500 hover:text-blue-700 text-xs text-center"
            to="/auth/login"
          >
            <span>
              <UserSVG />
            </span>
            <span className="ml-2">¿Ya tienes una cuenta?</span>
          </Link>
        </div>
      </div>
    </main>
  );
}
