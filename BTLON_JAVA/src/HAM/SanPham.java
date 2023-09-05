/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HAM;

/**
 *
 * @author Admin
 */
public class SanPham {

    public SanPham() {
    }

    public SanPham(int MaSP, int TrangThai, String TenSP, String LoaiSP, float GiaNhap, float GiaBan) {
        this.MaSP = MaSP;
        this.TrangThai = TrangThai;
        this.TenSP = TenSP;
        this.LoaiSP = LoaiSP;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
    }

    public int getMaSP() {
        return MaSP;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public String getTenSP() {
        return TenSP;
    }

    public String getLoaiSP() {
        return LoaiSP;
    }

    public float getGiaNhap() {
        return GiaNhap;
    }

    public float getGiaBan() {
        return GiaBan;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setLoaiSP(String LoaiSP) {
        this.LoaiSP = LoaiSP;
    }

    public void setGiaNhap(float GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public void setGiaBan(float GiaBan) {
        this.GiaBan = GiaBan;
    }

    
    
    private int MaSP, TrangThai;
    private String TenSP, LoaiSP;
    private float GiaNhap, GiaBan;
}
