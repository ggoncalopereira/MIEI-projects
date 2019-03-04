/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aebdproject.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JPVS
 */
@Entity
@Table(name = "MEMORYT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memoryt.findAll", query = "SELECT m FROM Memoryt m")
    , @NamedQuery(name = "Memoryt.findByUsername", query = "SELECT m FROM Memoryt m WHERE m.username = :username")
    , @NamedQuery(name = "Memoryt.findBySid", query = "SELECT m FROM Memoryt m WHERE m.sid = :sid")
    , @NamedQuery(name = "Memoryt.findBySessMem", query = "SELECT m FROM Memoryt m WHERE m.sessMem = :sessMem")})
public class Memoryt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 128)
    @Column(name = "USERNAME")
    private String username;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SID")
    private BigDecimal sid;
    @Column(name = "SESS_MEM")
    private BigInteger sessMem;

    public Memoryt() {
    }

    public Memoryt(BigDecimal sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getSid() {
        return sid;
    }

    public void setSid(BigDecimal sid) {
        this.sid = sid;
    }

    public BigInteger getSessMem() {
        return sessMem;
    }

    public void setSessMem(BigInteger sessMem) {
        this.sessMem = sessMem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memoryt)) {
            return false;
        }
        Memoryt other = (Memoryt) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aebdproject.service.Memoryt[ sid=" + sid + " ]";
    }
    
}
