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
public class POJO_KHACHHANG {
    String makh,tenkh,diachi,sdt;

    public POJO_KHACHHANG() {
    }

    public POJO_KHACHHANG(String makh, String tenkh, String diachi, String sdt) {
        this.makh = makh;
        this.tenkh = tenkh;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public String getMakh() {
        return makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
}
