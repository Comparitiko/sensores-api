package dto;


public class SensorData {
    private String sensorId;
    private double value;

    public SensorData() {}

    public SensorData(String sensorId, double value) {
        this.sensorId = sensorId;
        this.value = value;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
