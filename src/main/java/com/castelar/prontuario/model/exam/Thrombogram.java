package com.castelar.prontuario.model.exam;

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
public class Thrombogram {
    @Id
    @GeneratedValue
    private Long id;

    //Contagem de plaquetas
    private int platelet;
    //volume total de plaquetas num determinado volume de sangue
    private int platelocryt;
    //Amplitude de variação do tamanho das plaquetas
    private int pdw;
    //volume plaquetário médio
    private int vmp;
}
