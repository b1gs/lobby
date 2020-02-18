package com.example.lobby.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerCards {

    private Set<Card> prikup;

    private Set<Card> cards;

}
