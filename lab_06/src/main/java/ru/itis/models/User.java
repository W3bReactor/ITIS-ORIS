package ru.itis.models;


import lombok.*;


/*
    Посмотреть аннотации
*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
}

