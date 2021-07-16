/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.Date;

/**
 *
 * @author ngock
 */
public class POJO_KhuyenMai {
    String makm, mahg, gia, ngay, thang, nam;

    public POJO_KhuyenMai() {
    }

    public POJO_KhuyenMai(String makm, String mahg, String gia, String ngay, String thang, String nam) {
        this.makm = makm;
        this.mahg = mahg;
        this.gia = gia;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    public String getMakm() {
        return makm;
    }

    public String getMahg() {
        return mahg;
    }

    public String getGia() {
        return gia;
    }

    public String getNgay() {
        return ngay;
    }

    public String getThang() {
        return thang;
    }

    public String getNam() {
        return nam;
    }

    public void setMakm(String makm) {
        this.makm = makm;
    }

    public void setMahg(String mahg) {
        this.mahg = mahg;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }
    
}
