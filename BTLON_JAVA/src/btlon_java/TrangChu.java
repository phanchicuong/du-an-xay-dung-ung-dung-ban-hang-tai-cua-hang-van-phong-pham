/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package btlon_java;

import DAO.BanHangDAO;
import DAO.ConnectionDB;
import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DAO.LoaiSanPhamDAO;
import HAM.SanPhan;
import DAO.SanPhamDAO;

import HAM.BanHang;
import HAM.HOADON;
import HAM.KhachHang;
import HAM.LoaiSanPham;
import HAM.MyCombobox;
import HAM.SanPham;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Admin
 */
public class TrangChu extends javax.swing.JFrame {

    ArrayList<SanPhan> dsSV = new ArrayList<SanPhan>();
    int index = 0;

    /**
     * Creates new form TrangChu
     */
    public TrangChu() {
        initComponents();
//        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("C:\\JAVA\\BTLON-JAVA\\BTLON_JAVA\\src\\hinh\\logonen.jpg"));
//        this.setIconImage(logo.getImage());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        txtngaygiao.setDateFormatString(dateFormat.toPattern());
        txtngayban.setDateFormatString(dateFormat.toPattern());
        
        Date now = new Date();
        txtngayban.setDate(now);

        
        TableSP();
        TableKH();
        TableLoai();
        LoaispCbx();
        KhachHangCbx();
        loadCboTenSP();
        TableSPBH();
        TableHD();
        btntendn.setText(login.TenDangNhap);
    }

// SAN PHAM NHA QUAN
    public void TableSP() {
        ArrayList<SanPham> dsSinhVien1 = SanPhamDAO.GetListSanPham();
        DefaultTableModel dtmSinhVien = (DefaultTableModel) tbsanpham.getModel();
        dtmSinhVien.setRowCount(0);
        for (SanPham sv1 : dsSinhVien1) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(sv1.getMaSP());
            vec.add(sv1.getTenSP());
            vec.add(sv1.getLoaiSP());
            vec.add(sv1.getGiaNhap());
            vec.add(sv1.getGiaBan());
            vec.add(sv1.getTrangThai());
            dtmSinhVien.addRow(vec);
        }
        tbsanpham.setModel(dtmSinhVien);
    }

    public void AddSP() {
        try {
            SanPhamDAO sv = new SanPhamDAO();

            sv.AddSP(Integer.parseInt(txtmasp.getText().trim()), txttensp.getText().trim(), cbbLoaiSp.getSelectedItem().toString(), Integer.parseInt(txtgianhap.getText().trim()), Integer.parseInt(txtgiaban.getText().trim()), Integer.parseInt(txttrangthai.getText().trim()));
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
            TableSP();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "lỗi");
        }
    }

    public void UpdateSP() {

        try {
            SanPhamDAO sv = new SanPhamDAO();

            sv.UpdateSP(Integer.parseInt(txtmasp.getText().trim()), txttensp.getText().trim(), cbbLoaiSp.getSelectedItem().toString(), Integer.parseInt(txtgianhap.getText().trim()), Integer.parseInt(txtgiaban.getText().trim()), Integer.parseInt(txttrangthai.getText().trim()));
            JOptionPane.showMessageDialog(this, "Cập Nhật Thành Công");
            TableSP();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Không Tìm Thấy");
        }

    }

    public void DeleteSP() {
        try {
            SanPhamDAO kh = new SanPhamDAO();
            kh.DeleteSP(Integer.parseInt(txtmasp.getText()));
            JOptionPane.showMessageDialog(this, "Xoá Thành Công");
            TableSP();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "lỗi");

        }
    }

    public void FindSP() {
        try {
            SanPhamDAO sv = new SanPhamDAO();

            sv.FindSP(Integer.parseInt(txtmasp.getText().trim()));
            JOptionPane.showMessageDialog(this, "Tìm kiếm Thành Công");
            TableSP2(txtmasp.getText().trim());
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Không Tìm Thấy");
        }

    }

    public void TableSP2(String Ma) {

        ArrayList<SanPham> dsSinhVien1 = SanPhamDAO.GetListSanPham1(Ma);
        DefaultTableModel dtmSinhVien = (DefaultTableModel) tbsanpham.getModel();
        dtmSinhVien.setRowCount(0);
        for (SanPham sv1 : dsSinhVien1) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(sv1.getMaSP());
            vec.add(sv1.getTenSP());
            vec.add(sv1.getLoaiSP());
            vec.add(sv1.getGiaNhap());
            vec.add(sv1.getGiaBan());
            vec.add(sv1.getTrangThai());
            dtmSinhVien.addRow(vec);
        }
        tbsanpham.setModel(dtmSinhVien);
    }

    // SAN PHAM NHA QUAN   
