package com.orange.hw4.model;

import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.LocalDate;
@Value
@NonFinal
public class User {
    Long id;
    String name;
    String surname;
    LocalDate birthDate;
    String team;
}
