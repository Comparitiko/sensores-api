import React from 'react';
import Header from '../components/layout/Header';
import Footer from '../components/layout/Footer';
import PlantationTable from '../components/tables/PlantationTable';
import { PlantationContextProvider } from '../contexts/PlantationContext';

const Plantations = () => {
  return (
    <div className="p-10 bg-gradient-to-b from-blue-100 to-gray-100 min-h-screen flex flex-col">
      <Header />
      <div className="mx-auto bg-white p-8 rounded-3xl shadow-2xl flex-grow">
        <h2 className="text-4xl font-bold mb-8 text-gray-800 text-center">🌿 Gestión de Plantaciones</h2>
        <PlantationContextProvider>
          <PlantationTable />
        </PlantationContextProvider>
      </div>
      <Footer />
    </div>
  );
};

export default Plantations;