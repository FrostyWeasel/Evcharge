package ntua.softeng28.evcharge.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import ntua.softeng28.evcharge.car.AcCharger;
import ntua.softeng28.evcharge.car.EnergyConsumption;

public class CarData {
    @JsonProperty("id")
	private String id;
	
	@JsonProperty("brand")
	private String brand;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("brand_id")
	private String brand_id;
	
	@JsonProperty("model")
	private String model;

	@JsonProperty("release_year")
	private Integer release_year;

	@JsonProperty("variant")
	private String variant;

	@JsonProperty("usable_battery_size")
    private Float usable_battery_size;
    
    @JsonProperty("ac_charger")
    private AcCharger ac_charger;

    @JsonProperty("dc_charger")
    private DcChargerRequest dc_charger;

    @JsonProperty("energy_consumption")
    private EnergyConsumption energyConsumption;
    
    public CarData(){

    }

    public CarData(String id, String brand, String type, String brand_id, String model, Integer release_year,
            String variant, Float usable_battery_size, AcCharger ac_charger, DcChargerRequest dc_charger,
            EnergyConsumption energyConsumption) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.brand_id = brand_id;
        this.model = model;
        this.release_year = release_year;
        this.variant = variant;
        this.usable_battery_size = usable_battery_size;
        this.ac_charger = ac_charger;
        this.dc_charger = dc_charger;
        this.energyConsumption = energyConsumption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public Float getUsable_battery_size() {
        return usable_battery_size;
    }

    public void setUsable_battery_size(Float usable_battery_size) {
        this.usable_battery_size = usable_battery_size;
    }

    public AcCharger getAc_charger() {
        return ac_charger;
    }

    public void setAc_charger(AcCharger ac_charger) {
        this.ac_charger = ac_charger;
    }

    public DcChargerRequest getDc_charger() {
        return dc_charger;
    }

    public void setDc_charger(DcChargerRequest dc_charger) {
        this.dc_charger = dc_charger;
    }

    public EnergyConsumption getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(EnergyConsumption energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    @Override
    public String toString() {
        return "CarRequest [ac_charger=" + ac_charger + ", brand=" + brand + ", brand_id=" + brand_id + ", dc_charger="
                + dc_charger + ", energyConsumption=" + energyConsumption + ", id=" + id + ", model=" + model
                + ", release_year=" + release_year + ", type=" + type + ", usable_battery_size=" + usable_battery_size
                + ", variant=" + variant + "]";
    }
   

}
