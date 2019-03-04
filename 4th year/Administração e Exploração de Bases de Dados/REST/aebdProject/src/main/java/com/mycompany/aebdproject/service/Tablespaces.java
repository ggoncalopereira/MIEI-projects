/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aebdproject.service;

import java.io.Serializable;
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
@Table(name = "TABLESPACES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablespaces.findAll", query = "SELECT t FROM Tablespaces t")
    , @NamedQuery(name = "Tablespaces.findByTablespace", query = "SELECT t FROM Tablespaces t WHERE t.tablespace = :tablespace")
    , @NamedQuery(name = "Tablespaces.findByTotalSpace", query = "SELECT t FROM Tablespaces t WHERE t.totalSpace = :totalSpace")
    , @NamedQuery(name = "Tablespaces.findByFreeSpace", query = "SELECT t FROM Tablespaces t WHERE t.freeSpace = :freeSpace")
    , @NamedQuery(name = "Tablespaces.findByTotalSpaceMb", query = "SELECT t FROM Tablespaces t WHERE t.totalSpaceMb = :totalSpaceMb")
    , @NamedQuery(name = "Tablespaces.findByUsedSpaceMb", query = "SELECT t FROM Tablespaces t WHERE t.usedSpaceMb = :usedSpaceMb")
    , @NamedQuery(name = "Tablespaces.findByFreeSpaceMb", query = "SELECT t FROM Tablespaces t WHERE t.freeSpaceMb = :freeSpaceMb")
    , @NamedQuery(name = "Tablespaces.findByPctFree", query = "SELECT t FROM Tablespaces t WHERE t.pctFree = :pctFree")})
public class Tablespaces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TABLESPACE")
    private String tablespace;
    @Column(name = "TOTAL_SPACE")
    private BigInteger totalSpace;
    @Column(name = "FREE_SPACE")
    private BigInteger freeSpace;
    @Column(name = "TOTAL_SPACE_MB")
    private BigInteger totalSpaceMb;
    @Column(name = "USED_SPACE_MB")
    private BigInteger usedSpaceMb;
    @Column(name = "FREE_SPACE_MB")
    private BigInteger freeSpaceMb;
    @Column(name = "PCT_FREE")
    private BigInteger pctFree;

    public Tablespaces() {
    }

    public Tablespaces(String tablespace) {
        this.tablespace = tablespace;
    }

    public String getTablespace() {
        return tablespace;
    }

    public void setTablespace(String tablespace) {
        this.tablespace = tablespace;
    }

    public BigInteger getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(BigInteger totalSpace) {
        this.totalSpace = totalSpace;
    }

    public BigInteger getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(BigInteger freeSpace) {
        this.freeSpace = freeSpace;
    }

    public BigInteger getTotalSpaceMb() {
        return totalSpaceMb;
    }

    public void setTotalSpaceMb(BigInteger totalSpaceMb) {
        this.totalSpaceMb = totalSpaceMb;
    }

    public BigInteger getUsedSpaceMb() {
        return usedSpaceMb;
    }

    public void setUsedSpaceMb(BigInteger usedSpaceMb) {
        this.usedSpaceMb = usedSpaceMb;
    }

    public BigInteger getFreeSpaceMb() {
        return freeSpaceMb;
    }

    public void setFreeSpaceMb(BigInteger freeSpaceMb) {
        this.freeSpaceMb = freeSpaceMb;
    }

    public BigInteger getPctFree() {
        return pctFree;
    }

    public void setPctFree(BigInteger pctFree) {
        this.pctFree = pctFree;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tablespace != null ? tablespace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablespaces)) {
            return false;
        }
        Tablespaces other = (Tablespaces) object;
        if ((this.tablespace == null && other.tablespace != null) || (this.tablespace != null && !this.tablespace.equals(other.tablespace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aebdproject.service.Tablespaces[ tablespace=" + tablespace + " ]";
    }
    
}
