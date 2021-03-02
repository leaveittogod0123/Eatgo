package com.playground.noyo0123.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String name;

    @Setter
    @NotEmpty
    private String date;

    @Setter
    @NotEmpty
    private String time;

    @Setter
    @NotNull
    private Integer partySize;
}
