/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.MateriaDAO;
import com.ipn.mx.modelo.entidades.Materia;
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
@ManagedBean(name = "materiaMB")
@SessionScoped
public class MateriaMB extends BaseBean implements Serializable {
    
    private final MateriaDAO dao = new MateriaDAO();

    private Materia dto;
    private List<Materia> listaMateria;
    
    private String mail;
    
    /**
     * Creates a new instance of MateriaMB
     */
    public MateriaMB() {
    }

    public Materia getDto() {
        return dto;
    }

    public void setDto(Materia dto) {
        this.dto = dto;
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
        listaMateria = new ArrayList<>();
        listaMateria = dao.readAll();
    }
    
    public String prepareAdd() {
        dto = new Materia();
        setAccion(ACC_CREAR);
        return "/materia/materiaForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);
        return "/materia/materiaForm?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/materia/listadoMaterias?faces-redirect=true";
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

    public void seleccionarMateria(ActionEvent event) {
        String claveSel = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("claveSel");
        dto = new Materia();
        dto.setIdMateria(Integer.parseInt(claveSel));
        try {
            dto = dao.read(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String enviarCorreo() {
        EnviarMail email = new EnviarMail();
        String asunto = "Datos Materias";
        String mensaje = String.valueOf(dao.readAll());
        email.enviarCorreo(mail, asunto, mensaje);
        return "/materia/listadoMaterias?faces-redirect=true";
    }

}
