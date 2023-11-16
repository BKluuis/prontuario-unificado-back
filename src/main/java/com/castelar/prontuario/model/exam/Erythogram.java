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
public class Erythogram {
   @Id
   @GeneratedValue
   private Long id;

   //Contagem de hemácias
   private int redCells;
   //
   private int hemoglobin; 
   //
   private int hematocrit;
   //
   private int VCM;
   //
   private int HCM;
   //
   private int CHCM;
   //
   private int RDW;
}
