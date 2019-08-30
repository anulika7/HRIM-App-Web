/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Temployee;
import model.Tkantor;
import model.Tkota;
import model.Tprov;

/**
 *
 * @author GauztA
 */
public interface EmployDao {
    public List<Temployee> lisem();
    public void tambahdata(Temployee temployee);
    public void ngeditdata(Temployee temployee);
    public void ngapusdata(Temployee temployee); 
    
    //methode list dinamis
    public List<Tprov> liprov();
    public List<Tkantor> liskan(Temployee employee);
    public List<Tkota> liskot(Temployee employee);
}
