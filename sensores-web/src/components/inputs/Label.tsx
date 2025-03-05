import {ReactNode} from "react";

interface LabelProps {
    id: string;
    children: ReactNode;
}

export default function Label({children, id}: LabelProps){
    return (
        <label htmlFor={id} className="mb-1 text-xs sm:text-sm tracking-wide text-gray-600">
            {children}
        </label>
    );
}