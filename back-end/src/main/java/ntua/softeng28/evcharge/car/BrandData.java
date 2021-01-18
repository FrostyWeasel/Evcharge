package ntua.softeng28.evcharge.car;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandData {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    public BrandData(){
        
    }

    public BrandData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BrandData [id=" + id + ", name=" + name + "]";
    }
}
