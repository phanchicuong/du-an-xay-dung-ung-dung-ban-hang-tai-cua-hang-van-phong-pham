/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HAM;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class HOADON {

    public float getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(float GiaBan) {
        this.GiaBan = GiaBan;
    }

    public HOADON() {
    }

    public HOADON(int MaHD, int MaKH, int MaSP, int Solg, float GiaBan, String TenKH, String TenSP, Date ngayban, Date ngaygiao) {
        this.MaHD = MaHD;
        this.MaKH = MaKH;
        this.MaSP = MaSP;
        this.Solg = Solg;
        this.GiaBan = GiaBan;
        this.TenKH = TenKH;
        this.TenSP = TenSP;
        this.ngayban = ngayban;
        this.ngaygiao = ngaygiao;
    }

    

    public int getMaHD() {
        return MaHD;
    }

    public int getMaKH() {
        return MaKH;
    }

    public int getMaSP() {
        return MaSP;
    }

    public int getSolg() {
        return Solg;
    }

    public String getTenKH() {
        return TenKH;
    }

    public String getTenSP() {
        return TenSP;
    }

    public Date getNgayban() {
        return ngayban;
    }

    public Date getNgaygiao() {
        return ngaygiao;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public void setSolg(int Solg) {
        this.Solg = Solg;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setNgayban(Date ngayban) {
        this.ngayban = ngayban;
    }

    public void setNgaygiao(Date ngaygiao) {
        this.ngaygiao = ngaygiao;
    }
    private int MaHD, MaKH, MaSP, Solg;
    private float GiaBan;
    private String TenKH, TenSP;
    private Date  ngayban, ngaygiao;
    
    
    
    public double TTIEN(){
        return GiaBan * Solg;
    }
    
}
