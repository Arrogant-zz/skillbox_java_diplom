package main.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "global_settings")
@Data
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;
    private String name;
    private String value;
}
