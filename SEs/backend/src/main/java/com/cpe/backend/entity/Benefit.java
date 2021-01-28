package com.cpe.backend.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="BENEFIT")
public class Benefit {
    
    @Id
    @SequenceGenerator(name="BENEFIT_SEQ",sequenceName="BENEFIT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BENEFIT_SEQ")
    @Column(name="BENEFIT_ID",unique = true, nullable = true)

    private @NonNull Long id;
    private @NonNull String name;

    public void setName(String name) {
        this.name=name;
	}

}
