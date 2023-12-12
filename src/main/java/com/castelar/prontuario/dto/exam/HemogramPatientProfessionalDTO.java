package com.castelar.prontuario.dto.exam;

import java.time.LocalDateTime;

public record HemogramPatientProfessionalDTO(String patientName, String professionalName, LocalDateTime date, ErythogramDTO erythogram, LeukogramDTO leukogram, ThrombogramDTO thrombogram) {
    
}
