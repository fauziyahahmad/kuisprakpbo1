import java.awt.FlowLayout;
import java.sql.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class LihatData extends JFrame{
    String[] [] data = new String [500] [8]; //baris, kolom
    String[] kolom = {"Nama", "NIM", "Mata Kuliah 1", "Nilai 1", "Mata Kuliah 2", "Nilai 2", "Rata-Rata", "Konversi"}; //menamai kolom di atasnya, ga harus sama kayak di database
    JTable tabel;
    JScrollPane scrollPane;
    Statement statement;
    ResultSet resultSet;
    
    public void LihatDatanya(){
        setTitle("Data Nilai Mahasiswa");
        try{
            KoneksiDB koneksi = new KoneksiDB();
            statement = koneksi.getKoneksi().createStatement();
            
            String sql = "SELECT * FROM kuis";
            resultSet = statement.executeQuery(sql);
            
            int p=0;
            while(resultSet.next()){
                data[p][0] = resultSet.getString("nama"); //disesuaikan dg nama di database
                data[p][1] = resultSet.getString("nim");
                data[p][2] = resultSet.getString("mk1");
                data[p][3] = resultSet.getString("n1");
                data[p][4] = resultSet.getString("mk2");
                data[p][5] = resultSet.getString("n2");
                data[p][6] = resultSet.getString("rataRata");
                data[p][7] = resultSet.getString("huruf");
                p++;
            }
            statement.close();
            koneksi.getKoneksi().close();
            
        }
        catch (SQLException sqle){
            JOptionPane.showMessageDialog(rootPane, "Data gagal ditampilkan" + sqle);
        }
        
        catch (ClassNotFoundException classe){
            JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan" + classe);  
        }
          
        
        tabel = new JTable(data,kolom);
        scrollPane = new JScrollPane(tabel);
        
        setLayout(new FlowLayout());
        add(scrollPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        
    }
}
