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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JPVS
 */
@Embeddable
public class CpuusagePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "OSUSER")
    private String osuser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SID")
    private BigInteger sid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERIAL#")
    private BigInteger serial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CPU_USAGE_SECONDS")
    private BigInteger cpuUsageSeconds;

    public CpuusagePK() {
    }

    public CpuusagePK(String osuser, String username, BigInteger sid, BigInteger serial, BigInteger cpuUsageSeconds) {
        this.osuser = osuser;
        this.username = username;
        this.sid = sid;
        this.serial = serial;
        this.cpuUsageSeconds = cpuUsageSeconds;
    }

    public String getOsuser() {
        return osuser;
    }

    public void setOsuser(String osuser) {
        this.osuser = osuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigInteger getSid() {
        return sid;
    }

    public void setSid(BigInteger sid) {
        this.sid = sid;
    }

    public BigInteger getSerial() {
        return serial;
    }

    public void setSerial(BigInteger serial) {
        this.serial = serial;
    }

    public BigInteger getCpuUsageSeconds() {
        return cpuUsageSeconds;
    }

    public void setCpuUsageSeconds(BigInteger cpuUsageSeconds) {
        this.cpuUsageSeconds = cpuUsageSeconds;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (osuser != null ? osuser.hashCode() : 0);
        hash += (username != null ? username.hashCode() : 0);
        hash += (sid != null ? sid.hashCode() : 0);
        hash += (serial != null ? serial.hashCode() : 0);
        hash += (cpuUsageSeconds != null ? cpuUsageSeconds.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpuusagePK)) {
            return false;
        }
        CpuusagePK other = (CpuusagePK) object;
        if ((this.osuser == null && other.osuser != null) || (this.osuser != null && !this.osuser.equals(other.osuser))) {
            return false;
        }
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        if ((this.serial == null && other.serial != null) || (this.serial != null && !this.serial.equals(other.serial))) {
            return false;
        }
        if ((this.cpuUsageSeconds == null && other.cpuUsageSeconds != null) || (this.cpuUsageSeconds != null && !this.cpuUsageSeconds.equals(other.cpuUsageSeconds))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aebdproject.service.CpuusagePK[ osuser=" + osuser + ", username=" + username + ", sid=" + sid + ", serial=" + serial + ", cpuUsageSeconds=" + cpuUsageSeconds + " ]";
    }
    
}
