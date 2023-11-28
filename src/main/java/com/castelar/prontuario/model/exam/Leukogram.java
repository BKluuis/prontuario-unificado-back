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
// @Embeddable
public class Leukogram {
    @Id
    @GeneratedValue
    private Long id;

    private Integer leukocyte;
    private Integer neutrophils;
    private Integer myelocytes;
    private Integer metamyelocytes;
    private Integer band_neutrophils;
    private Integer segmented_neutrophils;
    private Integer eosinophils;
    private Integer basophils;
    private Integer monocytes;
    private Integer lym_tipic;
    private Integer lym_atipic;
}
