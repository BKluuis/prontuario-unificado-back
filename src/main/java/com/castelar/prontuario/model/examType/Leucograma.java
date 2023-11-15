package com.castelar.prontuario.model.examType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Leucograma {
    private int leucocitos;
    private int neutrofilos;
    private int mielocitos;
    private int metamielocitos;
    private int bastonetes;
    private int segmentados;
    private int eosinofilos;
    private int basofilos;
    private int monocitos;
    private int lin_tipicos;
    private int lin_atipicos;
}
