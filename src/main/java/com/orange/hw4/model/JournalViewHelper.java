package com.orange.hw4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class JournalViewHelper {
    private Long id;
    private LocalDate lessonDate;
    private User user1;
    private Double mark1;
    private User user2;
    private Double mark2;
}
