
interface UserSVGProps {
    className?: string;
}

export default function UserSVG({className="h-6 w-6"}: UserSVGProps){

    return (
        <svg className={className} fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
             viewBox="0 0 24 24" stroke="currentColor">
            <path d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z"/>
        </svg>
    );
}