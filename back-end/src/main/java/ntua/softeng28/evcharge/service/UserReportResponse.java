package ntua.softeng28.evcharge.service;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserReportResponse {
    @JsonProperty("Username")
    private final String username;

    @JsonProperty("RequestTimestamp")
	private final String requestTimestamp;

	@JsonProperty("PeriodFrom")
	private final String periodFrom;

	@JsonProperty("PeriodTo")
	private final String periodTo;

    @JsonProperty("TotalCost")
	private final Float totalCost;

	@JsonProperty("TotalSessions")
	private final Integer totalSessions;

	@JsonProperty("TotalCars")
	private final Integer totalCars;

    @JsonProperty("TotalEnergy")
	private final Float totalEnergy;

    @JsonProperty("CarSummary")
	private final CarSummary[] carSummary;

    public UserReportResponse(String username, String requestTimestamp, String periodFrom, String periodTo,
            Float totalCost, Integer totalSessions, Integer totalCars, Float totalEnergy, CarSummary[] carSummary) {
        this.username = username;
        this.requestTimestamp = requestTimestamp;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.totalCost = totalCost;
        this.totalSessions = totalSessions;
        this.totalCars = totalCars;
        this.totalEnergy = totalEnergy;
        this.carSummary = carSummary;
    }

    public String getUsername() {
        return username;
    }

    public String getRequestTimestamp() {
        return requestTimestamp;
    }

    public String getPeriodFrom() {
        return periodFrom;
    }

    public String getPeriodTo() {
        return periodTo;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public Integer getTotalSessions() {
        return totalSessions;
    }

    public Integer getTotalCars() {
        return totalCars;
    }

    public Float getTotalEnergy() {
        return totalEnergy;
    }

    public CarSummary[] getCarSummary() {
        return carSummary;
    }

    @Override
    public String toString() {
        return "UserReportResponse [carSummary=" + Arrays.toString(carSummary) + ", periodFrom=" + periodFrom
                + ", periodTo=" + periodTo + ", requestTimestamp=" + requestTimestamp + ", totalCars=" + totalCars
                + ", totalCost=" + totalCost + ", totalEnergy=" + totalEnergy + ", totalSessions=" + totalSessions
                + ", username=" + username + "]";
    }

}
