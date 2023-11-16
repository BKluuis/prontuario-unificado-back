package com.castelar.prontuario.model.examType;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
// @Embeddable
public class Leukogram {
    @Id
    @GeneratedValue
    private long id;

    private int leukocyte;
    private int neutrophils;
    private int mielocitos;
    private int metamielocitos;
    private int bastonetes;
    private int segmentados;
    private int eosinophils;
    private int basophils;
    private int monocytes;
    private int lym_tipic;
    private int lym_atipic;
}
