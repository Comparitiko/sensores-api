import { Plantation } from '../interfaces/Plantation';
export interface User {
    id: string;
    username: string;
    password: string;
    email: string;
    token: string;
    plantations: Plantation[];
}