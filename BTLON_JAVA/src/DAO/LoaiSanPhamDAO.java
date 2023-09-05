/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import HAM.LoaiSanPham;
import HAM.SanPham;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LoaiSanPhamDAO {
    public static ArrayList<LoaiSanPham>GetListLoaiSanPham(){
        ArrayList<LoaiSanPham>dsPBan =new ArrayList<LoaiSanPham>();
        try{
            String sql="Select *from LOAISANPHAM";
            ConnectionDB pro  = new ConnectionDB();
            pro.getCn();
             ResultSet rs= pro.executeQuery(sql);
            while(rs.next()){
              LoaiSanPham sp =new LoaiSanPham();
              sp.setMaLoai(rs.getInt("MaLoaiSanPham"));
              sp.setTenLoai(rs.getString("TenLoaiSanPham"));
            
                dsPBan.add(sp);

            }
            pro.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return dsPBan;
    }
}
