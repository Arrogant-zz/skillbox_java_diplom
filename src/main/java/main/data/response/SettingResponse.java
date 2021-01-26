package main.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SettingResponse {
    @JsonProperty("MULTIUSER_MODE")
    private boolean isMultiuserMode = false;
    @JsonProperty("POST_PREMODERATION")
    private boolean isPostPremoderation = false;
    @JsonProperty("STATISTICS_IS_PUBLIC")
    private boolean isStatisticsPublic = false;
}
