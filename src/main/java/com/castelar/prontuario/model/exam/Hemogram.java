package com.castelar.prontuario.model.exam;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hemogram {
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    // @Embedded
    private Erythogram erythogram;
    @OneToOne(cascade = CascadeType.ALL)
    // @Embedded
    private Leukogram leukogram;
    @OneToOne(cascade = CascadeType.ALL)
    // @Embedded
    private Thrombogram thrombogram;
}
