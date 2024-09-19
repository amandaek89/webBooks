package models;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String ISBN;

    @ManyToOne(fetch = FetchType.EAGER)
    private Authors author;
}
