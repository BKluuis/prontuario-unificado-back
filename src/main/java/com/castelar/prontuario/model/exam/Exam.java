package com.castelar.prontuario.model.exam;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class Exam {
    private long id;
    private String exam_name;
    private LocalDateTime exam_date;
    private long exam_doctor_id;
}
