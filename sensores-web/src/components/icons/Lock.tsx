
interface LockProps {
    className?: string;
}

export default function Lock({className="h-6 w-6"}: LockProps){

    return (
        <svg className={className} fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
             viewBox="0 0 24 24" stroke="currentColor">
            <path
                d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"
            />
        </svg>
    );
}