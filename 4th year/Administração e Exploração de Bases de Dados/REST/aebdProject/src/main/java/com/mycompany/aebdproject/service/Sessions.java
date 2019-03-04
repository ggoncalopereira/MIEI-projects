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
@Table(name = "SESSIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sessions.findAll", query = "SELECT s FROM Sessions s")
    , @NamedQuery(name = "Sessions.findByUsername", query = "SELECT s FROM Sessions s WHERE s.username = :username")
    , @NamedQuery(name = "Sessions.findByOsuser", query = "SELECT s FROM Sessions s WHERE s.osuser = :osuser")
    , @NamedQuery(name = "Sessions.findBySid", query = "SELECT s FROM Sessions s WHERE s.sid = :sid")
    , @NamedQuery(name = "Sessions.findBySerial", query = "SELECT s FROM Sessions s WHERE s.serial = :serial")
    , @NamedQuery(name = "Sessions.findBySpid", query = "SELECT s FROM Sessions s WHERE s.spid = :spid")
    , @NamedQuery(name = "Sessions.findByStatus", query = "SELECT s FROM Sessions s WHERE s.status = :status")
    , @NamedQuery(name = "Sessions.findByServiceName", query = "SELECT s FROM Sessions s WHERE s.serviceName = :serviceName")
    , @NamedQuery(name = "Sessions.findByModule", query = "SELECT s FROM Sessions s WHERE s.module = :module")
    , @NamedQuery(name = "Sessions.findByMachine", query = "SELECT s FROM Sessions s WHERE s.machine = :machine")
    , @NamedQuery(name = "Sessions.findByProgram", query = "SELECT s FROM Sessions s WHERE s.program = :program")
    , @NamedQuery(name = "Sessions.findByLogonTime", query = "SELECT s FROM Sessions s WHERE s.logonTime = :logonTime")
    , @NamedQuery(name = "Sessions.findByLastCallEtSecs", query = "SELECT s FROM Sessions s WHERE s.lastCallEtSecs = :lastCallEtSecs")})
public class Sessions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 128)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 128)
    @Column(name = "OSUSER")
    private String osuser;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SID")
    private BigDecimal sid;
    @Column(name = "SERIAL#")
    private BigInteger serial;
    @Size(max = 24)
    @Column(name = "SPID")
    private String spid;
    @Size(max = 8)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 64)
    @Column(name = "SERVICE_NAME")
    private String serviceName;
    @Size(max = 64)
    @Column(name = "MODULE")
    private String module;
    @Size(max = 64)
    @Column(name = "MACHINE")
    private String machine;
    @Size(max = 48)
    @Column(name = "PROGRAM")
    private String program;
    @Size(max = 29)
    @Column(name = "LOGON_TIME")
    private String logonTime;
    @Column(name = "LAST_CALL_ET_SECS")
    private BigInteger lastCallEtSecs;

    public Sessions() {
    }

    public Sessions(BigDecimal sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOsuser() {
        return osuser;
    }

    public void setOsuser(String osuser) {
        this.osuser = osuser;
    }

    public BigDecimal getSid() {
        return sid;
    }

    public void setSid(BigDecimal sid) {
        this.sid = sid;
    }

    public BigInteger getSerial() {
        return serial;
    }

    public void setSerial(BigInteger serial) {
        this.serial = serial;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getLogonTime() {
        return logonTime;
    }

    public void setLogonTime(String logonTime) {
        this.logonTime = logonTime;
    }

    public BigInteger getLastCallEtSecs() {
        return lastCallEtSecs;
    }

    public void setLastCallEtSecs(BigInteger lastCallEtSecs) {
        this.lastCallEtSecs = lastCallEtSecs;
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
        if (!(object instanceof Sessions)) {
            return false;
        }
        Sessions other = (Sessions) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aebdproject.service.Sessions[ sid=" + sid + " ]";
    }
    
}
