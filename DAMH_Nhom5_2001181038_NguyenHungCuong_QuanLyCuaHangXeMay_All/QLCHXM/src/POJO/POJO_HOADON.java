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
public class POJO_HOADON {
    String mahd, makh, manv, thanhtien;

    public POJO_HOADON() {
    }

    public POJO_HOADON(String mahd, String makh, String manv, String thanhtien) {
        this.mahd = mahd;
        this.makh = makh;
        this.manv = manv;
        this.thanhtien = thanhtien;
    }

    public String getMahd() {
        return mahd;
    }

    public String getMakh() {
        return makh;
    }

    public String getManv() {
        return manv;
    }

    public String getThanhtien() {
        return thanhtien;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public void setThanhtien(String thanhtien) {
        this.thanhtien = thanhtien;
    }
    
}
