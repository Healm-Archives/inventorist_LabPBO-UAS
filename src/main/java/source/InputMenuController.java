package source;

import java.io.File;
import java.io.FileInputStream;

// import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
// import com.sun.javafx.application.HostServicesDelegate;
// import javafx.application.HostServices;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InputMenuController {
    @FXML private ImageView imageViewPreview;
    @FXML private TextField textFieldNama;
    @FXML private TextField textFieldKuantitas;
    @FXML private TextField textFieldImagePath;
    @FXML private TextField textFieldTipe;
    @FXML private Label labelNama;
    @FXML private Label labelKuantitas;
    @FXML private Label labelImagePath;
    @FXML private Label labelTipe;
    
    FileInputStream fin;
    // Blob blob;
    // private HostServices hostServices;

    @FXML private void actionSubmit() throws IOException{
        if(textFieldNama.getLength() == 0){ return; }
        if(textFieldKuantitas.getLength() == 0){ return; }
        // if(textFieldUrl.getLength() == 0){ return; }
        if(textFieldTipe.getLength() == 0){ return; }

        Barang barang = new Barang(textFieldNama, 
        textFieldKuantitas, 
        textFieldImagePath, 
        textFieldTipe);

        // App.barangList.add(barang);
        // fin = new FileInputStream("D:\\Data\\Kevin M\\susdo\\Semester 4\\LabPBO\\JFX\\inventorist/src/main/resources/image/stock_image1.jpg");
        fin = new FileInputStream(barang.getImagePath());
        
        try {
            PreparedStatement psmt = Db.connection.prepareStatement(
                "INSERT INTO inventory (nama, kuantitas, tipe, image) VALUES (?, ?, ?, ?);"
            );
            psmt.setString(1, barang.getNama());
            
            psmt.setInt(2, barang.getKuantitas());
            // psmt.setString(3, barang.getUrl());
            psmt.setString(3, barang.getTipe());
            // psmt.setBinaryStream(5, fin, fin.available());
            psmt.setBlob(4, fin);
            
            psmt.executeUpdate();
        }
        catch (SQLException sql){
            System.out.println(sql.getLocalizedMessage());
        }

        App.setRoot("menu");
    }
    // data:image/jpeg;base64,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
    // https://www.google.com/imgres?q=cat&imgurl=https%3A%2F%2Fwww.wfla.com%2Fwp-content%2Fuploads%2Fsites%2F71%2F2023%2F05%2FGettyImages-1389862392.jpg%3Fw%3D2560%26h%3D1440%26crop%3D1&imgrefurl=https%3A%2F%2Fwww.wfla.com%2Fbloom-tampa-bay%2F10-surprising-benefits-of-having-a-cat-in-your-life%2F&docid=VgGX1b8PEJ1vRM&tbnid=LqbY6uqf87_ubM&vet=12ahUKEwjP-erLxK-GAxUfyzgGHXYsCDYQM3oECE4QAA..i&w=2560&h=1440&hcb=2&ved=2ahUKEwjP-erLxK-GAxUfyzgGHXYsCDYQM3oECE4QAA

    @FXML private void actionImagePath() throws IOException, SQLException {
        
        // HostServicesDelegate hostServices = HostServicesFactory.getinstance(this);
        // https://docs.oracle.com/javafx/2/api/javafx/application/HostServices.html#showDocument(java.lang.String)
        
        // String imagelink = "data:image/jpeg;base64,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";
        // String addrLink = "https://www.google.com/imgres?q=cat&imgurl=https%3A%2F%2Fwww.wfla.com%2Fwp-content%2Fuploads%2Fsites%2F71%2F2023%2F05%2FGettyImages-1389862392.jpg%3Fw%3D2560%26h%3D1440%26crop%3D1&imgrefurl=https%3A%2F%2Fwww.wfla.com%2Fbloom-tampa-bay%2F10-surprising-benefits-of-having-a-cat-in-your-life%2F&docid=VgGX1b8PEJ1vRM&tbnid=LqbY6uqf87_ubM&vet=12ahUKEwjP-erLxK-GAxUfyzgGHXYsCDYQM3oECE4QAA..i&w=2560&h=1440&hcb=2&ved=2ahUKEwjP-erLxK-GAxUfyzgGHXYsCDYQM3oECE4QAA";
        
        // Image image = new Image(imagelink);
        // System.out.println(imageViewPreview.setImage(););
        
        // url load, tp img g render
        // Image image = new Image(addrLink);
        // imageViewPreview.setImage(image);
        // System.out.println(image.getUrl());
        // System.out.println(imageViewPreview.getImage().getUrl());
        
        // hostServices.showDocument(addrLink);


        // fin = new FileInputStream("D:/Data/Kevin M/susdo/Semester 4/LabPBO/JFX/inventorist/src/main/resources/image/stock_image1.jpg");
        // fin = new FileInputStream("D:\\Data\\Kevin M\\susdo\\Semester 4\\LabPBO\\JFX\\inventorist\\src\\main\\resources\\image\\stock_image1.jpg");
        // fin = new FileInputStream("D:\\Data\\Kevin M\\susdo\\Semester 4\\LabPBO\\JFX\\inventorist/src/main/resources/image/stock_image1.jpg");
        // System.out.println(fin.available());
        // System.out.println(fin.read());
        // fin.
        
        // blob.setBytes(0, fin.readAllBytes());
        // System.out.println("blob : " + blob.length());

        imageViewPreview.setImage(new Image(new FileInputStream(textFieldImagePath.getText())));
        // imageViewPreview.setPreserveRatio(true);
        // imageViewPreview.setFitHeight(100);
        
    }
}
