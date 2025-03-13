import { createContext, ReactNode, useEffect, useState } from "react";
import { CONSTS } from "../consts";
import { Plantation, PlantationContextType } from "../interfaces/Plantation";
import { User } from "../interfaces/User";

const PlantationContext = createContext<PlantationContextType | undefined>(
  undefined
);

interface ProviderProps {
  children: ReactNode;
}

// Proveedor del contexto que envolverá la aplicación o los componentes necesarios
const PlantationProvider = ({ children }: ProviderProps) => {
  const HOST_URL = CONSTS.API_URL; // URL base de la API obtenida desde las variables de entorno

  // Estados para manejar las plantaciones y sensores
  const [plantaciones, setPlantaciones] = useState<Plantation[]>([]);
  const [hasLoaded, setHasLoaded] = useState<boolean>(false); // Indica si los datos se han cargado
  const [hasError, setHasError] = useState<boolean>(false); // Indica si hubo un error en la carga

  // Función para obtener la lista de plantaciones desde la API
  const getPlantations = async () => {
    const token = getToken(); // Se obtiene el token almacenado en sessionStorage
    if (!token) throw new Error("No token"); // Si no hay token, la función termina sin hacer la solicitud

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

  useEffect(() => {
    getPlantations();
  }, []);

  return (
    <PlantationContext.Provider
      value={{
        plantaciones,
        hasLoaded,
        hasError,
        getPlantations,
      }}
    >
      {children} {/* Proporciona el contexto a los componentes hijos */}
    </PlantationContext.Provider>
  );
};

const getToken = () => {
  const { token } = JSON.parse(sessionStorage.getItem("user") ?? "{}") as User;
  return token;
};

export { PlantationContext, PlantationProvider };
