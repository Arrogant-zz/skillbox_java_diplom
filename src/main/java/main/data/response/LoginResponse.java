package main.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import main.data.response.type.UserAuth;

@Data
@AllArgsConstructor
public class LoginResponse {
    private boolean result = true;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserAuth user;
}