// KHACH HANG NE QUAN
    public void TableKH() {
        ArrayList<KhachHang> dsKhachHang = KhachHangDAO.GetListKhachHang();
        DefaultTableModel dtmkhachhang = (DefaultTableModel) tbkhachhang.getModel();
        dtmkhachhang.setRowCount(0);
        for (KhachHang kh : dsKhachHang) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(kh.getMAKH());
            vec.add(kh.getTENKH());
            vec.add(kh.getDIACHI());
            vec.add(kh.getSODT());
            dtmkhachhang.addRow(vec);
        }
        tbkhachhang.setModel(dtmkhachhang);
    }

    public void AddKH() {
        try {
            KhachHangDAO sv = new KhachHangDAO();

            sv.AddKH(Integer.parseInt(txtmakh.getText().trim()), txttenkh.getText().trim(),
                    txtdiachikh.getText().trim(),
                    txtsodtkh.getText().trim());
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
            TableKH();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "lỗi");
        }
    }

    public void DeleteKH() {
        try {
            KhachHangDAO kh = new KhachHangDAO();
            kh.DeleteKH(Integer.parseInt(txtmakh.getText()));
            JOptionPane.showMessageDialog(this, "Xoá Thành Công");
            TableKH();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "lỗi");

        }
    }

    public void UpdateKH() {

        try {
            KhachHangDAO sv = new KhachHangDAO();

            sv.UpdateKH(Integer.parseInt(txtmakh.getText().trim()), txttenkh.getText().trim(),
                    txtdiachikh.getText().trim(),
                    txtsodtkh.getText().trim());
            JOptionPane.showMessageDialog(this, "Cập Nhật Thành Công");
            TableKH();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Không Tìm Thấy");
        }

    }

// KHACH HANG NE QUAN
// LOAI SAN PHAM NHA KU    
    public void TableLoai() {
        ArrayList<LoaiSanPham> dsLoaiSanPham = LoaiSanPhamDAO.GetListLoaiSanPham();
        DefaultTableModel dtmkhachhang = (DefaultTableModel) tbloaisp.getModel();
        dtmkhachhang.setRowCount(0);
        for (LoaiSanPham kh : dsLoaiSanPham) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(kh.getMaLoai());
            vec.add(kh.getTenLoai());

            dtmkhachhang.addRow(vec);
        }
        tbloaisp.setModel(dtmkhachhang);
    }

    public void LoaispCbx() {

        ArrayList<LoaiSanPham> dsPBan2 = new ArrayList<LoaiSanPham>();

        String sql = "Select *from LOAISANPHAM";
        ConnectionDB pro = new ConnectionDB();
        pro.getCn();
        ResultSet rs = pro.executeQuery(sql);

        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbLoaiSp.getModel();
        cbbModel.removeAllElements();

        try {
            while (rs.next()) {
                int MaLoaiSP = rs.getInt("MaLoaiSanPham");
                String TenLoaiSP = rs.getString("TenLoaiSanPham");

                MyCombobox mycbb = new MyCombobox(MaLoaiSP, TenLoaiSP);

                cbbModel.addElement(mycbb);
            }

        } catch (Exception e) {

        }

    }

