/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Asesoria;
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
public class AsesoriaDAO {
    
    public void create(Asesoria dto) {
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

    public void update(Asesoria dto) {
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

    public void delete(Asesoria dto) {
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

    public Asesoria read(Asesoria dto) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try {
            tx.begin();
            dto = (s.get(dto.getClass(), dto.getIdAsesoria()));
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
            Query q = s.createQuery("from Asesoria c order by c.idAsesoria");
            lista = q.list();
            /*for(Asesoria c: (List<Asesoria>) q.list()){
                Asesoria dto = new Asesoria();
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
        AsesoriaDAO dao = new AsesoriaDAO();
        Asesoria dto = new Asesoria();
        //dto.setNombreAsesoria("Asesoria para eliminar");
        //dto.setDescripcionAsesoria("Descripcion Asesoria para eliminar");
        //dto.setIdAsesoria(3);
        //dao.update(dto);
        //dao.create(dto);
        //dto = dao.read(dto);
        //dao.delete(dto);
        //System.out.println(dao.read(dto));
        System.out.println(dao.readAll());
    }
}

