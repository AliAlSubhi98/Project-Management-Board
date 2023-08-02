package com.Codeline.Project.Management.Board.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // Include columns in the board
    @ElementCollection
    @CollectionTable(name = "board_columns_mapping", joinColumns = @JoinColumn(name = "board_id"))
    @MapKeyColumn(name = "column_index")
    @Column(name = "column_name")
    private Map<Integer, String> columns = new HashMap<>();

    public Board(Long id) {
        this.id = id;
    }
    public Board(String title, Map<Integer, String> columns) {
        this.title = title;
        this.columns = columns;
//        initializeSections();
    }
//    private void initializeSections() {
//        // Add the static sections "To Do", "In Progress", "Done" to the columns map
//        columns.put(1, "To Do");
//        columns.put(2, "In Progress");
//        columns.put(3, "Done");
//    }

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