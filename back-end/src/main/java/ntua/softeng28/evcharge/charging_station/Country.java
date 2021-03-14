package ntua.softeng28.evcharge.charging_station;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
    @JsonProperty("Title")
    private String name;

    @JsonProperty("ISOCode")
    private String ISO_code;

    @JsonProperty("ContinentCode")
    private String continent_code;

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISOCode() {
        return ISO_code;
    }

    public void setISOCode(String iSOCode) {
        ISO_code = iSOCode;
    }

    public String getContinentCode() {
        return continent_code;
    }

    public void setContinentCode(String continentCode) {
        this.continent_code = continentCode;
    }

    @Override
    public String toString() {
        return "Country [ISOCode=" + ISO_code + ", continentCode=" + continent_code + ", name=" + name + "]";
    }
}
