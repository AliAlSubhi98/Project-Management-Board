package com.Codeline.Project.Management.Board.Requests;

import com.Codeline.Project.Management.Board.Models.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {
    Integer section;
    private Long id;
    private String title;
    private String description;
    private Date updatedDate = new Date();

    public Card convertToCard() {
        Card card = new Card();
        card.setId(this.getId());
        card.setTitle(this.getTitle());
        card.setDescription(this.getDescription());
        card.setSection(this.getSection());
        card.setUpdatedDate(this.updatedDate);

        return card;
    }
}
