/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package face;

import dao.EmployDao;
import java.util.List;
import model.Temployee;
import model.Tkantor;
import model.Tkota;
import model.Tprov;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author GauztA
 */
public class Employ implements EmployDao
{

    @Override
    public List<Temployee> lisem() {
        List<Temployee> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Temployee as e inner join fetch e.tprov left join fetch e.tkantor left join fetch e.tkota";
         try {
         lista = session.createQuery(hql).list();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return lista;
    }

    @Override
    public void tambahdata(Temployee temployee) {
        Session session=null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(temployee);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public List<Tprov> liprov() {
      List<Tprov> prolista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Tprov";
         try {
         prolista = session.createQuery(hql).list();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return prolista;
    }

    @Override
    public List<Tkantor> liskan(Temployee employee) {
       List<Tkantor> kanlist = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Tkantor WHERE idprov='"+employee.getTprov().getIdprovinsi()+"'";
         try {
         kanlist = session.createQuery(hql).list();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return kanlist;
    }

    @Override
    public List<Tkota> liskot(Temployee employee) {
      List<Tkota> listkot = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Tkota WHERE idkantor='"+employee.getTkantor().getIdkantor()+"'";
         try {
         listkot = session.createQuery(hql).list();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return listkot;}

    @Override
    public void ngeditdata(Temployee temployee) {
        Session session=null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(temployee);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void ngapusdata(Temployee temployee) {
      Session session=null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(temployee);
            session.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }
    
}
