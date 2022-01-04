/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author cerva
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "Clase")
public class Clase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClase;

    private String grupo;
    private String plataforma;
    private String enlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfesor")
    private Profesor idProfesor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMateria")
    private Materia idMateria;
    
    @Transient
    private int idProf;
    
    @Transient
    private int idMat;

}