// Ban hang
    public void TableSPBH() {
        ArrayList<HOADON> dsSinhVien1 = BanHangDAO.GetListDATHANG();
        DefaultTableModel dtmSinhVien = (DefaultTableModel) tbsanphambh.getModel();
        dtmSinhVien.setRowCount(0);
        for (HOADON sv1 : dsSinhVien1) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(sv1.getMaHD());
            vec.add(sv1.getTenKH());
            vec.add(sv1.getTenSP());
            vec.add(sv1.getGiaBan());
            vec.add(sv1.getSolg());
            vec.add(sv1.getNgayban());
            vec.add(sv1.getNgaygiao());
            vec.add(sv1.TTIEN());
            dtmSinhVien.addRow(vec);
        }
        tbsanphambh.setModel(dtmSinhVien);
    }

    public void TableBH(int id, String Name, int Qty, Double Price) {

        DefaultTableModel dt = (DefaultTableModel) tbbanhang.getModel();

        DecimalFormat df = new DecimalFormat("00.00");
        double totPrice = Price * Double.valueOf(Qty);

        String TotalPrice = df.format(totPrice);

        // product allready add chk
        for (int row = 0; row < tbbanhang.getRowCount(); row++) {

            if (Name == tbbanhang.getValueAt(row, 1)) {

                dt.removeRow(tbbanhang.convertRowIndexToModel(row));

            }
        }

        Vector v = new Vector();

        v.add(id);
        v.add(Name);
        v.add(Qty);
        v.add(TotalPrice); // total price

        dt.addRow(v);

    }

    public void KhachHangCbx() {

        ArrayList<KhachHang> dsPBan2 = new ArrayList<KhachHang>();

        String sql = "Select *from KHACHHANG";
        ConnectionDB pro = new ConnectionDB();
        pro.getCn();
        ResultSet rs = pro.executeQuery(sql);

        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbtenkh.getModel();
        cbbModel.removeAllElements();

        try {
            while (rs.next()) {
                int MaKhachHang = rs.getInt("MAKH");
                String TenKhachHang = rs.getString("TENKH");

                MyCombobox mycbb = new MyCombobox(MaKhachHang, TenKhachHang);

                cbbModel.addElement(mycbb);
            }

        } catch (Exception e) {

        }

    }

    public void SanPhamCbx() {

        ArrayList<SanPham> dsPBan2 = new ArrayList<SanPham>();

        String sql = "Select *from SANPHAM";
        ConnectionDB pro = new ConnectionDB();
        pro.getCn();
        ResultSet rs = pro.executeQuery(sql);

        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbsp.getModel();
        cbbModel.removeAllElements();

        try {
            while (rs.next()) {
                int MaSP = rs.getInt("MaSanPham");
                String TenSP = rs.getString("TenSanPham");

                MyCombobox mycbb = new MyCombobox(MaSP, TenSP);

                cbbModel.addElement(mycbb);
            }

        } catch (Exception e) {

        }

    }

    private void loadCboTenSP() {
        DefaultComboBoxModel cbomodel = new DefaultComboBoxModel();
        ArrayList<String> lst = HoaDonDAO.getDSTENSP();
        for (String str : lst) {
            cbomodel.addElement(str);
        }
        cbbsp.setModel(cbomodel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtmasp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txttensp = new javax.swing.JTextField();
        txtgianhap = new javax.swing.JTextField();
        txtgiaban = new javax.swing.JTextField();
        txttrangthai = new javax.swing.JTextField();
        btnaddsp = new javax.swing.JButton();
        btndeletesp = new javax.swing.JButton();
        btnrepairsp = new javax.swing.JButton();
        btnresetsp = new javax.swing.JButton();
        btnsearchsp = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btntendn = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        cbbLoaiSp = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbsanpham = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbloaisp = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbkhachhang = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtmakh = new javax.swing.JTextField();
        txttenkh = new javax.swing.JTextField();
        txtdiachikh = new javax.swing.JTextField();
        txtsodtkh = new javax.swing.JTextField();
        btnaddkh = new javax.swing.JButton();
        btndeletekh = new javax.swing.JButton();
        btnrepairkh = new javax.swing.JButton();
        btnresetkh = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        btnsearch1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbsanphambh = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        b = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbbanhang = new javax.swing.JTable();
        addtb1tb2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblMaHoaDon_HoaDon = new javax.swing.JLabel();
        txtmahd = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbbtenkh = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbbsp = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtgiabanhd = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtsolghd = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txttongtienhd = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtngayban = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        txtngaygiao = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane2.setBackground(new java.awt.Color(255, 153, 51));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(1000, 520));
        jTabbedPane2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jTabbedPane2ComponentResized(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 498));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/TC.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/hinh/LOGO1.png")), jPanel2); // NOI18N

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, -1, -1));
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 400, -1, -1));
        jPanel4.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setText("Mã sản phẩm:");

        jLabel3.setText("Tên sản phẩm:");

        jLabel5.setText("Loại sản phẩm:");

        jLabel8.setText("Giá nhập:");

        jLabel9.setText("Giá bán:");

        jLabel23.setText("Trạng thái:");

        btnaddsp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnaddsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/add.png"))); // NOI18N
        btnaddsp.setText("THÊM");
        btnaddsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddspActionPerformed(evt);
            }
        });

        btndeletesp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btndeletesp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/remove.png"))); // NOI18N
        btndeletesp.setText("XÓA");
        btndeletesp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletespActionPerformed(evt);
            }
        });

        btnrepairsp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnrepairsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/repair.png"))); // NOI18N
        btnrepairsp.setText("SỬA");
        btnrepairsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepairspActionPerformed(evt);
            }
        });

        btnresetsp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnresetsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/reset.png"))); // NOI18N
        btnresetsp.setText("RESET");
        btnresetsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetspActionPerformed(evt);
            }
        });

        btnsearchsp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsearchsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/search.png"))); // NOI18N
        btnsearchsp.setText("SEARCH");
        btnsearchsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchspActionPerformed(evt);
            }
        });

        jLabel4.setText("Nhập sản phẩm bạn muốm tìm kiếm:");

        btntendn.setText("Tên đăng nhập");

        jLabel22.setText(": Đang đăng nhập");

        cbbLoaiSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tbsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Giá nhập", "Giá bán", "Trạng thái"
            }
        ));
        tbsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsanphamMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbsanpham);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel23)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(cbbLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnaddsp)
                                    .addComponent(btnrepairsp, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(104, 104, 104)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnresetsp)
                                    .addComponent(btndeletesp, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField1))
                                .addGap(49, 49, 49)
                                .addComponent(btnsearchsp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(btntendn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22)))
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane6)
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txttensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbbLoaiSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtgianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(txttrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnrepairsp)
                                    .addComponent(btnresetsp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnsearchsp, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnaddsp)
                                    .addComponent(btndeletesp)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btntendn)
                            .addComponent(jLabel22))))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Sản Phẩm", jPanel8);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));

        tbloaisp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã loại sản phẩm", "Tên loại sản phẩm"
            }
        ));
        jScrollPane4.setViewportView(tbloaisp);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(356, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(293, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Loại sản phẩm", jPanel9);

        jPanel4.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 520));

        jTabbedPane2.addTab("Sản phẩm", new javax.swing.ImageIcon(getClass().getResource("/hinh/stock.png")), jPanel4); // NOI18N

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        tbkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã khách hàng ", "Tên khách hàng", "Địa chỉ", "Số điện thoại"
            }
        ));
        tbkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbkhachhangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbkhachhang);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Mã khách hàng:");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tên khách hàng:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Địa chỉ:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Số điện thoại:");

        btnaddkh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnaddkh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/add.png"))); // NOI18N
        btnaddkh.setText("THÊM");
        btnaddkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddkhActionPerformed(evt);
            }
        });

        btndeletekh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btndeletekh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/remove.png"))); // NOI18N
        btndeletekh.setText("XÓA");
        btndeletekh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletekhActionPerformed(evt);
            }
        });

        btnrepairkh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnrepairkh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/repair.png"))); // NOI18N
        btnrepairkh.setText("SỬA");
        btnrepairkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrepairkhActionPerformed(evt);
            }
        });

        btnresetkh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnresetkh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/reset.png"))); // NOI18N
        btnresetkh.setText("RESET");
        btnresetkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetkhActionPerformed(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("giới tính:");

        buttonGroup1.add(rdNam);
        rdNam.setSelected(true);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        btnsearch1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/search.png"))); // NOI18N
        btnsearch1.setText("SEARCH");
        btnsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearch1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtmakh, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel18))
                                    .addComponent(jLabel15))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rdNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdNu))
                            .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdiachikh, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsodtkh, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(53, 53, 53)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnaddkh)
                    .addComponent(btnrepairkh))
                .addGap(143, 143, 143)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnresetkh)
                    .addComponent(btndeletekh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                .addComponent(btnsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnaddkh)
                            .addComponent(btndeletekh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnrepairkh)
                                    .addComponent(btnresetkh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtmakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdNam)
                                    .addComponent(rdNu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtdiachikh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtsodtkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(btnsearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Khách hàng", new javax.swing.ImageIcon(getClass().getResource("/hinh/people.png")), jPanel5); // NOI18N

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));

        tbsanphambh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên khách hàng", "Tên sản phẩm", "Giá bán", "Số lượng", "Ngày bán", "Ngày giao", "Tổng tiền"
            }
        ));
        tbsanphambh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsanphambhMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbsanphambh);

        b.setColumns(20);
        b.setRows(5);
        jScrollPane1.setViewportView(b);

        tbbanhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên khách hàng", "Tên sản phẩm", "Giá bán", "Số lượng", "Ngày bán", "Ngày giao"
            }
        ));
        jScrollPane7.setViewportView(tbbanhang);

        addtb1tb2.setText("Thêm");
        addtb1tb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtb1tb2ActionPerformed(evt);
            }
        });

        jButton3.setText("TẤT CÃ HÓA ĐƠN");

        jButton4.setText("HÓA ĐƠN CỦA BẠN");

        lblMaHoaDon_HoaDon.setText("Mã Hóa Đơn:");

        jLabel16.setText("Tên khách hàng:");

        cbbtenkh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setText("Tên sản phẩm:");

        cbbsp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbspMouseClicked(evt);
            }
        });
        cbbsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbspActionPerformed(evt);
            }
        });

        jLabel19.setText("Giá bán:");

        jLabel21.setText("Số lượng:");

        txtsolghd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsolghdFocusLost(evt);
            }
        });

        jLabel24.setText("Tổng tiền:");

        txttongtienhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttongtienhdActionPerformed(evt);
            }
        });

        jLabel25.setText("Ngày Bán:");

        txtngayban.setDateFormatString("yyyy-MM-dd");

        jLabel26.setText("Ngày giao:");

        txtngaygiao.setDateFormatString("yyyy-MM-dd");

        jButton1.setText("Thêm hóa đơn của bạn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("In Hóa Đơn");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("Xóa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jButton3))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                                    .addComponent(jScrollPane7)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(jButton5)
                                .addGap(79, 79, 79)
                                .addComponent(jButton4)
                                .addGap(46, 46, 46)
                                .addComponent(addtb1tb2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaHoaDon_HoaDon)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttongtienhd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtsolghd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtgiabanhd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbsp, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbtenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txtngaygiao, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txtngayban, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jButton3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(addtb1tb2)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaHoaDon_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(cbbtenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(cbbsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtgiabanhd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(txtsolghd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txttongtienhd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel26))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtngayban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(txtngaygiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 43, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Bán hàng", new javax.swing.ImageIcon(getClass().getResource("/hinh/shopping.png")), jPanel6); // NOI18N

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel7ComponentShown(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/hinhsp.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Đăng xuất ", new javax.swing.ImageIcon(getClass().getResource("/hinh/log-out.png")), jPanel7); // NOI18N

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel3ComponentShown(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinh/hinhsp.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thoát", new javax.swing.ImageIcon(getClass().getResource("/hinh/exit.png")), jPanel3); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1347, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane2ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane2ComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2ComponentResized


    private void jPanel7ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel7ComponentShown
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(
                jTabbedPane2,
                "Bạn có chắc muốn đăng xuất không không ?",
                "Thông báo",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            login frmdn = new login();
            frmdn.show();
            this.dispose();
        }
    }//GEN-LAST:event_jPanel7ComponentShown

    private void jPanel3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentShown
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(
                jTabbedPane2,
                "Bạn có chắc muốn thoát chương trình không ?",
                "Thông báo",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jPanel3ComponentShown

    private void btnaddkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddkhActionPerformed
        // TODO add your handling code here:
        AddKH();
    }//GEN-LAST:event_btnaddkhActionPerformed

    private void btndeletekhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletekhActionPerformed
        // TODO add your handling code here:
        DeleteKH();
    }//GEN-LAST:event_btndeletekhActionPerformed

    private void btnrepairkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepairkhActionPerformed
        // TODO add your handling code here:
        UpdateKH();
    }//GEN-LAST:event_btnrepairkhActionPerformed

    private void btnresetkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetkhActionPerformed
        // TODO add your handling code here:
        txtmakh.setText("");
        txttenkh.setText("");
        txtdiachikh.setText("");
        txtsodtkh.setText("");
    }//GEN-LAST:event_btnresetkhActionPerformed

    private void tbkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbkhachhangMouseClicked
        // TODO add your handling code here:
        int i = tbkhachhang.getSelectedRow();
        txtmakh.setText(tbkhachhang.getValueAt(i, 0).toString().trim());
        txttenkh.setText(tbkhachhang.getValueAt(i, 1).toString().trim());
        txtdiachikh.setText(tbkhachhang.getValueAt(i, 2).toString().trim());
        txtsodtkh.setText(tbkhachhang.getValueAt(i, 3).toString().trim());
    }//GEN-LAST:event_tbkhachhangMouseClicked

    private void tbsanphambhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsanphambhMouseClicked
        // TODO add your handling code here:
