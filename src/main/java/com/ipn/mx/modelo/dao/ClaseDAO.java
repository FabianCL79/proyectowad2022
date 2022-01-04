/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Clase;
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
public class ClaseDAO {
    
    public void create(Clase dto) {
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

    public void update(Clase dto) {
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

    public void delete(Clase dto) {
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

    public Clase read(Clase dto) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try {
            tx.begin();
            dto = (s.get(dto.getClass(), dto.getIdClase()));
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
            Query q = s.createQuery("from Clase c order by c.idClase");
            lista = q.list();
            /*for(Clase c: (List<Clase>) q.list()){
                Clase dto = new Clase();
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
        ClaseDAO dao = new ClaseDAO();
        Clase dto = new Clase();
        //dto.setNombreClase("Clase para eliminar");
        //dto.setDescripcionClase("Descripcion Clase para eliminar");
        //dto.setIdClase(3);
        //dao.update(dto);
        //dao.create(dto);
        //dto = dao.read(dto);
        //dao.delete(dto);
        //System.out.println(dao.read(dto));
        System.out.println(dao.readAll());
    }
}

