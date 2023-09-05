/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import HAM.KhachHang;
import HAM.SanPhan;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhachHangDAO {
     public static ArrayList<KhachHang>GetListKhachHang(){
        ArrayList<KhachHang>dsPBan1 =new ArrayList<KhachHang>();
        try{
            String sql="Select *from KHACHHANG";
            ConnectionDB pro  = new ConnectionDB();
            pro.getCn();
             ResultSet rs= pro.executeQuery(sql);
            while(rs.next()){
              KhachHang sp =new KhachHang();
              sp.setMAKH(rs.getString("MAKH"));
              sp.setTENKH(rs.getString("TENKH"));
              sp.setDIACHI(rs.getString("DIACHI"));
              sp.setSODT(rs.getString("SODT"));
                
                dsPBan1.add(sp);

            }
            pro.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return dsPBan1;
     }
     
     
     public void AddKH(int MAKH ,String TENKH,String DIACHI, String SODT){
        String sql="INSERT INTO KHACHHANG values('"+MAKH+"',N'"+TENKH+"',N'"+DIACHI+"','"+SODT+"')";
        ConnectionDB pro  = new ConnectionDB();
          pro.getCn();
          int n= pro.executeUpdate(sql);
          pro.close();
    }
     
     
     public void DeleteKH(int MAKH){
         String sql="delete from KHACHHANG where MAKH='"+MAKH+"'";
          ConnectionDB pro  = new ConnectionDB();
        pro.getCn();
        int n= pro.executeUpdate(sql);
        pro.close();
     }
     
  
     
     public void UpdateKH(int MAKH ,String TENKH,String DIACHI, String SODT){
        String Sql = "update  KHACHHANG set TENKH =(N'"+TENKH+"'),DIACHI=(N'"+DIACHI+"') ,SODT=('"+SODT+"')where MAKH  =('"+MAKH+"')";
         ConnectionDB pro  = new ConnectionDB();
       pro.getCn();
       int n= pro.executeUpdate(Sql);
       pro.close();
    }
     
}
