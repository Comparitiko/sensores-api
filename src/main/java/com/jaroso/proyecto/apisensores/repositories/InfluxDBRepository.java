package com.jaroso.proyecto.apisensores.repositories;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class InfluxDBRepository {

    private final InfluxDBClient influxDBClient;

    @Value("${influx.bucket}")
    private String bucket;

    @Value("${influx.org}")
    private String org;

    public InfluxDBRepository(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
    }

    // Método para escribir datos en InfluxDB
    public void saveData(String location, double value) {
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

        //En este caso indicamos que el sensor es de temperatura
        Point point = Point.measurement("temperature")
                .addTag("location", location)
                .addField("value", value)
                .time(Instant.now(), WritePrecision.MS);

        writeApi.writePoint(bucket, org, point);
    }

    // Método para leer datos desde InfluxDB
    public List<FluxTable> getDataByLocation(String location) {
        //Modificar el rango de tiempo si es necesario
        String query = String.format(
                "from(bucket: \"%s\") |> range(start: -1h) |> filter(fn: (r) => r[\"location\"] == \"%s\")",
                bucket, location);

        return influxDBClient.getQueryApi().query(query, org);
    }
}
