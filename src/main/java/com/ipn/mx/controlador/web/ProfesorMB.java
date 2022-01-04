/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.ProfesorDAO;
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
@ManagedBean(name = "profesorMB")
@SessionScoped
public class ProfesorMB extends BaseBean implements Serializable {
    
    private final ProfesorDAO dao = new ProfesorDAO();

    private Profesor dto;
    private List<Profesor> listaProfesor;
    
    private String mail;
    
    /**
     * Creates a new instance of ProfesorMB
     */
    public ProfesorMB() {
    }

    public Profesor getDto() {
        return dto;
    }

    public void setDto(Profesor dto) {
        this.dto = dto;
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
        listaProfesor = dao.readAll();
    }
    
    public String prepareAdd() {
        dto = new Profesor();
        setAccion(ACC_CREAR);
        return "/profesor/profesorForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/profesor/profesorForm?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/profesor/listadoProfesores?faces-redirect=true";
    }

    public Boolean validate() {
        boolean valido = true;
        //Validaciones
        return valido;
    }
    
    public String add() {
        boolean valido = validate();
        if (valido) {
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

    public void seleccionarProfesor(ActionEvent event) {
        String claveSel = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("claveSelProf");
        dto = new Profesor();
        dto.setIdProfesor(Integer.parseInt(claveSel));
        try {
            dto = dao.read(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String enviarCorreo() {
        EnviarMail email = new EnviarMail();
        //destinatario = "correo@destinatario.com";
        String asunto = "Datos Profesores";
        String mensaje = String.valueOf(dao.readAll());
        email.enviarCorreo(mail, asunto, mensaje);
        return "/profesor/listadoProfesores?faces-redirect=true";
    }

}
