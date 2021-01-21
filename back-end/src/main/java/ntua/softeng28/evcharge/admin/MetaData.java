package ntua.softeng28.evcharge.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaData {
    @JsonProperty("updated_at")
    private String updated_at;

    @JsonProperty("overall_count")
    private String overall_count;

    public MetaData() {
        
    }

    public MetaData(String updated_at, String overall_count) {
        this.updated_at = updated_at;
        this.overall_count = overall_count;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getOverall_count() {
        return overall_count;
    }

    public void setOverall_count(String overall_count) {
        this.overall_count = overall_count;
    }

    @Override
    public String toString() {
        return "MetaData [overall_count=" + overall_count + ", updated_at=" + updated_at + "]";
    }

    
}
