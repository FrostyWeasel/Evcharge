package ntua.softeng28.evcharge.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionCsvResponse {
    @JsonProperty("SessionsInUploadedFile")
	private final Integer sessionsInUploadedFile;
	
	@JsonProperty("SessionsImported")
	private final Integer sessionsImported;

	@JsonProperty("TotalSessionsInDatabase")
	private final Integer totalSessionsInDatabase;

    public SessionCsvResponse(Integer sessionsInUploadedFile, Integer sessionsImported,
            Integer totalSessionsInDatabase) {
        this.sessionsInUploadedFile = sessionsInUploadedFile;
        this.sessionsImported = sessionsImported;
        this.totalSessionsInDatabase = totalSessionsInDatabase;
    }

    public Integer getSessionsInUploadedFile() {
        return sessionsInUploadedFile;
    }

    public Integer getSessionsImported() {
        return sessionsImported;
    }

    public Integer getTotalSessionsInDatabase() {
        return totalSessionsInDatabase;
    }

    @Override
    public String toString() {
        return "SessionCsvResponse [sessionsImported=" + sessionsImported + ", sessionsInUploadedFile="
                + sessionsInUploadedFile + ", totalSessionsInDatabase=" + totalSessionsInDatabase + "]";
    }
}
