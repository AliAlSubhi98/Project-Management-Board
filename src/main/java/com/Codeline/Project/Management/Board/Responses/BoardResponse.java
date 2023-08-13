package com.Codeline.Project.Management.Board.Responses;

import com.Codeline.Project.Management.Board.Models.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

    private Long id;
    private String title;

    private Map<Integer, String> columns;
    //private List<Card> cards;

}
