/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aebdproject.service;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByProcess", query = "SELECT u FROM Users u WHERE u.process = :process")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findBySid", query = "SELECT u FROM Users u WHERE u.sid = :sid")
    , @NamedQuery(name = "Users.findByOsuser", query = "SELECT u FROM Users u WHERE u.osuser = :osuser")
    , @NamedQuery(name = "Users.findByTerminal", query = "SELECT u FROM Users u WHERE u.terminal = :terminal")
    , @NamedQuery(name = "Users.findByMachine", query = "SELECT u FROM Users u WHERE u.machine = :machine")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 24)
    @Column(name = "PROCESS")
    private String process;
    @Size(max = 128)
    @Column(name = "USERNAME")
    private String username;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SID")
    private BigDecimal sid;
    @Size(max = 128)
    @Column(name = "OSUSER")
    private String osuser;
    @Size(max = 30)
    @Column(name = "TERMINAL")
    private String terminal;
    @Size(max = 64)
    @Column(name = "MACHINE")
    private String machine;

    public Users() {
    }

    public Users(BigDecimal sid) {
        this.sid = sid;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
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

    public String getOsuser() {
        return osuser;
    }

    public void setOsuser(String osuser) {
        this.osuser = osuser;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.aebdproject.service.Users[ sid=" + sid + " ]";
    }
    
}
