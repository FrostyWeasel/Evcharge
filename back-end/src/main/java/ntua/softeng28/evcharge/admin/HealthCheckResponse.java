package ntua.softeng28.evcharge.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HealthCheckResponse {
    @JsonProperty("status")
    String status;

    public HealthCheckResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HealthCheckResponse [status=" + status + "]";
    }
    
}
