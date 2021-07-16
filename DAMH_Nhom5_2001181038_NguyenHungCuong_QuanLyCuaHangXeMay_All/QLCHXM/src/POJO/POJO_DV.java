/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author ngock
 */
public class POJO_DV {
    String madv,tendv,gia;

    public POJO_DV(String madv, String tendv, String gia) {
        this.madv = madv;
        this.tendv = tendv;
        this.gia = gia;
    }

    public POJO_DV() {
    }

    public String getMadv() {
        return madv;
    }

    public String getTendv() {
        return tendv;
    }

    public String getGia() {
        return gia;
    }


    public void setMadv(String madv) {
        this.madv = madv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
    
}
