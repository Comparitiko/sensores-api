/**
 * Get the color class name of the sensor card with the sensor unit
 * @param unit
 * @returns class name
 */
export const getSensorCardColor = (sensorType: string): string => {
  switch (sensorType.toLowerCase()) {
    case "temperature":
      return "bg-red-500";
    case "humidity":
      return "bg-blue-500";
    case "pressure":
      return "bg-cyan-500";
    default:
      return "bg-gray-500";
  }
};

/**
 * Get the icon of the sensor card with his sensorType
 * @param sensorType
 * @returns icon of the card
 */
export const getSensorCardIcon = (sensorType: string): string => {
  switch (sensorType.toLowerCase()) {
    case "temperature":
      return "🌡️";
    case "humidity":
      return "💧";
    default:
      return "📡";
  }
};

/**
 * Translate the type of the sensor card
 * @param sensorType
 * @returns sensor type translated
 */
export const translateSensorCardType = (sensorType: string): string => {
  switch (sensorType.toLowerCase()) {
    case "temperature":
      return "Temperatura";
    case "humidity":
      return "Humedad";
    case "pressure":
      return "Presión";
    default:
      return "Sensor";
  }
};

export const formatDate = (dateStr: string) => {
  const date = new Date(dateStr);

  if (!date)
    throw new Error(
      "Error parsing date, check the params passed to the function",
    );

  return date.toLocaleString("es-ES", {
    weekday: "short", // long, short, narrow
    day: "numeric", // numeric, 2-digit
    year: "numeric", // numeric, 2-digit
    month: "long", // numeric, 2-digit, long, short, narrow
  });
};
