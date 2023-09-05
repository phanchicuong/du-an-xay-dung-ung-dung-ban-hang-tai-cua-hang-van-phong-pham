/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import HAM.HOADON;
import HAM.SanPham;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDonDAO {

    public static ArrayList<HOADON> GetListHOADON() {
        ArrayList<HOADON> dsPBan = new ArrayList<HOADON>();
        try {
            String sql = "Select *from CHITIETDATHANG";
            ConnectionDB pro = new ConnectionDB();
            pro.getCn();
            ResultSet rs = pro.executeQuery(sql);
            while (rs.next()) {
                HOADON sp = new HOADON();
                sp.setMaHD(rs.getInt("MADON"));
                sp.setTenKH(rs.getString("TENKH"));
                sp.setTenSP(rs.getString("TenSanPham"));
                sp.setGiaBan(rs.getFloat("GiaBan"));
                sp.setSolg(rs.getInt("SOLUONG"));
                sp.setNgayban(rs.getDate("NGAYBAN"));
                sp.setNgaygiao(rs.getDate("NGAYGIAO"));
                
                dsPBan.add(sp);

            }
            pro.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dsPBan;
    }

//    int MADON , String TENKH, String TenSanPham, float GiaBan, int SOLUONG, Date NGAYBAN, Date NGAYGIAO
    public void AddHD(int ma, String Name, String NgayBan) {

        String sql = "set dateformat dmy insert into CHITIETDATHANG values ('1234',N'HUỲNH GIA THUẬN',N'Giấy Thi','10',4,'" + NgayBan + "','" + NgayBan + "')";
        ConnectionDB pro = new ConnectionDB();
        pro.getCn();
        int n = pro.executeUpdate(sql);
        pro.close();
    }

    public void DeleteHD(int MAHD){
         String sql="delete from CHITIETDATHANG where MADON='"+MAHD+"'";
          ConnectionDB pro  = new ConnectionDB();
        pro.getCn();
        int n= pro.executeUpdate(sql);
        pro.close();
     }
    
    
    
    public void AddHD2(int ma, String tenkh, String tensp, float giaban, int soluong, String ngayban, String ngaygiao) {

        String sql = "set dateformat dmy INSERT INTO CHITIETDATHANG VALUES(" + ma + ",N'" + tenkh + "',N'" + tensp + "',"+giaban+","+soluong+",'"+ngayban+"','"+ngaygiao+"')";
        ConnectionDB pro = new ConnectionDB();
        pro.getCn();
        int n = pro.executeUpdate(sql);
        pro.close();
    }

    public static float getGIASP(String tensp) {
        float gia = 0;
        try {
            String sql = "Select GiaBan from SANPHAM where TenSanPham = N'"+tensp+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while (rs.next()) {
                gia = rs.getFloat(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return gia;
    }
    
    public static ArrayList<String> getDSTENSP() {
        ArrayList<String> ds = new ArrayList<String>();
        try {
            String sql = "select TenSanPham from SANPHAM";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                ds.add(rs.getString(1).trim());
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return ds;
    }
    
    
    
    
}
