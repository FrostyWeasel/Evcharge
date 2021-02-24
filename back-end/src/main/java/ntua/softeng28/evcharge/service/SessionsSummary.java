package ntua.softeng28.evcharge.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionsSummary {
	@JsonProperty("PointID")
	private final Long pointID;

	@JsonProperty("PointSessions")
	private final Integer pointSessions;

	@JsonProperty("EnergyDelivered")
	private final Float energyDelivered;

	public SessionsSummary(Long pointID, Integer pointSessions, Float energyDelivered) {
		this.pointID = pointID;
		this.pointSessions = pointSessions;
		this.energyDelivered = energyDelivered;
	}

	public Long getPointID() {
		return pointID;
	}

	public Integer getPointSessions() {
		return pointSessions;
	}

	public Float getEnergyDelivered() {
		return energyDelivered;
	}

	@Override
	public String toString() {
		return "SessionsSummary [energyDelivered=" + energyDelivered + ", pointID=" + pointID + ", pointSessions="
				+ pointSessions + "]";
	}
}