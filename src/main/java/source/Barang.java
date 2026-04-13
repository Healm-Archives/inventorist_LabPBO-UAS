package source;

import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Barang {
    public int Id;   
    public String nama;
    public int kuantitas;
    public String imagePath;
    public String tipe;
    public ImageView imageView;
    
    public int getId() {return Id;}
    public String getNama() {return nama;}
    public int getKuantitas() {return kuantitas;}
    public String getImagePath() {return imagePath;}
    public String getTipe() {return tipe;}
    public ImageView getImageView() {return imageView;}
    
    public void setId(int id) {Id = id;}
    public void setNama(String nama) {this.nama = nama;}
    public void setKuantitas(int kuantitas) {this.kuantitas = kuantitas;}
    public void setImagePath(String imagePath) {this.imagePath = imagePath;}
    public void setTipe(String tipe) {this.tipe = tipe;}
    public void setImageView(ImageView imageView) {this.imageView = imageView;}
    
    public Barang(String nama, int kuantitas, String imagePath, String tipe) {
        this.nama = nama;
        this.kuantitas = kuantitas;
        this.tipe = tipe;
        this.imagePath = imagePath;
    }
        
    public Barang(TextField nama, TextField kuantitas, TextField imagePath, TextField tipe) {
        this.nama = nama.getText();
        this.kuantitas = Integer.parseInt(kuantitas.getText());
        this.tipe = tipe.getText();
        this.imagePath = imagePath.getText();
    }
}
