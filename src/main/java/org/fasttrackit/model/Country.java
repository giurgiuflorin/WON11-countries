package org.fasttrackit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Country {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @Column
    private String capital;
    @Column
    private long population;
    @Column
    private long area;
    @Column
    private  String continent;
    @Transient
    private  List<String> neighbours;

}
