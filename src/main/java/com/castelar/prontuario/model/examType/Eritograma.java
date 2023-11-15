package com.castelar.prontuario.model.examType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Eritograma {
   private int hemacias;
   private int hemoglobina; 
   private int hematocrito;
   private int VCM;
   private int HCM;
   private int CHCM;
   private int RDW;
}
