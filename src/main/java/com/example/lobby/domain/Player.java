package com.example.lobby.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GenericGenerator(
            name = "ID_GENERATOR",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ID_SEQ")
            }
    )
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @Column(name = "username" , nullable = false )
    private String username;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

}
