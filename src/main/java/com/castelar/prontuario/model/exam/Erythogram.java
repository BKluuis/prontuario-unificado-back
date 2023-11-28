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
public class Erythogram{
   @Id
   @GeneratedValue
   private Long id;

   // Representa a contagem de hemácias (também conhecidas como eritrócitos ou glóbulos vermelhos).
   private Integer redCells;
   // A hemoglobina é uma proteina que fica dentro da hemácia
   private Integer hemoglobin; 
   // Porcetagem de hemácias no sangue
   private Integer hematocrit;
   // Volume Corpuscular Médio (VCM), um índice hematimétrico presente no hemograma que indica a média do tamanho das hemácias
   private Integer VCM;
   // HCM (hemoglobina corpuscular média) é o peso da hemoglobina dentro das hemácias
   private Integer HCM;
   // Concentração da Hemoglobina Corpuscular Média (CHCM), um índice que serve para verificar a quantidade de hemoglobina presente nas hemácias
   private Integer CHCM;
   // Red Cell Distribution Width (RDW), um parâmetro que avalia a variação de tamanho entre as hemácias
   private Integer RDW;
}
