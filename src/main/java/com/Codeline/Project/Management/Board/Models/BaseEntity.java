package com.Codeline.Project.Management.Board.Models;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {
    Boolean isActive = true;
    @CreatedDate
    Date createdDate = new Date();

    @UpdateTimestamp
    Date updatedDate;
}