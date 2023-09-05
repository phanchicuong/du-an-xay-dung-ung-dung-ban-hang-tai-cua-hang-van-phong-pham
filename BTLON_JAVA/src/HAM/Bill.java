/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HAM;

/**
 *
 * @author Admin
 */
public class Bill {

    public Bill() {
    }

    public Bill(String Ten, int Soluong, float Gia) {
        this.Ten = Ten;
        this.Soluong = Soluong;
        this.Gia = Gia;
    }

    public String getTen() {
        return Ten;
    }

    public int getSoluong() {
        return Soluong;
    }

    public float getGia() {
        return Gia;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }
    public String Ten;
    public int Soluong;
    public float Gia;
}
