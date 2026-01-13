/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_2310010124;

import java.io.File;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author LENOVO
 */
public class crud {
    private String dataBase = "pbo_2310010124";
    private String userName = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost/"+dataBase;
    public Connection koneksiDB;
    public String var_nm_program, var_nm_kategori, var_nm_penerima, var_nm_kecamatan, var_nm_kelurahan, var_jenis_bantuan,
            var_tgl_mulai, var_tgl_selesai, var_ket, var_nik, var_alamat, var_sts_bantuan, var_kode_pos, var_keterangan  = null;
    public boolean validasi = false;

public crud(){
    try {
        Driver driverKoneksi = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driverKoneksi);
        koneksiDB = DriverManager.getConnection(url, userName, password);
        System.out.print("Berhasil Koneksi");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, " Terjadi Error : \n" + e.getMessage());
    }
}

public void loadData(JTable tabel, String sql){
    try {
            Statement perintah = koneksiDB.createStatement();
            ResultSet ds = perintah.executeQuery(sql);
            ResultSetMetaData data = ds.getMetaData();
            int kolom = data.getColumnCount();
            DefaultTableModel model = new DefaultTableModel();
            
            for (int i = 1; i <= kolom; i++ ){
                model.addColumn(data.getColumnName(i));
            } 
            model.getDataVector();
            model.fireTableDataChanged();
            while (ds.next()){
                Object[] baris = new Object[kolom];
                for (int j = 1; j <= kolom; j++){
                    baris[j - 1] = ds.getObject(j);
                }
                model.addRow(baris);
            }
            tabel.setModel(model);
        } catch (Exception e) {
        }
    }

public void simpanDataPenerimaPRT(String id_penerima, String nm_penerima, String nik,
        String alamat, String id_program, String id_wilayah, String status_bantuan){
    try {
        String sql = "INSERT INTO data_penerima (id_penerima, nama_penerima, nik, alamat, id_program,"
                + " id_wilayah, status_bantuan) VALUE (?, ?, ?, ?, ?, ?, ?)";
        String checkPrimary = "SELECT * FROM data_penerima WHERE id_penerima = '"+id_penerima+"'";
        Statement checkdata = koneksiDB.createStatement();
        ResultSet data = checkdata.executeQuery(checkPrimary);
        if (data.next()){
            String isi = "\nNama Penerima : " + data.getString("nama_penerima") + 
                    "\nNik : " + data.getString("nik") + 
                    "\nAlamat : " + data.getString("alamat") +
                    "\nStatus Bantuan : " + data.getString("status_bantuan") ;
            JOptionPane.showMessageDialog(null, "ID Penerima sudah terdaftar" + isi);
            
            this.validasi = true;
            this.var_nm_penerima = data.getString("nama_penerima");
            this.var_nik = data.getString("nik");
            this.var_alamat = data.getString("alamat");
            this.var_sts_bantuan = data.getString("status_bantuan");
            
        } else {
            PreparedStatement perintah = koneksiDB.prepareStatement(sql);
            perintah.setString(1, id_penerima);
            perintah.setString(2, nm_penerima);
            perintah.setString(3, nik);
            perintah.setString(4, alamat);
            perintah.setString(5, id_program);
            perintah.setString(6, id_wilayah);
            perintah.setString(7, status_bantuan);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "BERHASIL DISIMPAN");
            
            this.validasi = false;
            this.var_nm_penerima = null;
            this.var_nik = null;
            this.var_alamat = null;
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
//          System.err.print(e.getMessage());
    }
}

