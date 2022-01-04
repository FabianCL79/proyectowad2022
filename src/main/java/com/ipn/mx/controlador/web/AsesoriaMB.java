/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.AsesoriaDAO;
import com.ipn.mx.modelo.dao.MateriaDAO;
import com.ipn.mx.modelo.dao.ProfesorDAO;
import com.ipn.mx.modelo.entidades.Asesoria;
import com.ipn.mx.modelo.entidades.Materia;
import com.ipn.mx.modelo.entidades.Profesor;
import com.ipn.mx.utilerias.EnviarMail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author cerva
 */
@ManagedBean(name = "asesoriaMB")
@SessionScoped
public class AsesoriaMB extends BaseBean implements Serializable {
    
    private final AsesoriaDAO dao = new AsesoriaDAO();
    private final ProfesorDAO profDAO = new ProfesorDAO();
    private final MateriaDAO matDAO = new MateriaDAO();

    private Asesoria dto;
    private List<Asesoria> listaAsesoria;
    private List<Profesor> listaProfesor;
    private List<Materia> listaMateria;
    
    private String mail;
    
    /**
     * Creates a new instance of AsesoriaMB
     */
    public AsesoriaMB() {
    }

    public Asesoria getDto() {
        return dto;
    }

    public void setDto(Asesoria dto) {
        this.dto = dto;
    }

    public List<Asesoria> getListaAsesoria() {
        return listaAsesoria;
    }

    public void setListaAsesoria(List<Asesoria> listaAsesoria) {
        this.listaAsesoria = listaAsesoria;
    }

    public List<Profesor> getListaProfesor() {
        return listaProfesor;
    }

    public void setListaProfesor(List<Profesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
    }

    public List<Materia> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    @PostConstruct
    public void init() {
        listaProfesor = new ArrayList<>();
        listaMateria = new ArrayList<>();
        listaAsesoria = new ArrayList<>();
        
        listaProfesor = profDAO.readAll();
        listaMateria = matDAO.readAll();
        listaAsesoria = dao.readAll();
    }
    
    public String prepareAdd() {
        dto = new Asesoria();
        setAccion(ACC_CREAR);
        return "/asesoria/asesoriaForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/asesoria/asesoriaForm?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/asesoria/listadoAsesorias?faces-redirect=true";
    }

    public Boolean validate() {
        boolean valido = true;
        //Validaciones
        return valido;
    }
    
    public String add() {
        boolean valido = validate();
        if (valido) {
            
            Profesor prof = new Profesor();
            prof.setIdProfesor(dto.getIdProf());
            prof = profDAO.read(prof);
            dto.setIdProfesor(prof);
            
            Materia mat = new Materia();
            mat.setIdMateria(dto.getIdMat());
            mat = matDAO.read(mat);
            dto.setIdMateria(mat);
            
            dao.create(dto);
            if (valido) {
                return prepareIndex();
            } else {
                return prepareAdd();
            }
        }
        return prepareAdd();
    }

    public String update() {
        boolean valido = validate();
        if (valido) {
                        
            Profesor prof = new Profesor();
            prof.setIdProfesor(dto.getIdProf());
            prof = profDAO.read(prof);
            dto.setIdProfesor(prof);
            
            Materia mat = new Materia();
            mat.setIdMateria(dto.getIdMat());
            mat = matDAO.read(mat);
            dto.setIdMateria(mat);
            
            dao.update(dto);
            if (valido) {
                return prepareIndex();
            } else {
                return prepareUpdate();
            }
        }
        return prepareUpdate();
    }

    public String delete() {
        dao.delete(dto);
        return prepareIndex();
    }

    public void seleccionarAsesoria(ActionEvent event) {
        String claveSel = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("claveSel");
        dto = new Asesoria();
        dto.setIdAsesoria(Integer.parseInt(claveSel));
        try {
            dto = dao.read(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String enviarCorreo() {
        EnviarMail email = new EnviarMail();
        String asunto = "Datos Asesorias";
        String mensaje = String.valueOf(dao.readAll());
        email.enviarCorreo(mail, asunto, mensaje);
        return "/asesoria/listadoAsesorias?faces-redirect=true";
    }

}

