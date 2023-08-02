package com.Codeline.Project.Management.Board.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Integer section;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;


    // Getter for section name
    public String getSectionName() {
        return Board.getSectionName(this.section);
    }
}

