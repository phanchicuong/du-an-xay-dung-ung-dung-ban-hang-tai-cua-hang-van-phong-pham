/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HAM;

/**
 *
 * @author Admin
 */
public class KhachHang {

    public KhachHang() {
    }

    public KhachHang(String MAKH, String TENKH, String DIACHI, String SODT) {
        this.MAKH = MAKH;
        this.TENKH = TENKH;
        this.DIACHI = DIACHI;
        this.SODT = SODT;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public void setSODT(String SODT) {
        this.SODT = SODT;
    }

    public String getMAKH() {
        return MAKH;
    }

    public String getTENKH() {
        return TENKH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public String getSODT() {
        return SODT;
    }
    String MAKH, TENKH, DIACHI, SODT;
}
