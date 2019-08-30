/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bind;

import dao.EmployDao;
import face.Employ;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Temployee;
import model.Tkantor;
import model.Tkota;
import model.Tprov;

/**
 *
 * @author GauztA
 */
@ManagedBean
@ViewScoped
public class TemployBean {

    private List<Temployee> templist;
    private Temployee temp;
    private List<SelectItem> listprov;
    private List<SelectItem> listkantor;
    private List<SelectItem> listkota;

    public TemployBean() {
        this.temp = new Temployee();
    }

    public Temployee getTemp() {
        return temp;
    }

    public void setTemp(Temployee temp) {
        this.temp = temp;
    }

    public void setTemplist(List<Temployee> templist) {
        this.templist = templist;
    }

    public List<Temployee> getTemplist() {
     if (templist ==null){
       try{
           EmployDao edo = new Employ();
           templist = edo.lisem();
       }catch(Exception e){
           System.out.println("List tidak ditemukan :"+e);
       }
        
    }
        return templist;
    }

    public List<SelectItem> getListprov() {
        this.listprov = new ArrayList<SelectItem>();
        EmployDao edo = new Employ();
        List<Tprov> lip = edo.liprov();
        this.listprov.clear();
        for (Tprov prov : lip) {
            SelectItem pitem = new SelectItem(prov.getIdprovinsi(), prov.getNamaprovinsi());
            this.listprov.add(pitem);
        }
        return listprov;
    }

    public List<SelectItem> getListkantor() {
        this.listkantor = new ArrayList<SelectItem>();
        EmployDao edo = new Employ();
        List<Tkantor> tk = edo.liskan(this.temp);
        this.listkantor.clear();
        for (Tkantor tkan : tk) {
            SelectItem katem = new SelectItem(tkan.getIdkantor(), tkan.getNamakantor());
            this.listkantor.add(katem);
        }
        return listkantor;
    }

    public List<SelectItem> getListkota() {
        this.listkota = new ArrayList<SelectItem>();
        EmployDao edo = new Employ();
        List<Tkota> tko = edo.liskot(this.temp);
        this.listkota.clear();
        for (Tkota kot : tko) {
            SelectItem kotem = new SelectItem(kot.getIdkota(), kot.getNamakota());
            this.listkota.add(kotem);
        }
        return listkota;
    }
    
    public void prosesdataTemployee(ActionEvent event){
        temp = new Temployee();
    }

    public void savedata() {
        EmployDao edo = new Employ();
        edo.tambahdata(temp);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "data tersimpan"));
    }

    public void editdata() {
        EmployDao edo = new Employ();
        edo.ngeditdata(temp);
        temp = new Temployee();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "data sudah di edit"));
    }
    
    public void apusdata() {
        EmployDao edo = new Employ();
        edo.ngapusdata(temp);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "data sudah ilang"));
        }
    
    public void gakjadiinput() {
        this.temp = new Temployee();
    }
}
