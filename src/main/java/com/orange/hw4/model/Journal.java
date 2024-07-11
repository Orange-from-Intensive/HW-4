package com.orange.hw4.model;

import java.time.LocalDate;

public class Journal {
    private Long id;
    private LocalDate lessonDate;
    private Long userOneId;
    private Double userOneMark;
    private Long userTwoId;
    private Double userTwoMark;

    public Journal(Long id, LocalDate lessonDate, Long userOneId, Double userOneMark, Long userTwoId, Double userTwoMark) {
        this.id = id;
        this.lessonDate = lessonDate;
        this.userOneId = userOneId;
        this.userOneMark = userOneMark;
        this.userTwoId = userTwoId;
        this.userTwoMark = userTwoMark;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public Long getUserOneId() {
        return userOneId;
    }

    public Double getUserOneMark() {
        return userOneMark;
    }

    public Long getUserTwoId() {
        return userTwoId;
    }

    public Double getUserTwoMark() {
        return userTwoMark;
    }
}
