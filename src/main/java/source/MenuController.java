package source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController {
    @FXML private Button buttonCreate;
    @FXML private TableView<Barang> tableViewBarang;

    private ObservableList<TableColumn<Barang, ?>> cols = FXCollections.observableArrayList();
    private static String cd = System.getProperty("user.dir");


    @SuppressWarnings("unchecked")
    public void initialize() throws IOException, SQLException{
        TableColumn<Barang, String> colNama = new TableColumn<Barang, String>("Nama");
        TableColumn<Barang, Integer> colKuantitas = new TableColumn<Barang, Integer>("Qty");
        TableColumn<Barang, ImageView> colImage = new TableColumn<Barang, ImageView>("Gambar");
        TableColumn<Barang, String> colTipe = new TableColumn<Barang, String>("Tipe");

        colNama.setCellValueFactory(new PropertyValueFactory<Barang, String>("nama"));
        colKuantitas.setCellValueFactory(new PropertyValueFactory<Barang, Integer>("kuantitas"));
        colImage.setCellValueFactory(new PropertyValueFactory<Barang, ImageView>("imageView"));
        colTipe.setCellValueFactory(new PropertyValueFactory<Barang, String>("tipe"));

        cols.addAll(
            colNama,
            colKuantitas,
            colImage,
            colTipe
        );  

        tableViewBarang.getColumns().addAll(cols);
        tableViewBarang.getItems().addAll(loadDb());
    }

    private static ObservableList<Barang> loadDb() throws SQLException, IOException{
        ObservableList<Barang> daftarBarang = FXCollections.observableArrayList();
        String cdImage = cd + "/src/main/resources/image/";
        String pathToImage;

        PreparedStatement psmt = Db.connection.prepareStatement(
            "SELECT nama, kuantitas, image, tipe FROM inventory;");
        ResultSet rs = psmt.executeQuery();

        while (rs.next()){
            String barangNama = rs.getString("nama");
            int barangKuantitas = rs.getInt("kuantitas");
            Blob barangImage = rs.getBlob("image");
            String barangTipe = rs.getString("tipe");

            byte barr[] = barangImage.getBytes(1, (int) barangImage.length());

            pathToImage = cdImage + barangNama + ".jpg";
            File imageFile = new File(pathToImage);
            if (!imageFile.exists()){
                FileOutputStream fout = new FileOutputStream(pathToImage);
                fout.write(barr);
                fout.close();
            }

            Barang barang = new Barang(barangNama, barangKuantitas, "none", barangTipe);
            ImageView imageViewPreview = new ImageView(new Image(new FileInputStream(pathToImage)));
            imageViewPreview.setPreserveRatio(true);
            imageViewPreview.setFitHeight(100);
            
            barang.setImageView(imageViewPreview);

            daftarBarang.add(barang);
        }

        return daftarBarang;
    }

    @FXML private void actionCreate() throws IOException {
        App.setRoot("inputmenu");
    }   

}