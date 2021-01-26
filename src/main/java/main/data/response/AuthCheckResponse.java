package main.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import main.data.response.type.UserAuth;

@Data
public class AuthCheckResponse {
    private boolean result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserAuth user;

    public AuthCheckResponse(boolean result, UserAuth user) {
        this.result = result;
        this.user = user;
    }
}
