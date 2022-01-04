/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Profesor;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author cerva
 */
public class ProfesorDAO {
    
    public void create(Profesor dto) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try {
            tx.begin();
            s.save(dto);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public void update(Profesor dto) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try {
            tx.begin();
            s.update(dto);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public void delete(Profesor dto) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try {
            tx.begin();
            s.delete(dto);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    public Profesor read(Profesor dto) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try {
            tx.begin();
            dto = (s.get(dto.getClass(), dto.getIdProfesor()));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
        return dto;
    }

    public List readAll() {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        List lista = new ArrayList();
        try {
            tx.begin();
            Query q = s.createQuery("from Profesor c order by c.idProfesor");
            lista = q.list();
            /*for(Profesor c: (List<Profesor>) q.list()){
                Profesor dto = new Profesor();
                dto = (c);
                lista.add(dto);
            }*/
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
        return lista;
    }
    
    public static void main(String[] args){
        ProfesorDAO dao = new ProfesorDAO();
        //Profesor dto = new Profesor();
        //dto.setNombreProfesor("Profesor Modificado");
        //dto.setCorreo("correo@cambio.com");
        //dto.setIdProfesor(5);
        //dao.update(dto);
        //dao.create(dto);
        //dto = dao.read(dto);
        //dao.delete(dto);
        //System.out.println(dao.read(dto));
        String mensaje = String.valueOf(dao.readAll());
        System.out.println(mensaje);
        //System.out.println(dao.readAll());
    }
}
