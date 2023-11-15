package com.castelar.prontuario.model.examType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hemograma {
    private Eritograma eritograma;
    private Leucograma leucograma;
    private Plaquetograma plaquetograma;
}
