package com.castelar.prontuario.model.exam;

import com.castelar.prontuario.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hemogram {
    @Id
    @GeneratedValue
    @Column(name = "hemogram_id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private User professional;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToOne(cascade = CascadeType.ALL)
    private Erythogram erythogram;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Leukogram leukogram;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Thrombogram thrombogram;

    @Column(length = 512, name = "hemogram_comment")
    private String comment = "";
}
