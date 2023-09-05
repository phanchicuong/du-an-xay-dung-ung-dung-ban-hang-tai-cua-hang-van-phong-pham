/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import HAM.SanPham;
import HAM.SanPhan;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class SanPhamDAO {
     public static ArrayList<SanPham>GetListSanPham(){
        ArrayList<SanPham>dsPBan =new ArrayList<SanPham>();
        try{
            String sql="Select *from SANPHAM";
            ConnectionDB pro  = new ConnectionDB();
            pro.getCn();
             ResultSet rs= pro.executeQuery(sql);
            while(rs.next()){
              SanPham sp =new SanPham();
              sp.setMaSP(rs.getInt("MaSanPham"));
              sp.setTenSP(rs.getString("TenSanPham"));
              sp.setLoaiSP(rs.getString("TenLoaiSanPham"));
              sp.setGiaNhap(rs.getFloat("GiaNhap"));
              sp.setGiaBan(rs.getFloat("GiaBan"));
              sp.setTrangThai(rs.getInt("TrangThai")); 
                dsPBan.add(sp);

            }
            pro.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return dsPBan;
    }
     
    public void AddSP(int MaSanPham ,String TenSanPham,String TenLoaiSanPham, float GiaNhap, float GiaBan, int TrangThai){
        String sql="Insert into SANPHAM VALUES ('"+MaSanPham+"','"+TenSanPham+"','"+TenLoaiSanPham+"','"+GiaNhap+"','"+GiaBan+"','"+TrangThai+"')";
        ConnectionDB pro  = new ConnectionDB();
          pro.getCn();
          int n= pro.executeUpdate(sql);
          pro.close();
    }
    
    
    public void UpdateSP(int MaSanPham ,String TenSanPham,String TenLoaiSanPham, float GiaNhap, float GiaBan, int TrangThai){
        String Sql = "update  SANPHAM set TenSanPham=('"+TenSanPham+"') ,TenLoaiSanPham=('"+TenLoaiSanPham+"'), GiaNhap=('"+GiaNhap+"'), GiaBan=('"+GiaBan+"'), TrangThai=('"+TrangThai+"') where MaSanPham =('"+MaSanPham+"')";
         ConnectionDB pro  = new ConnectionDB();
       pro.getCn();
       int n= pro.executeUpdate(Sql);
       pro.close();
    }
    
    
    public void DeleteSP(int MaSanPham){
         String sql="delete from SANPHAM where MaSanPham='"+MaSanPham+"'";
          ConnectionDB pro  = new ConnectionDB();
        pro.getCn();
        int n= pro.executeUpdate(sql);
        pro.close();
     }
    
    public static void FindSP(int MaSanPham){
         String Name="";
            try{
                String sql="select * from SANPHAM where MaSanPham = '"+MaSanPham+"' ";
                  ConnectionDB pro  = new ConnectionDB();
                pro.getCn();
                ResultSet rs = pro.executeQuery(sql);
                while(rs.next()){
                     Name=rs.getString(1);
            }
             pro.close();
            }catch(Exception ex){
                System.out.print("Lỗi không thấy dữ liệu");
            }
        
     

        }
    
    public static ArrayList<SanPham>GetListSanPham1(String t){
        
        ArrayList<SanPham>dsPBan =new ArrayList<SanPham>();
        try{
            String sql="Select *from SANPHAM where MaSanPham = '"+t+"'";
            ConnectionDB pro  = new ConnectionDB();
            pro.getCn();
             ResultSet rs= pro.executeQuery(sql);
            while(rs.next()){
              SanPham sp =new SanPham();
              sp.setMaSP(rs.getInt("MaSanPham"));
              sp.setTenSP(rs.getString("TenSanPham"));
              sp.setLoaiSP(rs.getString("TenLoaiSanPham"));
              sp.setGiaNhap(rs.getFloat("GiaNhap"));
              sp.setGiaBan(rs.getFloat("GiaBan"));
              sp.setTrangThai(rs.getInt("TrangThai"));
                
                dsPBan.add(sp);

            }
            pro.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return dsPBan;
    }
    
     
}
