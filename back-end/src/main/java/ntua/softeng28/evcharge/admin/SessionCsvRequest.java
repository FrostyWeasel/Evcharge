package ntua.softeng28.evcharge.admin;

import java.sql.Timestamp;

import com.opencsv.bean.CsvBindByName;

public class SessionCsvRequest {
    // @CsvBindByName
    // private String type;
    // @CsvBindByName
    // private String description;
    @CsvBindByName
    private Timestamp started_on;
    @CsvBindByName
    private Timestamp finished_on;
    @CsvBindByName
    private String protocol;
    @CsvBindByName
    private Float energy_delivered;
	@CsvBindByName
    private Float cost;
    @CsvBindByName
    private String car_id;
    @CsvBindByName
    private Long charging_point_id;
    @CsvBindByName
    private Long energy_provider_id;

	public SessionCsvRequest(){
		
	}
    
	public SessionCsvRequest(Timestamp started_on, Timestamp finished_on, String protocol, Float energy_delivered,
			Float cost, String car_id, Long charging_point_id, Long energy_provider_id) {
		this.started_on = started_on;
		this.finished_on = finished_on;
		this.protocol = protocol;
		this.energy_delivered = energy_delivered;
		this.cost = cost;
		this.car_id = car_id;
		this.charging_point_id = charging_point_id;
		this.energy_provider_id = energy_provider_id;
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
	public Float getEnergy_delivered() {
		return energy_delivered;
	}
	public void setEnergy_delivered(Float energy_delivered) {
		this.energy_delivered = energy_delivered;
	}
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public Long getCharging_point_id() {
		return charging_point_id;
	}
	public void setCharging_point_id(Long charging_point_id) {
		this.charging_point_id = charging_point_id;
	}
	public Long getEnergy_provider_id() {
		return energy_provider_id;
	}
	public void setEnergy_provider_id(Long energy_provider_id) {
		this.energy_provider_id = energy_provider_id;
	}
	@Override
	public String toString() {
		return "SessionCsvRequest [car_id=" + car_id + ", charging_point_id=" + charging_point_id + ", cost=" + cost
				+ ", energy_delivered=" + energy_delivered + ", energy_provider_id=" + energy_provider_id
				+ ", finished_on=" + finished_on + ", protocol=" + protocol + ", started_on=" + started_on + "]";
	}

	
}
