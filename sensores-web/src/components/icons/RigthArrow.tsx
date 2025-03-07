

interface RigthArrowProps {
    className?: string;
}

export default function RigthArrow({className="h-6 w-6"}: RigthArrowProps){

    return (
        <svg className={className} fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
             viewBox="0 0 24 24" stroke="currentColor">
            <path d="M13 9l3 3m0 0l-3 3m3-3H8m13 0a9 9 0 11-18 0 9 9 0 0118 0z"/>
        </svg>
    );
}