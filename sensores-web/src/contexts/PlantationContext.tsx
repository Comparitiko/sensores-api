import { createContext, ReactNode, useState } from "react";
import { Plantation, PlantationContextType, Sensor } from "../interfaces/plantation";

const PlantationContext = createContext<PlantationContextType | undefined>(undefined);

interface ProviderProps {
    children: ReactNode;
}

// Proveedor del contexto que envolverá la aplicación o los componentes necesarios
const PlantationProvider = ({ children }: ProviderProps) => {
    const HOST_URL = import.meta.env.VITE_HOST_URL; // URL base de la API obtenida desde las variables de entorno

    // Estados para manejar las plantaciones y sensores
    const [plantaciones, setPlantaciones] = useState<Plantation[]>([]);
    const [sensors, setSensors] = useState<Sensor[]>([]);
    const [hasLoaded, setHasLoaded] = useState<boolean>(false); // Indica si los datos se han cargado
    const [hasError, setHasError] = useState<boolean>(false); // Indica si hubo un error en la carga

    // Función para obtener la lista de plantaciones desde la API
    const getPlantations = async () => {
        const token = sessionStorage.getItem("token"); // Se obtiene el token almacenado en sessionStorage
        if (!token) return; // Si no hay token, la función termina sin hacer la solicitud

        try {
            setHasLoaded(false);
            setHasError(false);

            const response = await fetch(`${HOST_URL}/plantations`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`, // Se envía el token en los headers para la autenticación
                },
            });

            if (!response.ok) throw new Error("Error en la respuesta"); // Manejo de error en caso de respuesta no exitosa

            const data: Plantation[] = await response.json();
            setPlantaciones(data); // Se actualiza el estado con los datos obtenidos
            setHasLoaded(true);
        } catch (error) {
            console.error("Error obteniendo las plantaciones:", error);
            setHasError(true);
            setHasLoaded(false);
        }
    };

    // Función para obtener los sensores de una plantación específica
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const getSensors = async (_id: number) => {
        const token = sessionStorage.getItem("token");
        if (!token) return;

        try {
            setHasLoaded(false);
            setHasError(false);

            const response = await fetch(`${HOST_URL}`, { //TODO: No se si aquí hay que poner algo
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
            });

            if (!response.ok) throw new Error("Error en la respuesta");

            const data: Sensor[] = await response.json();
            setSensors(data);
            setHasLoaded(true);
        } catch (error) {
            console.error("Error obteniendo los sensores:", error);
            setHasError(true);
            setHasLoaded(false);
        }
    };

    return (
        <PlantationContext.Provider value={{
            plantaciones,
            sensors,
            hasLoaded,
            hasError,
            getPlantations,
            getSensors,
        }}>
            {children} {/* Proporciona el contexto a los componentes hijos */}
        </PlantationContext.Provider>
    );
};

export { PlantationProvider };
