package model;

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
}
