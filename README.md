<div>
    <h1 style="text-align: center;">API Sensores</h1>
</div>

## Cosas que fixear

- En SensorDto deberíamos de tener un atributo de plantaciónId, y añadir a la entidad de Sensor la plantacion buscando el id de la plantación en la base de datos.

- En la entidad de Plantación hay que añadir la relación de sensores.

- En la entidad de Sensor hay que añadir la relación de plantación.

- En la entidad de Sensor hay que cambiar el atributo sensor_type a sensorType por temas de seguir un estilo de código.

- En dtos hay que añadir los dtos necesarios para la entidad de Plantación y modificar los controladores de plantación para que usen los dtos de la entidad.

## Cosas que añadir

- Autenticación de usuarios.
