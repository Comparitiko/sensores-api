import Lock from "../components/icons/Lock";
import Atsing from "./icons/Atsing.tsx";
import InputText from "./inputs/InputText.tsx";
import Label from "./inputs/Label.tsx";
import {Link} from "react-router-dom";
import RigthArrow from "./icons/RigthArrow.tsx";
import UserSVG from "./icons/UserSVG.tsx";
import {ChangeEvent, FormEvent, useState} from "react";


export default function Login (){

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value);
        setPassword(e.target.value);
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!email && !password) return;

    };



    return (
        <div class="min-h-screen flex flex-col items-center justify-center bg-gray-300">
            <div class="flex flex-col bg-white shadow-md px-4 sm:px-6 md:px-8 lg:px-10 py-8 rounded-md w-full max-w-md">
                <div class="font-medium self-center text-xl sm:text-2xl uppercase text-gray-800">Inicio de Sesión</div>
                <div class="mt-10">
                    <form action="#" onSubmit={handleSubmit}>
                        <div class="flex flex-col mb-6">
                            <Label id={"email"}>Dirección E-Mail:</Label>
                            <div class="relative">
                                <div class="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400">
                                    <Atsing />
                                </div>
                                <InputText id="email" type="email" placeholder="Dirección E-Mail" onChange={() => handleInput}/>
                            </div>
                        </div>
                        <div class="flex flex-col mb-6">
                            <Label id={"password"}>Contraseña:</Label>
                            <div class="relative">
                                <div class="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400">
                                  <span>
                                    <Lock/>
                                  </span>
                                </div>
                                <InputText id={"password"} type="password" placeholder="Contraseña" onChange={() => handleInput}/>
                            </div>
                        </div>
                        <hr className="my-6 border-gray-400"/>
                        <div class="flex w-full">
                            <button type="submit" class="flex items-center justify-center focus:outline-none text-white text-sm sm:text-base bg-blue-600 hover:bg-blue-700 rounded py-2 w-full transition duration-150 ease-in">
                                <span class="mr-2 uppercase">Iniciar Sesión</span>
                                <span>
                                    <RigthArrow />
                                </span>
                            </button>
                        </div>
                    </form>
                </div>
                <div class="flex justify-center items-center mt-6">
                    <Link class="inline-flex items-center font-bold text-blue-500 hover:text-blue-700 text-xs text-center"
                          to="/auth/register">
                        <span>
                            <UserSVG />
                        </span>
                        <span class="ml-2">¿No tienes una cuenta?</span>
                    </Link>
                </div>
            </div>
        </div>
    );
}
