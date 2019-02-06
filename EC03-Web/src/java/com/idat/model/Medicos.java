/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idat.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author crowe
 */
@Entity
@Table(name = "medicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicos.findAll", query = "SELECT m FROM Medicos m")
    , @NamedQuery(name = "Medicos.findByCodmed", query = "SELECT m FROM Medicos m WHERE m.codmed = :codmed")
    , @NamedQuery(name = "Medicos.findByCodes", query = "SELECT m FROM Medicos m WHERE m.codes = :codes")
    , @NamedQuery(name = "Medicos.findByNombre", query = "SELECT m FROM Medicos m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Medicos.findByColegiatura", query = "SELECT m FROM Medicos m WHERE m.colegiatura = :colegiatura")})
public class Medicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codmed")
    private String codmed;
    @Column(name = "codes")
    private String codes;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "colegiatura")
    private Integer colegiatura;

    public Medicos() {
    }

    public Medicos(String codmed) {
        this.codmed = codmed;
    }

    public String getCodmed() {
        return codmed;
    }

    public void setCodmed(String codmed) {
        this.codmed = codmed;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(Integer colegiatura) {
        this.colegiatura = colegiatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmed != null ? codmed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicos)) {
            return false;
        }
        Medicos other = (Medicos) object;
        if ((this.codmed == null && other.codmed != null) || (this.codmed != null && !this.codmed.equals(other.codmed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idat.model.Medicos[ codmed=" + codmed + " ]";
    }
    
}
