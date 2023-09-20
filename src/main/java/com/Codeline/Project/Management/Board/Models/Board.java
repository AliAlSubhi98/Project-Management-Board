package com.Codeline.Project.Management.Board.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "boards")
public class Board extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "board")
    List<Card> cards;

    // Include columns in the board
    @ElementCollection
    @CollectionTable(name = "board_columns_mapping", joinColumns = @JoinColumn(name = "board_id"))
    @MapKeyColumn(name = "column_index")
    @Column(name = "column_name")
    private Map<Integer, String> columns = Map.of(
            1, "To do",
            2, "In progress",
            3, "Done"
    );

    public Board(Long id) {
        this.id = id;
    }

//    public Board(String title, Map<Integer, String> columns) {
//        this.title = title;
//        this.columns = columns;
//    }

    public Board(String title, Map<Integer, String> columns) {
        this.title = title;
        if (columns != null && !columns.isEmpty()) {
            this.columns.clear();
            this.columns.putAll(columns);
        }
    }


    // Helper method to get section name by section index
    public static String getSectionName(int sectionIndex) {
        // Assuming that the sections are initialized in the same order always
        switch (sectionIndex) {
            case 1:
                return "To Do";
            case 2:
                return "In Progress";
            case 3:
                return "Done";
            default:
                return "Unknown";
        }
    }
}
