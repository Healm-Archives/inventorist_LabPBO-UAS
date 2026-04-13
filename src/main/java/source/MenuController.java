package source;

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
        // TableColumn<Barang, Integer> colId = new TableColumn<Barang, Integer>("ID");
        TableColumn<Barang, String> colNama = new TableColumn<Barang, String>("Nama");
        TableColumn<Barang, Integer> colKuantitas = new TableColumn<Barang, Integer>("Qty");
        TableColumn<Barang, ImageView> colImage = new TableColumn<Barang, ImageView>("Gambar");
        TableColumn<Barang, String> colTipe = new TableColumn<Barang, String>("Tipe");

        // colId.setCellValueFactory(new PropertyValueFactory<Barang, Integer>("id"));
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
        // loadData();
        // tableViewBarang.getItems().addAll(loadData());
        tableViewBarang.getItems().addAll(loadDb());
        // tableViewBarang.getItems().addAll(App.barangList);
        tableViewBarang.getItems().addAll();
        // loadimage();
    }

    private static void loadimage() throws SQLException, IOException{
        PreparedStatement psmt = Db.connection.prepareStatement("SELECT * FROM inventory;");
        ResultSet rs = psmt.executeQuery();
        if(rs.next()) {
            Blob blob = rs.getBlob(5);
            System.out.println("byte length : " + blob.length());   
            byte barr[] = blob.getBytes(1, (int) blob.length());
            FileOutputStream fout = new FileOutputStream("d:\\ok.jpg");
            
            fout.write(barr);
            fout.close();
        }
    }

    private static ObservableList<Barang> loadDb() throws SQLException, IOException{
        // Barang b1 = new Barang("A", 1, "alp");
        // Barang b2 = new Barang("B", 1, "alp");
        // Barang b3 = new Barang("C", 2, "alp");
        // Barang b4 = new Barang("D", 1, "alp");
        String cdImage = cd + "/src/main/resources/image/";

        PreparedStatement psmt = Db.connection.prepareStatement(
            "SELECT nama, kuantitas, image, tipe FROM inventory;");
        ResultSet rs = psmt.executeQuery();

        ObservableList<Barang> daftarBarang = FXCollections.observableArrayList();
        int i = 1;
        while (rs.next()){
            String barangNama = rs.getString("nama");
            int barangKuantitas = rs.getInt("kuantitas");
            String barangTipe = rs.getString("tipe");

            Blob barangImage = rs.getBlob("image");
            byte barr[] = barangImage.getBytes(1, (int) barangImage.length());
            
            FileOutputStream fout = new FileOutputStream(cdImage + "blobby_" + i + ".jpg");

            fout.write(barr);
            fout.close();

            // imageViewPreview.setImage(new Image(new FileInputStream(textFieldImagePath.getText())));

            
            Barang barang = new Barang(barangNama, barangKuantitas, "none", barangTipe);
            ImageView imageViewPreview = new ImageView(new Image(new FileInputStream(cdImage + "blobby_" + i + ".jpg")));
            imageViewPreview.setPreserveRatio(true);
            imageViewPreview.setFitHeight(100);
            
            // barang.setImageView(new ImageView(new Image(new FileInputStream(cdImage + "blobby_" + i + ".jpg"))));
            barang.setImageView(imageViewPreview);

            daftarBarang.add(barang);
            i++;
        }

        return daftarBarang;
    }

    @FXML private void actionCreate() throws IOException {
        App.setRoot("inputmenu");
    }   

}
