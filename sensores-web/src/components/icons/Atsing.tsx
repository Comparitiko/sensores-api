interface AtsingProps {
    className?: string;
}

export default function Atsing({className="h-6 w-6"}: AtsingProps){

    return (
        <svg className={className} fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
             viewBox="0 0 24 24" stroke="currentColor">
            <path
                d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"/>
        </svg>
    );
}