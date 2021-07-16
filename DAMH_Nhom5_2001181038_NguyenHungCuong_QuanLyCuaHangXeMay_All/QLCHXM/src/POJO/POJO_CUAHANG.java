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
public class POJO_CUAHANG {
    String Mach,Tench,DiaChi;

    public POJO_CUAHANG(String Mach, String Tench, String DiaChi) {
        this.Mach = Mach;
        this.Tench = Tench;
        this.DiaChi = DiaChi;
    }

    public POJO_CUAHANG() {
    }

    public String getMach() {
        return Mach;
    }

    public String getTench() {
        return Tench;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setMach(String Mach) {
        this.Mach = Mach;
    }

    public void setTench(String Tench) {
        this.Tench = Tench;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }
}