public void simpanProgramBantuanPRT(String id_program, String nm_program, String jenis_bantuan,
        String tgl_mulai, String tgl_selesai, String id_kategori){
    try {
        String sql = "INSERT INTO program_bantuan (id_program, nama_program, jenis_bantuan,"
                + " tanggal_mulai, tanggal_selesai, id_kategori) VALUE (?, ?, ?, ?, ?, ?)";
        String checkPrimary = "SELECT * FROM program_bantuan WHERE id_program = '"+id_program+"'";
        Statement checkdata = koneksiDB.createStatement();
        ResultSet data = checkdata.executeQuery(checkPrimary);
        if (data.next()){
            String isi = "\nNama Program : " + data.getString("nama_program") + 
                    "\nJenis Bantuan : " + data.getString("jenis_bantuan") + 
                    "\nTanggal Mulai : " + data.getString("tanggal_mulai") +
                    "\nTanggal Selesai : " + data.getString("tanggal_selesai") ;
            JOptionPane.showMessageDialog(null, "ID Program sudah terdaftar" + isi);
            
            this.validasi = true;
            this.var_nm_program = data.getString("nama_program");
            this.var_jenis_bantuan = data.getString("jenis_bantuan");
            this.var_tgl_mulai = data.getString("tanggal_mulai");
            this.var_tgl_selesai = data.getString("tanggal_selesai");
            
        } else {
            PreparedStatement perintah = koneksiDB.prepareStatement(sql);
            perintah.setString(1, id_program);
            perintah.setString(2, nm_program);
            perintah.setString(3, jenis_bantuan);
            perintah.setString(4, tgl_mulai);
            perintah.setString(5, tgl_selesai);
            perintah.setString(6, id_kategori);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "BERHASIL DISIMPAN");
            
            this.validasi = false;
            this.var_nm_program = null;
            this.var_jenis_bantuan = null;
            this.var_tgl_mulai = null;
            this.var_tgl_selesai = null;
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
//          System.err.print(e.getMessage());
    }
}

public void simpanKategoriPRT(String id_kategori, String nm_kategori, String keterangan){
    try {
        String sql = "INSERT INTO kategori (id_kategori, nama_kategori, keterangan) VALUE (?, ?, ?)";
        String checkPrimary = "SELECT * FROM kategori WHERE id_kategori = '"+id_kategori+"'";
        Statement checkdata = koneksiDB.createStatement();
        ResultSet data = checkdata.executeQuery(checkPrimary);
        if (data.next()){
            String isi = "\nNama Kategori : " + data.getString("nama_kategori") + 
                    "\nKeterangan : " + data.getString("keterangan");
            JOptionPane.showMessageDialog(null, "ID Kategori sudah terdaftar" + isi);
            
            this.validasi = true;
            this.var_nm_kategori = data.getString("nama_kategori");
            this.var_keterangan = data.getString("keterangan");
            
        } else {
            PreparedStatement perintah = koneksiDB.prepareStatement(sql);
            perintah.setString(1, id_kategori);
            perintah.setString(2, nm_kategori);
            perintah.setString(3, keterangan);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "BERHASIL DISIMPAN");
            
            this.validasi = false;
            this.var_nm_kategori = null;
            this.var_keterangan = null;
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
//          System.err.print(e.getMessage());
    }
}

public void simpanWilayahPRT(String id_wilayah, String nm_kecamatan, String nm_kelurahan, String kode_pos){
    try {
        String sql = "INSERT INTO wilayah (id_wilayah, nama_kecamatan, nama_kelurahan, kode_pos) VALUE (?, ?, ?, ?)";
        String checkPrimary = "SELECT * FROM wilayah WHERE id_wilayah = '"+id_wilayah+"'";
        Statement checkdata = koneksiDB.createStatement();
        ResultSet data = checkdata.executeQuery(checkPrimary);
        if (data.next()){
            String isi = "\nNama Kecamatan : " + data.getString("nama_kecamatan") + 
                    "\nNama Kelurahan : " + data.getString("nama_kelurahan") +
                    "\nKode Pos : " + data.getString("kode_pos");
            JOptionPane.showMessageDialog(null, "ID Wilayah sudah terdaftar" + isi);
            
            this.validasi = true;
            this.var_nm_kecamatan = data.getString("nama_kecamatan");
            this.var_nm_kelurahan = data.getString("nama_kelurahan");
            this.var_kode_pos = data.getString("kode_pos");
            
        } else {
            PreparedStatement perintah = koneksiDB.prepareStatement(sql);
            perintah.setString(1, id_wilayah);
            perintah.setString(2, nm_kecamatan);
            perintah.setString(3, nm_kelurahan);
            perintah.setString(4, kode_pos);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "BERHASIL DISIMPAN");
            
            this.validasi = false;
            this.var_nm_kecamatan = null;
            this.var_nm_kelurahan = null;
            this.var_kode_pos = null;
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
//          System.err.print(e.getMessage());
    }
}

