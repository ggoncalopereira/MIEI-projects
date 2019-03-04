/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aebdproject.service;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JPVS
 */
@Entity
@Table(name = "CPUUSAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cpuusage.findAll", query = "SELECT c FROM Cpuusage c")
    , @NamedQuery(name = "Cpuusage.findByOsuser", query = "SELECT c FROM Cpuusage c WHERE c.cpuusagePK.osuser = :osuser")
    , @NamedQuery(name = "Cpuusage.findByUsername", query = "SELECT c FROM Cpuusage c WHERE c.cpuusagePK.username = :username")
    , @NamedQuery(name = "Cpuusage.findBySid", query = "SELECT c FROM Cpuusage c WHERE c.cpuusagePK.sid = :sid")
    , @NamedQuery(name = "Cpuusage.findBySerial", query = "SELECT c FROM Cpuusage c WHERE c.cpuusagePK.serial = :serial")
    , @NamedQuery(name = "Cpuusage.findByCpuUsageSeconds", query = "SELECT c FROM Cpuusage c WHERE c.cpuusagePK.cpuUsageSeconds = :cpuUsageSeconds")})
public class Cpuusage implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CpuusagePK cpuusagePK;

    public Cpuusage() {
    }

    public Cpuusage(CpuusagePK cpuusagePK) {
        this.cpuusagePK = cpuusagePK;
    }

    public Cpuusage(String osuser, String username, BigInteger sid, BigInteger serial, BigInteger cpuUsageSeconds) {
        this.cpuusagePK = new CpuusagePK(osuser, username, sid, serial, cpuUsageSeconds);
    }

    public CpuusagePK getCpuusagePK() {
        return cpuusagePK;
    }

    public void setCpuusagePK(CpuusagePK cpuusagePK) {
        this.cpuusagePK = cpuusagePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpuusagePK != null ? cpuusagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cpuusage)) {
            return false;
        }
        Cpuusage other = (Cpuusage) object;
        if ((this.cpuusagePK == null && other.cpuusagePK != null) || (this.cpuusagePK != null && !this.cpuusagePK.equals(other.cpuusagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aebdproject.service.Cpuusage[ cpuusagePK=" + cpuusagePK + " ]";
    }
    
}
