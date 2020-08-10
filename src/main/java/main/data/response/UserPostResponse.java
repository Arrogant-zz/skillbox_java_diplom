package main.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserPostResponse {
    private int id;
    private String name;
}
