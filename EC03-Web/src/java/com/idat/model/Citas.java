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
@Table(name = "citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c")
    , @NamedQuery(name = "Citas.findByNrocita", query = "SELECT c FROM Citas c WHERE c.nrocita = :nrocita")
    , @NamedQuery(name = "Citas.findByCodmed", query = "SELECT c FROM Citas c WHERE c.codmed = :codmed")
    , @NamedQuery(name = "Citas.findByNompac", query = "SELECT c FROM Citas c WHERE c.nompac = :nompac")
    , @NamedQuery(name = "Citas.findByTipo", query = "SELECT c FROM Citas c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Citas.findByPago", query = "SELECT c FROM Citas c WHERE c.pago = :pago")})
public class Citas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nrocita")
    private Integer nrocita;
    @Column(name = "codmed")
    private String codmed;
    @Column(name = "nompac")
    private String nompac;
    @Column(name = "tipo")
    private Integer tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pago")
    private BigDecimal pago;

    public Citas() {
    }

    public Citas(Integer nrocita) {
        this.nrocita = nrocita;
    }

    public Integer getNrocita() {
        return nrocita;
    }

    public void setNrocita(Integer nrocita) {
        this.nrocita = nrocita;
    }

    public String getCodmed() {
        return codmed;
    }

    public void setCodmed(String codmed) {
        this.codmed = codmed;
    }

    public String getNompac() {
        return nompac;
    }

    public void setNompac(String nompac) {
        this.nompac = nompac;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPago() {
        return pago;
    }

    public void setPago(BigDecimal pago) {
        this.pago = pago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrocita != null ? nrocita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.nrocita == null && other.nrocita != null) || (this.nrocita != null && !this.nrocita.equals(other.nrocita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.idat.model.Citas[ nrocita=" + nrocita + " ]";
    }
    
}
