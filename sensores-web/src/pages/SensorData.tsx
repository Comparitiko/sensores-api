import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Loader from "../components/Loader";
import TextError from "../components/TextError";
import SimpleChart from "../components/chart/SimpleChart.tsx";
import SensorDataTable from "../components/tables/SensorDataTable.tsx";
import { CONSTS } from "../consts";
import { UserContext } from "../contexts/UserContext";
import { Sensor } from "../interfaces/Sensor";
import { SensorData as SensorDataInterface } from "../interfaces/SensorData";
import Layout from "../layouts/Layout";

export default function SensorData() {
  const userContext = useContext(UserContext);

  // Retrieve the sensor id from url params
  const params = useParams();
  const sensorId = params.sensorId;

  const [sensorInfo, setSensorInfo] = useState<Sensor>({} as Sensor);
  const [sensorData, setSensorData] = useState<SensorDataInterface[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [errors, setErrors] = useState("");

  const getDataBySensor = async (abortController: AbortController) => {
    const res = await fetch(`${CONSTS.API_URL}/sensors/data/${sensorId}`, {
      headers: {
        Authorization: `Bearer ${userContext?.user?.token}`,
        "content-type": "application/json",
      },
      signal: abortController.signal,
    });

    if (!res.ok) {
      setErrors(
        "No se pudieron recuperar los datos de los sensores, pruebe de nuevo mas tarde"
      );
      setIsLoading(false);
    }

    const data = await res.json();
    setSensorData(data);
    setIsLoading(false);
  };

  const getSensorInfo = async (abortController: AbortController) => {
    const res = await fetch(`${CONSTS.API_URL}/sensors/${sensorId}`, {
      headers: {
        Authorization: `Bearer ${userContext?.user?.token}`,
        "content-type": "application/json",
      },
      signal: abortController.signal,
    });

    if (!res.ok) {
      setErrors(
        "No se pudieron recuperar la información del sensor, pruebe de nuevo mas tarde"
      );
      setIsLoading(false);
    }

    const data = await res.json();
    setSensorInfo(data);
  };

  useEffect(() => {
    // Create an abort controller to stop the request if is necessary
    const abortController = new AbortController();
    getSensorInfo(abortController);
    getDataBySensor(abortController);

    // If component destroy the request will be cancelled
    return () => abortController.abort();
  }, []);

  if (isLoading) return <Loader />;

  return (
    <>
      <Layout>
        <main className="min-h-screen bg-gradient-to-b from-blue-100 to-gray-100 py-10">
          {errors ? (
            <TextError error={errors} />
          ) : (
            <>
              <div className="mx-auto bg-white p-2 md:p-8 rounded-3xl shadow-2xl w-full max-w-3xl">
                <SimpleChart sensor={sensorInfo} sensorData={sensorData} />
                <SensorDataTable sensorData={sensorData} />
              </div>
            </>
          )}
        </main>
      </Layout>
    </>
  );
}
