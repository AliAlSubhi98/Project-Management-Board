package com.Codeline.Project.Management.Board.Responses;

import com.Codeline.Project.Management.Board.Models.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {
    private Long id;

    private String title;

    private String description;

    private Integer section;

    private Date updatedDate;
    public String getSectionName() {
        return Board.getSectionName(this.section);
    }
}
