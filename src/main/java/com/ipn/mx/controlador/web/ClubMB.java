/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.ipn.mx.controlador.web;

import static com.ipn.mx.controlador.web.BaseBean.ACC_ACTUALIZAR;
import static com.ipn.mx.controlador.web.BaseBean.ACC_CREAR;
import com.ipn.mx.modelo.dao.ClubDAO;
import com.ipn.mx.modelo.dao.MateriaDAO;
import com.ipn.mx.modelo.dao.ProfesorDAO;
import com.ipn.mx.modelo.entidades.Club;
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
@ManagedBean(name = "clubMB")
@SessionScoped
public class ClubMB extends BaseBean implements Serializable {
    
    private final ClubDAO dao = new ClubDAO();
    private final ProfesorDAO profDAO = new ProfesorDAO();

    private Club dto;
    private List<Club> listaClub;
    private List<Profesor> listaProfesor;
    
    private String mail;
    
    /**
     * Creates a new instance of ClubMB
     */
    public ClubMB() {
    }

    public Club getDto() {
        return dto;
    }

    public void setDto(Club dto) {
        this.dto = dto;
    }

    public List<Club> getListaClub() {
        return listaClub;
    }

    public void setListaClub(List<Club> listaClub) {
        this.listaClub = listaClub;
    }

    public List<Profesor> getListaProfesor() {
        return listaProfesor;
    }

    public void setListaProfesor(List<Profesor> listaProfesor) {
        this.listaProfesor = listaProfesor;
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
        listaClub = new ArrayList<>();
        
        listaProfesor = profDAO.readAll();
        listaClub = dao.readAll();
    }
    
    public String prepareAdd() {
        dto = new Club();
        setAccion(ACC_CREAR);
        return "/club/clubForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/club/clubForm?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/club/listadoClubs?faces-redirect=true";
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

    public void seleccionarClub(ActionEvent event) {
        String claveSel = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("claveSel");
        dto = new Club();
        dto.setIdClub(Integer.parseInt(claveSel));
        try {
            dto = dao.read(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String enviarCorreo() {
        EnviarMail email = new EnviarMail();
        String asunto = "Datos Clubs";
        String mensaje = String.valueOf(dao.readAll());
        email.enviarCorreo(mail, asunto, mensaje);
        return "/club/listadoClubs?faces-redirect=true";
    }

}


