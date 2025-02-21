import Lock from "../icons/Lock.tsx";
import Atsing from "../icons/Atsing.tsx";
import InputText from "../inputs/InputText.tsx";
import Label from "../inputs/Label.tsx";
import {Link} from "react-router-dom";
import RigthArrow from "../icons/RigthArrow.tsx";
import UserSVG from "../icons/UserSVG.tsx";
import {ChangeEvent, FormEvent, useState} from "react";


export default function Login (){

    const [form, setForm] = useState({
        email: "",
        password: ""
    });

    const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
        if (e.target.id === "email") {
            setForm((prevState) => ({...prevState, email: e.target.value}));
        } else {
            setForm((prevState) => ({...prevState, password: e.target.value}));
        }
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!form.email || !form.password) return;

    };



    return (
        <div className="min-h-screen flex flex-col items-center justify-center bg-gray-300">
            <div className="flex flex-col bg-white shadow-md px-4 sm:px-6 md:px-8 lg:px-10 py-8 rounded-md w-full max-w-md">
                <div className="font-medium self-center text-xl sm:text-2xl uppercase text-gray-800">Inicio de Sesión</div>
                <div className="mt-10">
                    <form method={"post"} onSubmit={handleSubmit}>
                        <div className="flex flex-col mb-6">
                            <Label id={"email"}>Dirección E-Mail:</Label>
                            <div className="relative">
                                <div className="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400">
                                    <Atsing />
                                </div>
                                <InputText id="email" type="email" placeholder="Dirección E-Mail" onChange={() => handleInput}/>
                            </div>
                        </div>
                        <div className="flex flex-col mb-6">
                            <Label id={"password"}>Contraseña:</Label>
                            <div className="relative">
                                <div className="inline-flex items-center justify-center absolute left-0 top-0 h-full w-10 text-gray-400">
                                  <span>
                                    <Lock/>
                                  </span>
                                </div>
                                <InputText id={"password"} type="password" placeholder="Contraseña" onChange={() => handleInput}/>
                            </div>
                        </div>
                        <hr className="my-6 border-gray-400"/>
                        <div className="flex w-full">
                            <button type="submit" className="flex items-center justify-center focus:outline-none text-white text-sm sm:text-base bg-blue-600 hover:bg-blue-700 rounded py-2 w-full transition duration-150 ease-in">
                                <span className="mr-2 uppercase">Iniciar Sesión</span>
                                <span>
                                    <RigthArrow />
                                </span>
                            </button>
                        </div>
                    </form>
                </div>
                <div className="flex justify-center items-center mt-6">
                    <Link className="inline-flex items-center font-bold text-blue-500 hover:text-blue-700 text-xs text-center"
                          to="/auth/register">
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
