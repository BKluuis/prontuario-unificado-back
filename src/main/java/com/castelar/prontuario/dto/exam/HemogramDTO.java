package com.castelar.prontuario.dto.exam;

public record HemogramDTO(ErythogramDTO erythogram, LeukogramDTO leukogram, ThrombogramDTO thrombogram, String comment) {
    
}
