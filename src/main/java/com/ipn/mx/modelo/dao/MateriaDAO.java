/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Materia;
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
public class MateriaDAO {
    
    public void create(Materia dto) {
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

    public void update(Materia dto) {
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

    public void delete(Materia dto) {
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

    public Materia read(Materia dto) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try {
            tx.begin();
            dto = (s.get(dto.getClass(), dto.getIdMateria()));
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
            Query q = s.createQuery("from Materia c order by c.idMateria");
            lista = q.list();
            /*for(Materia c: (List<Materia>) q.list()){
                Materia dto = new Materia();
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
        MateriaDAO dao = new MateriaDAO();
        Materia dto = new Materia();
        //dto.setNombreMateria("Materia para eliminar");
        //dto.setDescripcionMateria("Descripcion Materia para eliminar");
        //dto.setIdMateria(3);
        //dao.update(dto);
        //dao.create(dto);
        //dto = dao.read(dto);
        //dao.delete(dto);
        //System.out.println(dao.read(dto));
        System.out.println(dao.readAll());
    }
}
