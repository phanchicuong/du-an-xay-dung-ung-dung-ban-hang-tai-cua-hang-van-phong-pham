/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HAM;

import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class SanPhan {

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public void setDongia(float Dongia) {
        this.Dongia = Dongia;
    }

    public String getMaSP() {
        return MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public int getSoluong() {
        return Soluong;
    }

    public float getDongia() {
        return Dongia;
    }

    public SanPhan(String MaSP, String TenSP, int Soluong, float Dongia) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.Soluong = Soluong;
        this.Dongia = Dongia;
    }

    public SanPhan() {
    }
    private String MaSP,TenSP;
    private int Soluong;
    private float Dongia;

    
    
}
