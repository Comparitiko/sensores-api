import { AxisOptions, Chart } from "react-charts";
import { Sensor } from "../../interfaces/Sensor";
import { SensorData } from "../../interfaces/SensorData";
import { useMemo } from "react";

type DailyStars = {
  date: Date;
  stars: number;
};

type Series = {
  label: string;
  data: DailyStars[];
};

interface ChartProps {
  sensor: Sensor;
  sensorData: SensorData[];
}

interface ChartData {
  label: string;
  data: SensorData;
}

export default function SimpleChart({ sensor, sensorData }: ChartProps) {
  const data: Series[] = [
    {
      label: "React Charts",
      data: [
        {
          date: new Date(),
          stars: 202123,
        },
        // ...
      ],
    },
    {
      label: "React Query",
      data: [
        {
          date: new Date(),
          stars: 10234230,
        },
        // ...
      ],
    },
  ];

  console.log(sensor);
  console.log(sensorData);
  const primaryAxis = useMemo(
    (): AxisOptions<DailyStars> => ({
      getValue: (datum) => datum.date,
    }),
    [],
  );

  const secondaryAxes = useMemo(
    (): AxisOptions<DailyStars>[] => [
      {
        getValue: (datum) => datum.stars,
      },
    ],
    [],
  );

  return (
    <>
      <h1>Chart</h1>
      <section className="w-full h-1/2">
        <Chart
          options={{
            data,
            primaryAxis,
            secondaryAxes,
          }}
          className=""
        />
      </section>
    </>
  );
}
