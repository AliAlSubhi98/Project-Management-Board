package com.Codeline.Project.Management.Board.Responses;

import com.Codeline.Project.Management.Board.Models.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {
    private Long id;

    private String title;

    private String description;

    private Integer section;

    public String getSectionName() {
        return Board.getSectionName(this.section);
    }
}
