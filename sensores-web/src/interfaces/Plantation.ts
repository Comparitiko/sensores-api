import type { Sensor } from "./Sensor";

// Interfaz para las plantaciones
export interface Plantation {
  id: number;
  name: string;
  ubication: string;
  country: string;
  province: string;
  city: string;
  coordinates: string;
  plantationType: string;
  sensors: Sensor[];
}

export interface PlantationContextType {
  plantaciones: Plantation[];
  sensors: Sensor[];
  hasLoaded: boolean;
  hasError: boolean;
  getPlantations: () => Promise<void>;
  getSensors: (id: number) => Promise<void>;
}
