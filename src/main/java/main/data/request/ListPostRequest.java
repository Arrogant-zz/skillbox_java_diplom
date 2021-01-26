package main.data.request;

import lombok.Data;

@Data
public class ListPostRequest {
    private int offset;
    private int limit;
    private String mode;
    private String query;
    private String date;
    private String tag;
}
