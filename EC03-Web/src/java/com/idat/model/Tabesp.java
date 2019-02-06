/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idat.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tabesp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tabesp.findAll", query = "SELECT t FROM Tabesp t")
    , @NamedQuery(name = "Tabesp.findByCode", query = "SELECT t FROM Tabesp t WHERE t.code = :code")
    , @NamedQuery(name = "Tabesp.findByNome", query = "SELECT t FROM Tabesp t WHERE t.nome = :nome")
    , @NamedQuery(name = "Tabesp.findByCosto", query = "SELECT t FROM Tabesp t WHERE t.costo = :costo")})
public class Tabesp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Column(name = "nome")
    private String nome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo")
    private BigDecimal costo;

    public Tabesp() {
    }

    public Tabesp(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabesp)) {
            return false;
        }
        Tabesp other = (Tabesp) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idat.model.Tabesp[ code=" + code + " ]";
    }
    
}
