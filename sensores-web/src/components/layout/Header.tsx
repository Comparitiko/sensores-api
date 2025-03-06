import React, { useContext } from 'react';
import { UserContext } from '../../contexts/UserContext';

const Header = () => {
    const userContext = useContext(UserContext);

    return (
        <header className="bg-gray-900 text-white py-4 shadow-lg">
            <nav className="container mx-auto px-4">
                <div className="flex items-center justify-between">
                    {/* Logo o ícono con enlace */}
                    <div className="flex items-center space-x-3">
                        <a href="/" className="flex items-center space-x-3 hover:text-gray-300 transition duration-300">
                            <span className="text-4xl">📡</span>
                            <span className="text-4xl font-semibold">Sensores React</span>
                        </a>
                    </div>

                    {/* Menú de navegación */}
                    <ul className="flex items-center space-x-6">
                        <li>
                            <a href="/plantations" className="flex items-center space-x-2 hover:underline hover:text-gray-300 transition duration-300">
                                <span>🌿</span>
                                <span>Plantaciones</span>
                            </a>
                        </li>
                        <li>
                            <button onClick={userContext?.logout} className="flex items-center space-x-2 hover:underline hover:text-gray-300 transition duration-300 cursor-pointer">
                                <span>🚪</span>
                                <span>Cerrar sesión</span>
                            </button>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    );
};

export default Header;
