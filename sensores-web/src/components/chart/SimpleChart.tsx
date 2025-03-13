import { useMemo } from "react";
import { AxisOptions, Chart } from "react-charts";
import { Sensor } from "../../interfaces/Sensor";
import { SensorData } from "../../interfaces/SensorData";
import { translateSensorCardType } from "../../utils/utils";

interface ChartProps {
  sensor: Sensor;
  sensorData: SensorData[];
}

interface ChartInfo {
  label: string;
  data: ChartData[];
}

interface ChartData {
  date: Date;
  value: number;
}

export default function SimpleChart({ sensor, sensorData }: ChartProps) {
  const data: ChartInfo[] = [
    {
      label: translateSensorCardType(sensorData[0].records[0].measurement),
      data: sensorData[0].records.map((record) => ({
        date: new Date(record.time),
        value: record.value,
      })),
    },
  ];

  const primaryAxis = useMemo(
    (): AxisOptions<ChartData> => ({
      getValue: (datum) => datum.date,
    }),
    []
  );

  const secondaryAxes = useMemo(
    (): AxisOptions<ChartData>[] => [
      {
        getValue: (datum) => datum.value,
      },
    ],
    []
  );

  return (
    <>
      <section className="min-w-sm max-w-7xl m-auto bg-white rounded-lg shadow-md p-4">
        <h1 className="text-3xl font-bold mb-4 text-center">
          Datos del sensor de la zona {sensor.location}
        </h1>
        <div className="min-h-72">
          <Chart
            options={{
              data,
              primaryAxis,
              secondaryAxes,
            }}
          />
        </div>
      </section>
    </>
  );
}