//         TableBH(1, "ZRW Combo  ", 1, 2.99);

    }//GEN-LAST:event_tbsanphambhMouseClicked

    private void btnsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsearch1ActionPerformed

    private void btnaddspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddspActionPerformed
        // TODO add your handling code here:
        AddSP();
    }//GEN-LAST:event_btnaddspActionPerformed

    private void btndeletespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletespActionPerformed
        // TODO add your handling code here:
        DeleteSP();
    }//GEN-LAST:event_btndeletespActionPerformed

    private void btnrepairspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrepairspActionPerformed
        // TODO add your handling code here:
        UpdateSP();
    }//GEN-LAST:event_btnrepairspActionPerformed

    private void btnresetspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetspActionPerformed
        // TODO add your handling code here:
        txtmasp.setText("");
        txttensp.setText("");
        txtgianhap.setText("");
        txtgiaban.setText("");
        txttrangthai.setText("");
    }//GEN-LAST:event_btnresetspActionPerformed

    private void btnsearchspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchspActionPerformed
        // TODO add your handling code here:

        if (txtmasp.getText().isEmpty()) {
            TableSP();
        } else {
            FindSP();
        }
    }//GEN-LAST:event_btnsearchspActionPerformed

    private void tbsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsanphamMouseClicked
        // TODO add your handling code here:
        int i = tbsanpham.getSelectedRow();
        txtmasp.setText(tbsanpham.getValueAt(i, 0).toString().trim());
        txttensp.setText(tbsanpham.getValueAt(i, 1).toString().trim());

        txtgianhap.setText(tbsanpham.getValueAt(i, 3).toString().trim());
        txtgiaban.setText(tbsanpham.getValueAt(i, 4).toString().trim());
        txttrangthai.setText(tbsanpham.getValueAt(i, 5).toString().trim());
    }//GEN-LAST:event_tbsanphamMouseClicked

    private void addtb1tb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtb1tb2ActionPerformed
        // TODO add your handling code here:
        TableModel model1 = tbsanphambh.getModel();

        int[] indexs = tbsanphambh.getSelectedRows();

        Object[] row = new Object[4];

        DefaultTableModel model2 = (DefaultTableModel) tbbanhang.getModel();

        for (int i = 0; i < indexs.length; i++) {
            row[0] = model1.getValueAt(indexs[i], 0);

            row[1] = model1.getValueAt(indexs[i], 1);

            model2.addRow(row);
        }
    }//GEN-LAST:event_addtb1tb2ActionPerformed

    private void txttongtienhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttongtienhdActionPerformed
        // TODO add your handling code here:
        ArrayList<HOADON> dsSinhVien1 = HoaDonDAO.GetListHOADON();

        for (HOADON sv1 : dsSinhVien1) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(sv1.TTIEN());

        }
    }//GEN-LAST:event_txttongtienhdActionPerformed

    private void cbbspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbspMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cbbspMouseClicked

    public void AddHD() {
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String day = formatter2.format(txtngayban.getDate());
        String day1 = formatter2.format(txtngaygiao.getDate());
        try {

            HoaDonDAO hd = new HoaDonDAO();
            hd.AddHD2(Integer.parseInt(txtmahd.getText().trim()),
                    cbbtenkh.getSelectedItem().toString(),
                    cbbsp.getSelectedItem().toString(),
                    Integer.parseInt(txtgiabanhd.getText().trim()),
                    Integer.parseInt(txtsolghd.getText().trim()),
                    day,
                    day1
            );
            JOptionPane.showMessageDialog(this, "ok");
        } catch (Exception ex) {

            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "lỗi");
        }
    }

    public void TableHD() {
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        
        
        ArrayList<HOADON> dsSinhVien1 = HoaDonDAO.GetListHOADON();
        DefaultTableModel dtmSinhVien = (DefaultTableModel) tbbanhang.getModel();
        dtmSinhVien.setRowCount(0);
        for (HOADON sv1 : dsSinhVien1) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(sv1.getMaHD());
            vec.add(sv1.getTenKH());
            vec.add(sv1.getTenSP());
            vec.add(sv1.getGiaBan());
            vec.add(sv1.getSolg());
            vec.add(formatter2.format(sv1.getNgayban()));
            vec.add(formatter2.format(sv1.getNgaygiao()));

            dtmSinhVien.addRow(vec);
        }
        tbbanhang.setModel(dtmSinhVien);
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        AddHD();
        TableSPBH();
        TableHD();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbbspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbspActionPerformed
        // TODO add your handling code here:
        txtgiabanhd.setText(String.valueOf(HoaDonDAO.getGIASP((String) cbbsp.getSelectedItem())));

    }//GEN-LAST:event_cbbspActionPerformed

    private void txtsolghdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsolghdFocusLost
        // TODO add your handling code here:
        txttongtienhd.setText(String.valueOf(Double.parseDouble(txtgiabanhd.getText()) * Integer.parseInt(txtsolghd.getText())));
    }//GEN-LAST:event_txtsolghdFocusLost

    public void DeleteHD() {
        try {
            HoaDonDAO kh = new HoaDonDAO();
            kh.DeleteHD(Integer.parseInt(txtmahd.getText().trim()));
            JOptionPane.showMessageDialog(this, "Xoá Thành Công");
            TableHD();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "lỗi");

        }
    }


    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        DeleteHD();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            
           b.setText("                         Dapp Code FKD \n");
            b.setText(b.getText() + "                         589/ King Road, \n");
            b.setText(b.getText() + "                         Colombo, Sri lanka, \n");
            b.setText(b.getText() + "                         +9411 123456789, \n");
            b.setText(b.getText() + "---------------------------------------------------------------------\n");
            b.setText(b.getText() + "  Item \t\tQty \tPrice" +"\n");
            b.setText(b.getText() + "---------------------------------------------------------------------\n");
            
            DefaultTableModel df = (DefaultTableModel) tbbanhang.getModel();
            
            // get table Product details
            
            for (int i = 0; i < tbbanhang.getRowCount(); i++) {
                
                String Name = txtmahd.getText().trim();
                String Qty = df.getValueAt(i, 2).toString();
                String Price = df.getValueAt(i, 3).toString();
                
                b.setText(b.getText() +"  "+ Name+"\t\t"+Qty +"\t"+Price + "\n");
            }
            
            b.setText(b.getText() + "---------------------------------------------------------------------\n");
            b.setText(b.getText() + "Sub Total : " + txtmahd.getText() +"\n");
            b.setText(b.getText() + "Cash      : " + cbbsp.getSelectedItem().toString() +"\n");
            b.setText(b.getText() + "Balance   : " + txttongtienhd.getText() +"\n");
            b.setText(b.getText() + "---------------------------------------------------------------------\n");
            b.setText(b.getText() + "                     Thanks For Your Business...!"+"\n");
            b.setText(b.getText() + "---------------------------------------------------------------------\n");
            b.setText(b.getText() + "                     Software by Youtube Dapp Code"+"\n");
            
           
        b.print(); //print
            
            
        } catch (Exception e) {
            
            System.out.println(e);
            
            
        }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    
    
    
    
    
    
    // print bill
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addtb1tb2;
    private javax.swing.JTextArea b;
    private javax.swing.JButton btnaddkh;
    private javax.swing.JButton btnaddsp;
    private javax.swing.JButton btndeletekh;
    private javax.swing.JButton btndeletesp;
    private javax.swing.JButton btnrepairkh;
    private javax.swing.JButton btnrepairsp;
    private javax.swing.JButton btnresetkh;
    private javax.swing.JButton btnresetsp;
    private javax.swing.JButton btnsearch1;
    private javax.swing.JButton btnsearchsp;
    private javax.swing.JButton btntendn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLoaiSp;
    private javax.swing.JComboBox<String> cbbsp;
    private javax.swing.JComboBox<String> cbbtenkh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblMaHoaDon_HoaDon;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tbbanhang;
    private javax.swing.JTable tbkhachhang;
    private javax.swing.JTable tbloaisp;
    private javax.swing.JTable tbsanpham;
    private javax.swing.JTable tbsanphambh;
    private javax.swing.JTextField txtdiachikh;
    private javax.swing.JTextField txtgiaban;
    private javax.swing.JTextField txtgiabanhd;
    private javax.swing.JTextField txtgianhap;
    private javax.swing.JTextField txtmahd;
    private javax.swing.JTextField txtmakh;
    private javax.swing.JTextField txtmasp;
    private com.toedter.calendar.JDateChooser txtngayban;
    private com.toedter.calendar.JDateChooser txtngaygiao;
    private javax.swing.JTextField txtsodtkh;
    private javax.swing.JTextField txtsolghd;
    private javax.swing.JTextField txttenkh;
    private javax.swing.JTextField txttensp;
    private javax.swing.JTextField txttongtienhd;
    private javax.swing.JTextField txttrangthai;
    // End of variables declaration//GEN-END:variables

}
