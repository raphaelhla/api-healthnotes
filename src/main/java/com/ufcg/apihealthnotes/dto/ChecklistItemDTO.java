package com.ufcg.apihealthnotes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChecklistItemDTO {

    private String description;
    private boolean marked;

    public ChecklistItemDTO() {
    }   

    public ChecklistItemDTO(String description, boolean marked) {
        this.description = description;
        this.marked = marked;
    }
}
