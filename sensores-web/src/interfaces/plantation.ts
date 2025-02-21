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

export interface Sensor{
    id: number;
    name: string;
    type: string;
    status: string;
    plantationId: number;
    createdAt: string;
    updatedAt: string;
}

export interface PlantationContextType {
    plantaciones: Plantation[];
    sensors: Sensor[];
    hasLoaded: boolean;
    hasError: boolean;
    getPlantations: () => Promise<void>;
    getSensors: (id: number) => Promise<void>;
}
