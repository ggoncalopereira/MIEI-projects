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
@Table(name = "DATAFILEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datafilest.findAll", query = "SELECT d FROM Datafilest d")
    , @NamedQuery(name = "Datafilest.findByTablespaceName", query = "SELECT d FROM Datafilest d WHERE d.tablespaceName = :tablespaceName")
    , @NamedQuery(name = "Datafilest.findByFileName", query = "SELECT d FROM Datafilest d WHERE d.fileName = :fileName")
    , @NamedQuery(name = "Datafilest.findByBytes", query = "SELECT d FROM Datafilest d WHERE d.bytes = :bytes")})
public class Datafilest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TABLESPACE_NAME")
    private String tablespaceName;
    @Size(max = 513)
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "BYTES")
    private BigInteger bytes;

    public Datafilest() {
    }

    public Datafilest(String tablespaceName) {
        this.tablespaceName = tablespaceName;
    }

    public String getTablespaceName() {
        return tablespaceName;
    }

    public void setTablespaceName(String tablespaceName) {
        this.tablespaceName = tablespaceName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BigInteger getBytes() {
        return bytes;
    }

    public void setBytes(BigInteger bytes) {
        this.bytes = bytes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tablespaceName != null ? tablespaceName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datafilest)) {
            return false;
        }
        Datafilest other = (Datafilest) object;
        if ((this.tablespaceName == null && other.tablespaceName != null) || (this.tablespaceName != null && !this.tablespaceName.equals(other.tablespaceName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aebdproject.service.Datafilest[ tablespaceName=" + tablespaceName + " ]";
    }
    
}
