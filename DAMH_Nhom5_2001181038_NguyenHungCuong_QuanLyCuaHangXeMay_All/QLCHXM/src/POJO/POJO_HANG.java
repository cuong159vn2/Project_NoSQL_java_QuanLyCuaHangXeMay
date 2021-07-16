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
public class POJO_HANG {
    String mahg,tenhg,gia,mota,hinh,soluong;
    public POJO_HANG(String mahg, String tenhg, String gia, String mota, String hinh, String soluong) {
        this.mahg = mahg;
        this.tenhg = tenhg;
        this.gia = gia;
        this.mota = mota;
        this.hinh = hinh;
        this.soluong = soluong;
    }
    public String getHinh() {
        return hinh;
    }
    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
    public String getSoluong() {
        return soluong;
    }
    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
    public POJO_HANG() {
    }

    public String getMahg() {
        return mahg;
    }

    public String getTenhg() {
        return tenhg;
    }

    public String getGia() {
        return gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMahg(String mahg) {
        this.mahg = mahg;
    }

    public void setTenhg(String tenhg) {
        this.tenhg = tenhg;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    
}
