import React from "react";
import { Plantation } from "../../interfaces/Plantation";

interface Props {
  plantations: Plantation[];
}

const PlantationTable: React.FC<Props> = ({ plantations }) => {
  return (
    <div className="p-10 bg-gradient-to-b from-blue-100 to-gray-100 min-h-screen">
      <div className="max-w-6xl mx-auto bg-white p-8 rounded-3xl shadow-2xl">
        <h2 className="text-4xl font-bold mb-8 text-gray-800 text-center">🌿 Gestión de Plantaciones</h2>
        
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white border border-gray-200 shadow-md rounded-lg">
            <thead>
              <tr className="bg-blue-500 text-white uppercase text-sm leading-normal">
                <th className="py-4 px-6 text-left">Nombre</th>
                <th className="py-4 px-6 text-left">Ubicación</th>
                <th className="py-4 px-6 text-left">País</th>
                <th className="py-4 px-6 text-left">Provincia</th>
                <th className="py-4 px-6 text-left">Ciudad</th>
                <th className="py-4 px-6 text-left">Coordenadas</th>
                <th className="py-4 px-6 text-left">Tipo</th>
                <th className="py-4 px-6 text-center">Sensores</th>
              </tr>
            </thead>
            <tbody className="text-gray-700 text-sm">
              {plantations.length > 0 ? (
                plantations.map((plantation) => (
                  <tr key={plantation.id} className="border-b border-gray-200 hover:bg-blue-50 transition-all">
                    <td className="py-4 px-6">{plantation.name}</td>
                    <td className="py-4 px-6">{plantation.ubication}</td>
                    <td className="py-4 px-6">{plantation.country}</td>
                    <td className="py-4 px-6">{plantation.province}</td>
                    <td className="py-4 px-6">{plantation.city}</td>
                    <td className="py-4 px-6 text-blue-600">{plantation.coordinates}</td>
                    <td className="py-4 px-6">{plantation.plantationType}</td>
                    <td className="py-4 px-6 text-center font-semibold text-green-600">{plantation.sensors.length} sensores</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan={8} className="text-center py-6 text-gray-500">No hay plantaciones registradas.</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default PlantationTable;
