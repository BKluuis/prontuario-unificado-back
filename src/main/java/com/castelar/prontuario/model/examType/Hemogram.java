package com.castelar.prontuario.model.examType;

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
    private long id;
    
    @OneToOne
    // @Embedded
    private Erythogram eritograma;
    @OneToOne
    // @Embedded
    private Leukogram leucograma;
    @OneToOne
    // @Embedded
    private Thrombogram plaquetograma;
}
