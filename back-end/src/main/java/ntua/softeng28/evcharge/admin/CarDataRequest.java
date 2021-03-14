package ntua.softeng28.evcharge.admin;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarDataRequest {
    @JsonProperty("data")
    private CarData[] carData;

    @JsonProperty("brands")
    private BrandData[] brandData;

    @JsonProperty("meta")
    private MetaData metaData;

    public CarDataRequest() {

    }
    
    public CarDataRequest(CarData[] carData, BrandData[] brandData, MetaData metaData) {
        this.carData = carData;
        this.brandData = brandData;
        this.metaData = metaData;
    }

    public CarData[] getCarData() {
        return carData;
    }

    public void setCarData(CarData[] carData) {
        this.carData = carData;
    }

    public BrandData[] getBrandData() {
        return brandData;
    }

    public void setBrandData(BrandData[] brandData) {
        this.brandData = brandData;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    @Override
    public String toString() {
        return "CarDataRequest [brandData=" + Arrays.toString(brandData) + ", carData=" + Arrays.toString(carData)
                + ", metaData=" + metaData + "]";
    }
}
