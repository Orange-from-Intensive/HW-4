package com.orange.hw4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Journal {
    private Long id;
    private LocalDate lessonDate;
    private Long user1_id;
    private Long user2_id;
    private Double user1_mark;
    private Double user2_mark;
}
