package com.castelar.prontuario.model.examType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plaquetograma {
    //Contagem de plaquetas
    private int plaquetas;
    //volume total de plaquetas num determinado volume de sangue
    private int plaquetocrito;
    //Amplitude de variação do tamanho das plaquetas
    private int pdw;
    //volume plaquetário médio
    private int vmp;
}
