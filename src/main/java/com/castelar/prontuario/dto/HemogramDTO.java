package com.castelar.prontuario.dto;

// import com.castelar.prontuario.model.examType.Erythogram;
// import com.castelar.prontuario.model.examType.Leukogram;
// import com.castelar.prontuario.model.examType.Thrombogram;

public record HemogramDTO(ErythogramDTO erythogram, LeukogramDTO leukogram, ThrombogramDTO thombogram) {
    
}
