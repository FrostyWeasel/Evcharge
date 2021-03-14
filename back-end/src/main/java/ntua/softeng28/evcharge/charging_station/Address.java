package ntua.softeng28.evcharge.charging_station;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Address {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

    @JsonProperty("AddressLine1")
    private String address;

    @JsonProperty("Town")
    private String town;

    @JsonProperty("StateOrProvince")
    private String province;

    @JsonProperty("Country")
    @Embedded
    @AttributeOverride(name = "name", column = @Column(name="country_name"))
    @AttributeOverride(name = "ISO_code", column = @Column(name="country_ISO_code"))
    @AttributeOverride(name = "continent_code", column = @Column(name="country_continent_code"))
    private Country country;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address [address=" + address + ", country=" + country + ", id=" + id + ", province=" + province
                + ", town=" + town + "]";
    }
}
