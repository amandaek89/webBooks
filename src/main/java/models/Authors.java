package models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int age;
}
