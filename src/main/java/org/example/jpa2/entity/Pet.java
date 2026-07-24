package org.example.jpa2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 20)
    private String name;
    @Column(nullable = false)
    private int age;
    // SoftDelete
    private boolean isDeleted;

    public void changeDeleted() {
        isDeleted = true;
    }
}
