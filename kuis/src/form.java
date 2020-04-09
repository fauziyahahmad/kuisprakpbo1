
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class form extends JFrame{
    JLabel nim,nama,mk1,mk2,n1,n2; //variabel apa saja yg dibutuhkan
    JTextField txnim,txnama,txmk1, txmk2, txn1, txn2;
    JButton convert;
    Statement statement;
    
    public void isiform (){
        setTitle("Hitung Nilai"); //judul window jframe
        
        nim = new JLabel("NIM"); //tampilan nama JLabel yg muncul di window JFrame
        nama = new JLabel("Nama");        
        mk1 = new JLabel("Mata Kuliah 1");
        mk2 = new JLabel("Mata Kuliah 2");
        n1 = new JLabel("Nilai 1");
        n2 = new JLabel("Nilai 2");
        
        txnim = new JTextField("");
        txnama = new JTextField("");
        txmk1 = new JTextField("");
        txmk2 = new JTextField("");
        txn1 = new JTextField("");
        txn2 = new JTextField("");
        
        convert = new JButton("Convert");
        
        setLayout(null);
        add(nim);
        add(nama);
        add(mk1);
        add(mk2);
        add(n1);
        add(n2);
        add(txnim);
        add(txnama);
        add(txmk1);
        add(txmk2);
        add(txn1);
        add(txn2);
        add(convert);
        
        nim.setBounds(30, 25, 100, 20); //menentukan posisi tiap variabel
        nama.setBounds(30, 50, 100, 20);
        mk1.setBounds(30, 75, 100, 20);
        n1.setBounds(30, 100, 100, 20);
        mk2.setBounds(30, 125, 100, 20);
        n2.setBounds(30, 150, 100, 20);
        txnim.setBounds(150, 25, 200, 20);
        txnama.setBounds(150, 50, 200, 20);
        txmk1.setBounds(150, 75, 200, 20);
        txn1.setBounds(150, 100, 200, 20);
        txmk2.setBounds(150, 125, 200, 20);
        txn2.setBounds(150, 150, 200, 20);
        convert.setBounds(140, 200, 100, 20);
         
        setSize(400,300); //ukuran JFrame  
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        convert.addActionListener(new ActionListener() { //respon terhadap klik tombol Convert
            @Override
            public void actionPerformed(ActionEvent e) { //menambahkan method untuk menangani klik pada tombol
                try { //kode yg dieksekusi ketika tombol diklik
                String a1 = txnama.getText(); //mengambil text yg sudah dimasukkan
                int a2 =  Integer.parseInt(txnim.getText());
                String a3 = txmk1.getText();
                int a4 =  Integer.parseInt(txn1.getText());
                String a5 = txmk2.getText();
                int a6 =  Integer.parseInt(txn2.getText());
                float rataRata = (float)(a4+a6)/2;
                String huruf = convertNilaiRerataKeNilaiHuruf(rataRata);
                        
                KoneksiDB koneksi = new KoneksiDB();
                    try {
                        statement = koneksi.getKoneksi().createStatement(); //statement digunakan untuk mempresentasikan suatu perintah SQL
                        statement.executeUpdate("INSERT INTO kuis VALUES ('" 
                        + a1 + "','" + a2 + "','" + a3 + "','" + a4 + "','" +a5 + "','" + a6 + "','" + rataRata + "','" + huruf + "')"); //memasukkan ke tabel di database
                        JOptionPane.showMessageDialog(rootPane, "Data tersimpan");
                        
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                System.out.println("Nama = "+a1); //meng outputkan data yg diminta
                System.out.println("NIM = "+a2);
                System.out.println("Mata Kuliah 1 = "+a3);
                System.out.println("Mata Kuliah 2 = "+a5);
                System.out.println("Rata-rata = "+ rataRata);
                System.out.println("Nilai Huruf = "+ huruf);
                    
                } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(rootPane,"TIPE DATA SALAH"); //exception jika data yg diminta masukkan adalah angka tapi yg kita tulis huruf
                } catch (Error ext){
                 JOptionPane.showMessageDialog(rootPane,"SALAH");
                }
                
            }
            
            private String convertNilaiRerataKeNilaiHuruf(float rataRata){ //mengkonversi nilai rata2 yg sudah dimasukkan jadi nilai huruf
                if (rataRata>=80 && rataRata <=100) return "A";
                else if (rataRata>=75 && rataRata <80) return "B+";
                else if (rataRata>=65 && rataRata <75) return "B";
                else if (rataRata>=60 && rataRata <65) return "C+";
                else if (rataRata>=50 && rataRata <60) return "C";
                else if (rataRata>=20 && rataRata <50) return "D";
                else if (rataRata>=0 && rataRata <20) return "E";
                else return "Error";
            }
        });
    }
}
