import {ChangeEvent, useId} from "react";


interface InputTextProps {
    id: string;
    type: string;
    placeholder: string;
    value?: string;
    onChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

export default function InputText({id, type, placeholder, value="", onChange}: InputTextProps){
    return (
        <input id={id} type={type}
               className="text-sm sm:text-base placeholder-gray-500 pl-10 pr-4 rounded-lg border border-gray-400 w-full py-2 focus:outline-none focus:border-blue-400"
               placeholder={placeholder}
               value={value}
               onChange={onChange}
        />
    );
}