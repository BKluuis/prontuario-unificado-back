package com.castelar.prontuario.model.exam;

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
public class Thrombogram {
    @Id
    @GeneratedValue
    private Long id;

    //Contagem de plaquetas
    private Integer platelet;
    //volume total de plaquetas num determinado volume de sangue
    private Integer platelocryt;
    //Amplitude de variação do tamanho das plaquetas
    private Integer pdw;
    //volume plaquetário médio
    private Integer vmp;
}
