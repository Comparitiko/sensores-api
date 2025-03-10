import { Link } from "react-router-dom";
import { Sensor } from "../../interfaces/Sensor";
import {
  formatDate,
  getSensorCardColor,
  getSensorCardIcon,
  translateSensorCardType,
} from "../../utils/utils";

interface SensorCardProps {
  sensor: Sensor;
}

export default function SensorCard({ sensor }: SensorCardProps) {
  return (
    <>
      <article className="w-full max-w-md bg-white rounded-xl shadow-2xl overflow-hidden">
        {/* Barra de color dinámica según la unidad */}
        <div className={`h-2 ${getSensorCardColor(sensor.sensorType)}`}></div>

        <section className="p-6 space-y-6">
          {/* Encabezado con icono y tipo de sensor */}
          <div className="flex items-center space-x-3">
            <span className="text-4xl">
              {getSensorCardIcon(sensor.sensorType)}
            </span>
            <h2 className="text-2xl font-bold text-gray-900">
              {translateSensorCardType(sensor.sensorType)}
            </h2>
          </div>

          {/* Detalles del sensor */}
          <div className="bg-gray-50 p-4 rounded-lg shadow-sm">
            <div className="grid grid-cols-2 gap-2 text-gray-700 text-sm">
              <p className="font-medium text-gray-500">Ubicación:</p>
              <small>{sensor.location}</small>
              <p className="font-medium text-gray-500">Coordenadas:</p>
              <small>
                {sensor.latitude}, {sensor.longitude}
              </small>
              <p className="font-medium text-gray-500">Unidad:</p>
              <small>{sensor.unit}</small>
              <p className="font-medium text-gray-500">Fecha de creación:</p>
              <small>{formatDate(sensor.createdAt)}</small>
            </div>
          </div>

          {/* Botón para ver detalles */}
          <div className="text-center">
            <Link
              to={`/plantations/sensors/${sensor.id}`}
              className="w-full bg-blue-500 text-white font-medium px-4 py-2 rounded-lg shadow-md hover:bg-blue-600 transition-all"
            >
              🔍 Ver detalles
            </Link>
          </div>
        </section>
      </article>
    </>
  );
}
