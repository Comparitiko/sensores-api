import React from 'react';

const Header = () => {
    return (
        <header className="bg-gray-900 text-white py-4 shadow-lg">
        <nav className="container mx-auto px-4">
            <div className="flex items-center justify-between">
                {/* <!-- Logo o ícono con enlace --> */}
                <div className="flex items-center space-x-3">
                    {/* <a routerLink="/" class="flex items-center space-x-3 hover:text-gray-300 transition duration-300">
                        <span className="text-4xl">📡</span>
                        <span className="text-4xl font-semibold">Sensores Angular</span>
                    </a> */}
                </div>
    
                {/* <!-- Menú de navegación --> */}
                <ul className="flex items-center space-x-6">
                    <li>
                        {/* <a routerLink="/plantations"
                            class="flex items-center space-x-2 hover:underline hover:text-gray-300 transition duration-300">
                            <span>🌿</span>
                            <span>Plantaciones</span>
                        </a> */}
                    </li>
                    <li>
                        {/* <button (click)="onLogout()"
                            class="flex items-center space-x-2 hover:underline hover:text-gray-300 transition duration-300 cursor-pointer">
                            <span>🚪</span>
                            <span>Cerrar sesión</span>
                        </button> */}
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    );
};

export default Header;