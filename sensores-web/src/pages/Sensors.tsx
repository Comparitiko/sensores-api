import { lazy, useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { CONSTS } from "../consts";
import { UserContext } from "../contexts/UserContext";
import { Plantation } from "../interfaces/Plantation";
import Loader from "../components/Loader";
import Layout from "../layouts/Layout";

import TextError from "../components/TextError";

// Lazy loading of the sensor card component
const SensorCard = lazy(() => import("../components/cards/SensorCard"));

export default function Sensors() {
  const params = useParams();

  const userContext = useContext(UserContext);

  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [plantation, setPlantation] = useState<Plantation>({} as Plantation);
  const [errors, setErrors] = useState("");

  const getSensorsByPlantationId = async (abortController: AbortController) => {
    const res = await fetch(
      `${CONSTS.API_URL}/plantations/${params.plantationId}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${userContext?.user?.token}`,
        },
        signal: abortController.signal,
      },
    );

    if (!res.ok) {
      setIsLoading(false);
      setErrors(
        "No se pudieron recuperar los sensores, pruebe de nuevo mas tarde",
      );
      return;
    }

    const data = (await res.json()) as Plantation;

    setPlantation(data);
    setIsLoading(false);
  };

  useEffect(() => {
    // Create an abort controller to stop the request if is necessary
    const abortController = new AbortController();

    getSensorsByPlantationId(abortController);

    // If component destroy the request will be cancelled
    return (() => abortController.abort())
  }, []);

  if (isLoading) return <Loader />;

  return (
    <>
      <Layout>
        <main className="container mx-auto p-6">
          {errors ? (
            <TextError error={errors} />
          ) : (
            <>
              <h1 className="text-3xl font-bold text-gray-900 text-center mb-6">
                Sensores de la Plantación: {plantation.name}
              </h1>
              <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 place-items-center gap-5">
                {plantation.sensors.map((sensor) => (
                  <SensorCard key={sensor.id} sensor={sensor} />
                ))}
              </div>
            </>
          )}
        </main>
      </Layout>
    </>
  );
}
