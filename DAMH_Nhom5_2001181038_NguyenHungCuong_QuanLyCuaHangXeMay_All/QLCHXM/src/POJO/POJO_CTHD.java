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
public class POJO_CTHD {
    String MaHD, MaHG, Gia;

    public POJO_CTHD() {
    }

    public POJO_CTHD(String MaHD, String MaHG, String Gia) {
        this.MaHD = MaHD;
        this.MaHG = MaHG;
        this.Gia = Gia;
    }

    public String getMaHD() {
        return MaHD;
    }

    public String getMaHG() {
        return MaHG;
    }

    public String getGia() {
        return Gia;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public void setMaHG(String MaHG) {
        this.MaHG = MaHG;
    }

    public void setGia(String Gia) {
        this.Gia = Gia;
    }
    
}
