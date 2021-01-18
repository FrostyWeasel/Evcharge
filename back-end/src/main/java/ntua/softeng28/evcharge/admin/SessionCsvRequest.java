package ntua.softeng28.evcharge.admin;

import java.sql.Timestamp;

import com.opencsv.bean.CsvBindByName;

public class SessionCsvRequest {
    @CsvBindByName
    private String type;
    @CsvBindByName
    private String description;
    @CsvBindByName
    private Timestamp started_on;
    @CsvBindByName
    private Timestamp finished_on;
    @CsvBindByName
    private String protocol;
    @CsvBindByName
    private long energy_delivered;
    @CsvBindByName
    private String car_id;
    @CsvBindByName
    private Long charging_session_id;
    @CsvBindByName
    private Long charging_point_id;
    
    public SessionCsvRequest() {

    }

    public SessionCsvRequest(String type, String description, Timestamp started_on, Timestamp finished_on,
            String protocol, long energy_delivered, String car_id, Long charging_session_id, Long charging_point_id) {
        this.type = type;
        this.description = description;
        this.started_on = started_on;
        this.finished_on = finished_on;
        this.protocol = protocol;
        this.energy_delivered = energy_delivered;
        this.car_id = car_id;
        this.charging_session_id = charging_session_id;
        this.charging_point_id = charging_point_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStarted_on() {
        return started_on;
    }

    public void setStarted_on(Timestamp started_on) {
        this.started_on = started_on;
    }

    public Timestamp getFinished_on() {
        return finished_on;
    }

    public void setFinished_on(Timestamp finished_on) {
        this.finished_on = finished_on;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public long getEnergy_delivered() {
        return energy_delivered;
    }

    public void setEnergy_delivered(long energy_delivered) {
        this.energy_delivered = energy_delivered;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public Long getCharging_session_id() {
        return charging_session_id;
    }

    public void setCharging_session_id(Long charging_session_id) {
        this.charging_session_id = charging_session_id;
    }

    public Long getCharging_point_id() {
        return charging_point_id;
    }

    public void setCharging_point_id(Long charging_point_id) {
        this.charging_point_id = charging_point_id;
    }

    @Override
    public String toString() {
        return "SessionCsvRequest [car_id=" + car_id + ", charging_point_id=" + charging_point_id
                + ", charging_session_id=" + charging_session_id + ", description=" + description
                + ", energy_delivered=" + energy_delivered + ", finished_on=" + finished_on + ", protocol=" + protocol
                + ", started_on=" + started_on + ", type=" + type + "]";
    }

    
}
