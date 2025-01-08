package com.pbo.crud_mvc.controller;

import com.pbo.crud_mvc.model.Produk;
import com.pbo.crud_mvc.model.dao.ProdukDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class MainController {

    @FXML
    private TableView<Produk> addProduk_tableview;

    @FXML
    private TableColumn<Produk, String> table_column_id_produk;
    @FXML
    private TableColumn<Produk, String> table_column_nama_produk;
    @FXML
    private TableColumn<Produk, String> table_column_harga;
    @FXML
    private TableColumn<Produk, String> table_column_kategori;

    @FXML
    private TextField addProduk_ID_ProdukField;
    @FXML
    private TextField addProduk_namaProdukField;
    @FXML
    private TextField addProduk_HargaField;

    @FXML
    private ComboBox<String> addProduk_kategori;

    @FXML
    private void initialize() {
        // Set item untuk ComboBox
        addProduk_kategori.setItems(FXCollections.observableArrayList(
                "Makanan Ringan", "Sembako", "Minuman", "Alat Sekolah"
        ));

        // Set cell value factory untuk tabel
        table_column_id_produk.setCellValueFactory(cellData -> cellData.getValue().idProdukProperty());
        table_column_nama_produk.setCellValueFactory(cellData -> cellData.getValue().namaProperty());
        table_column_harga.setCellValueFactory(cellData -> cellData.getValue().hargaProperty());
        table_column_kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriNamaProperty());

        // Load data ke tabel
        updateTable();
    }


    @FXML
    private void handleAddProduk() {
        String idProduk = addProduk_ID_ProdukField.getText().trim();
        String nama = addProduk_namaProdukField.getText().trim();
        String harga = addProduk_HargaField.getText().trim();
        String kategori = addProduk_kategori.getValue();

        if (idProduk.isEmpty() || nama.isEmpty() || harga.isEmpty() || kategori == null) {
            System.out.println("Semua field harus diisi.");
            return;
        }

        try {
            int kategoriId = mapkategoriToId(kategori);
            Produk produk = new Produk(idProduk, nama, harga, kategoriId);
            ProdukDAO.addProduk(produk);
            clearFields();
            updateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemoveProduk() {
        Produk produk = addProduk_tableview.getSelectionModel().getSelectedItem();
        if (produk != null) {
            try {
                ProdukDAO.deleteProduk(produk.getIDProduk());
                updateTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void updateTable() {
        try {
            // Pastikan mendapatkan data dari DAO
            ObservableList<Produk> produkList = FXCollections.observableArrayList(ProdukDAO.getAllProduk());
            addProduk_tableview.setItems(produkList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private int mapkategoriToId(String kategori) {
        switch (kategori) {
            case "Makanan Ringan":
                return 1;
            case "Sembako":
                return 2;
            case "Minuman":
                return 3;
            case "Alat Sekolah":
                return 4;
            default:
                return 0;
        }
    }

    @FXML
    private void handleTableClick(MouseEvent event) {
        Produk selectedProduk = addProduk_tableview.getSelectionModel().getSelectedItem();
        if (selectedProduk != null) {
            addProduk_ID_ProdukField.setText(selectedProduk.getIDProduk());
            addProduk_namaProdukField.setText(selectedProduk.getNama());
            addProduk_HargaField.setText(selectedProduk.getHarga());
            addProduk_kategori.setValue(selectedProduk.getKategoriNama());
        }
    }

    @FXML
    private void clearFields() {
        addProduk_ID_ProdukField.clear();
        addProduk_namaProdukField.clear();
        addProduk_HargaField.clear();
        addProduk_kategori.getSelectionModel().clearSelection();
    }
}
