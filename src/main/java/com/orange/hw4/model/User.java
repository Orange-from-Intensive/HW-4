package com.orange.hw4.model;

import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.LocalDate;
import java.util.UUID;

@Value
@NonFinal
public class User {
    UUID id;
    String name;
    String surname;
    LocalDate birthDate;
    String team;
}
