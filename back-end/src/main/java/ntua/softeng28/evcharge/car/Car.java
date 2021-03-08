package ntua.softeng28.evcharge.car;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Car {

	private @Id String id;

	private String type;

	@ManyToOne
	private Brand brand;

	private String model;

	private Integer release_year;

	private String variant;

	private Float usable_battery_size;

	@OneToOne
	private AcCharger ac_charger;

	@OneToOne
	private DcCharger dc_charger;

	@Embedded
	private EnergyConsumption energy_consumption;

	public Car() {
	}

	public Car(String type, Brand brand, String model, Integer release_year, String variant, Float usable_battery_size,
			AcCharger ac_charger, DcCharger dc_charger, EnergyConsumption energy_consumption) {
		super();
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.release_year = release_year;
		this.variant = variant;
		this.usable_battery_size = usable_battery_size;
		this.ac_charger = ac_charger;
		this.dc_charger = dc_charger;
		this.energy_consumption = energy_consumption;
	}

	public Car(String id, String type, Brand brand, String model, Integer release_year, String variant,
			Float usable_battery_size, AcCharger ac_charger, DcCharger dc_charger,
			EnergyConsumption energy_consumption) {
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.release_year = release_year;
		this.variant = variant;
		this.usable_battery_size = usable_battery_size;
		this.ac_charger = ac_charger;
		this.dc_charger = dc_charger;
		this.energy_consumption = energy_consumption;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
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

	public DcCharger getDc_charger() {
		return dc_charger;
	}

	public void setDc_charger(DcCharger dc_charger) {
		this.dc_charger = dc_charger;
	}

	public EnergyConsumption getEnergy_consumption() {
		return energy_consumption;
	}

	public void setEnergy_consumption(EnergyConsumption energy_consumption) {
		this.energy_consumption = energy_consumption;
	}

	@Override
	public String toString() {
		return "Car [ac_charger=" + ac_charger + ", brand=" + brand + ", dc_charger=" + dc_charger
				+ ", energy_consumption=" + energy_consumption + ", id=" + id + ", model=" + model + ", release_year="
				+ release_year + ", type=" + type + ", usable_battery_size=" + usable_battery_size + ", variant="
				+ variant + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}