public void ubahDataPenerimaPRT(String id_penerima, String nm_penerima, String nik, String alamat,
        String id_program, String id_wilayah, String status_bantuan){
    try {
        String sqlUbah = "UPDATE data_penerima SET nama_penerima = ?, nik = ?, alamat = ?, id_program = ?, id_wilayah = ?,"
                + " status_bantuan = ? WHERE id_penerima = ?"; 
        
        PreparedStatement ubah = koneksiDB.prepareStatement(sqlUbah);
        ubah.setString(1, nm_penerima);
        ubah.setString(2, nik);
        ubah.setString(3, alamat);
        ubah.setString(4, id_program);
        ubah.setString(5, id_wilayah);
        ubah.setString(6, status_bantuan);
        ubah.setString(7, id_penerima);
        
        ubah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

public void ubahProgramBantuanPRT(String id_program, String nm_program, String jenis_bantuan, String tgl_mulai,
        String tgl_selesai, String id_kategori){
    try {
        String sqlUbah = "UPDATE program_bantuan SET nama_program= ?, jenis_bantuan = ?, tanggal_mulai = ?,"
                + " tanggal_selesai = ?, id_kategori = ? WHERE id_program = ?"; 
        
        PreparedStatement ubah = koneksiDB.prepareStatement(sqlUbah);
        ubah.setString(1, nm_program);
        ubah.setString(2, jenis_bantuan);
        ubah.setString(3, tgl_mulai);
        ubah.setString(4, tgl_selesai);
        ubah.setString(5, id_kategori);
        ubah.setString(6, id_program);
        
        ubah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

public void ubahKategoriPRT(String id_kategori, String nm_kategori, String keterangan){
    try {
        String sqlUbah = "UPDATE kategori SET nama_kategori= ?, keterangan = ? WHERE id_kategori = ?"; 
        
        PreparedStatement ubah = koneksiDB.prepareStatement(sqlUbah);
        ubah.setString(1, nm_kategori);
        ubah.setString(2, keterangan);
        ubah.setString(3, id_kategori);
        
        ubah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

public void ubahWilayahPRT(String id_wilayah, String nm_kecamatan, String nm_kelurahan, String kode_pos){
    try {
        String sqlUbah = "UPDATE wilayah SET nama_kecamatan = ?, nama_kelurahan = ?, kode_pos = ? WHERE id_wilayah = ?"; 
        
        PreparedStatement ubah = koneksiDB.prepareStatement(sqlUbah);
        ubah.setString(1, nm_kecamatan);
        ubah.setString(2, nm_kelurahan);
        ubah.setString(3, kode_pos);
        ubah.setString(4, id_wilayah);
        
        ubah.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

public void hapusDataPenerimaPRT(String id_penerima){
    try {
        String sqlHapus = "DELETE FROM data_penerima WHERE id_penerima = ? ";
        PreparedStatement hapus = koneksiDB.prepareStatement(sqlHapus);
        hapus.setString(1, id_penerima);
        hapus.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Data Berhasil dihapus!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

public void hapusProgramBantuanPRT(String id_program){
    try {
        String sqlHapus = "DELETE FROM program_bantuan WHERE id_program = ? ";
        PreparedStatement hapus = koneksiDB.prepareStatement(sqlHapus);
        hapus.setString(1, id_program);
        hapus.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Data Berhasil dihapus!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

public void hapusKategoriPRT(String id_kategori){
    try {
        String sqlHapus = "DELETE FROM kategori WHERE id_kategori = ? ";
        PreparedStatement hapus = koneksiDB.prepareStatement(sqlHapus);
        hapus.setString(1, id_kategori);
        hapus.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Data Berhasil dihapus!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

public void hapusWilayahPRT(String id_wilayah){
    try {
        String sqlHapus = "DELETE FROM wilayah WHERE id_wilayah = ? ";
        PreparedStatement hapus = koneksiDB.prepareStatement(sqlHapus);
        hapus.setString(1, id_wilayah);
        hapus.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Data Berhasil dihapus!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

public void cetakLaporan(String fileLaporan, String sql){
    try {
        File laporan = new File(fileLaporan);
        JasperDesign designLaporan = JRXmlLoader.load(laporan);
        JRDesignQuery queryLaporan = new JRDesignQuery();
        queryLaporan.setText(sql);
        designLaporan.setQuery(queryLaporan);
        JasperReport objekLaporan = JasperCompileManager.compileReport(designLaporan);
        JasperPrint objekPrint = JasperFillManager.fillReport(objekLaporan, null, this.koneksiDB);
        JasperViewer.viewReport(objekPrint, false);

    } catch (Exception e) {
    }
}

}



