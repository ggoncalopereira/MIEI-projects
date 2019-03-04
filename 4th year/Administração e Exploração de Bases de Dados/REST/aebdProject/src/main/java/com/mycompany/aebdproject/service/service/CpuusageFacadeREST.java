/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aebdproject.service.service;

import com.mycompany.aebdproject.service.Cpuusage;
import com.mycompany.aebdproject.service.CpuusagePK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author JPVS
 */
@Stateless
@Path("com.mycompany.aebdproject.service.cpuusage")
public class CpuusageFacadeREST extends AbstractFacade<Cpuusage> {

    @PersistenceContext(unitName = "com.mycompany_aebdProject_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private CpuusagePK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;osuser=osuserValue;username=usernameValue;sid=sidValue;serial=serialValue;cpuUsageSeconds=cpuUsageSecondsValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.mycompany.aebdproject.service.CpuusagePK key = new com.mycompany.aebdproject.service.CpuusagePK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> osuser = map.get("osuser");
        if (osuser != null && !osuser.isEmpty()) {
            key.setOsuser(osuser.get(0));
        }
        java.util.List<String> username = map.get("username");
        if (username != null && !username.isEmpty()) {
            key.setUsername(username.get(0));
        }
        java.util.List<String> sid = map.get("sid");
        if (sid != null && !sid.isEmpty()) {
            key.setSid(new java.math.BigInteger(sid.get(0)));
        }
        java.util.List<String> serial = map.get("serial");
        if (serial != null && !serial.isEmpty()) {
            key.setSerial(new java.math.BigInteger(serial.get(0)));
        }
        java.util.List<String> cpuUsageSeconds = map.get("cpuUsageSeconds");
        if (cpuUsageSeconds != null && !cpuUsageSeconds.isEmpty()) {
            key.setCpuUsageSeconds(new java.math.BigInteger(cpuUsageSeconds.get(0)));
        }
        return key;
    }

    public CpuusageFacadeREST() {
        super(Cpuusage.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cpuusage entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Cpuusage entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.mycompany.aebdproject.service.CpuusagePK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Cpuusage find(@PathParam("id") PathSegment id) {
        com.mycompany.aebdproject.service.CpuusagePK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cpuusage> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cpuusage> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
