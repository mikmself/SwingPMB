package component;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
public class Service {
    Connection conn;
    public String koneksi(){
        String urlDB = "jdbc:mysql://localhost:3306/pmb"; 
        try {
            conn = DriverManager.getConnection(urlDB, "root", "");
            System.out.println("Sukses terhubung ke databs");
            return "Sukses terhubung ke databse";
        } catch (Exception e) {
            return e.toString();
        }
    }
    public DefaultTableModel TampilData(String namaTable, String daftarField[], String JudulKolom[]) {
        try {
            if (conn == null || conn.isClosed()) {
                throw new SQLException("Koneksi belum dibuka.");
            }
            DefaultTableModel tabelModel = new DefaultTableModel(JudulKolom, 0);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + namaTable);
            while (rs.next()) {
                Vector data = new Vector();
                for (int i = 0; i < daftarField.length; i++) {
                    data.addElement(rs.getString(daftarField[i]));
                }
                tabelModel.addRow(data);
            }
            return tabelModel;
        } catch (SQLException e) {
            e.printStackTrace();  
            return null;
        }
    }
    public String tambah(String namaTable, String daftarField, String valueField){
        try {
            if(conn.isClosed()){
                throw new SQLException("Koneksi belum dibuka");
            }else{
                Statement st = conn.createStatement();
                st.executeUpdate("INSERT INTO " + namaTable + " (" + daftarField + ") values(" + valueField +")");
                return "Berhasil memasukan data baru";
            }
        } catch (SQLException e) {
            return "Gagal memasukan data : " + e.getMessage();
        }
    }
    public String ubah(String namaTable, String data, String kondisi){
        try {
            if(conn.isClosed()){
                throw new SQLException("Koneksi belum dibuka");
            }else{
                Statement st = conn.createStatement();
                st.executeUpdate("UPDATE " + namaTable + " SET " + data + " WHERE " + kondisi);
                return "Berhasil mengupdate data";
            }
        } catch (SQLException e) {
            return "Gagal memasukan data : " + e.getMessage();
        }
    }
    public String hapus(String namaTable, String kondisi){
        try {
            if(conn.isClosed()){
                throw new SQLException("Koneksi belum dibuka");
            }else{
                Statement st = conn.createStatement();
                st.executeUpdate("DELETE FROM " + namaTable + " WHERE " + kondisi);
                return "Berhasil menghapus data";
            }
        } catch (SQLException e) {
            return "Gagal memasukan data : " + e.getMessage();
        }
    }
}
