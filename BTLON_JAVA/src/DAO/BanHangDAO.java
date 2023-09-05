/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import HAM.HOADON;
import HAM.KhachHang;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BanHangDAO {
    public static ArrayList<HOADON>GetListDATHANG(){
        ArrayList<HOADON>dsPBan1 =new ArrayList<HOADON>();
        try{
            String sql="Select *from DATHANG";
            ConnectionDB pro  = new ConnectionDB();
            pro.getCn();
             ResultSet rs= pro.executeQuery(sql);
            while(rs.next()){
              HOADON sp =new HOADON();
               sp.setMaHD(rs.getInt("MADON"));
                sp.setTenKH(rs.getString("TENKH"));
                sp.setTenSP(rs.getString("TenSanPham"));
                sp.setGiaBan(rs.getFloat("GiaBan"));
                sp.setSolg(rs.getInt("SOLUONG"));
                sp.setNgayban(rs.getDate("NGAYBAN"));
                sp.setNgaygiao(rs.getDate("NGAYGIAO"));
                
                dsPBan1.add(sp);

            }
            pro.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return dsPBan1;
     }
}
