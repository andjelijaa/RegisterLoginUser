package com.example.RegisterLoginUser.Model;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import javax.persistence.*;

@Slf4j
@Data
@Entity
@Table( name= "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String username;
    private String password;

/*    private int page = 1;

    private int limit = 10;

    private String direction = "dsc";


    public Sort buildSort() {
        switch (direction) {
            case "dsc":
                return Sort.by(name).descending();
            case "asc":
                return Sort.by(name).ascending();
            default:
                log.warn("Invalid direction provided in PageSettings, defaulting to descending");
                return Sort.by(name).descending();
        }
    }*/
}
