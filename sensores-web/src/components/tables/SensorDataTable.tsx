import {SensorData} from "../../interfaces/SensorData.ts";
import {formatDate} from "../../utils/utils.ts";

interface SensorDataTableProps {
  sensorData: SensorData[];
}

export default function SensorDataTable({sensorData}: SensorDataTableProps) {
  return (
    <section className="mt-8">
      <h3 className="text-xl font-semibold mb-4 text-gray-700 text-center">📊 Datos Recientes</h3>
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white border border-gray-300 rounded-lg shadow-sm">
          <thead className="bg-gray-200 text-gray-700">
          <tr>
            <th className="px-4 py-2 border">Fecha</th>
            <th className="px-4 py-2 border">Temperatura</th>
          </tr>
          </thead>
          <tbody>
          {
            sensorData[0].records.map((record) => (
              <tr key={record.time.toString()} className="text-center border-b border-gray-300">
                <td className="px-4 py-2 border">{formatDate(record.time.toString())}</td>
                <td className="px-4 py-2 border">{record.value}</td>
              </tr>
            ))
          }
          </tbody>
        </table>
      </div>
    </section>
  );
}