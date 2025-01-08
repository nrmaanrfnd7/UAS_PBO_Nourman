package com.pbo.crud_mvc.model.dao;

import com.pbo.crud_mvc.model.Produk;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class ProdukDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_mahasiswa";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "your_password";

    // Add a new product
    public static void addProduk(Produk produk) throws SQLException {
        String query = "INSERT INTO produk (id_produk, nama_produk, harga, kategori) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, produk.getIDProduk());
            statement.setString(2, produk.getNama());
            statement.setString(3, produk.getHarga());
            statement.setInt(4, produk.getKategori());
            statement.executeUpdate();
        }
    }

    // Update an existing product
    public static void updateProduk(Produk produk) throws SQLException {
        String query = "UPDATE produk SET nama_produk = ?, harga = ?, kategori = ? WHERE id_produk = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, produk.getNama());
            statement.setString(2, produk.getHarga());
            statement.setInt(3, produk.getKategori());
            statement.setString(4, produk.getIDProduk());
            statement.executeUpdate();
        }
    }

    // Delete a product
    public static void deleteProduk(String idProduk) throws SQLException {
        String query = "DELETE FROM produk WHERE id_produk = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idProduk);
            statement.executeUpdate();
        }
    }

    // Get all products
    public static ObservableList<Produk> getAllProduk() throws SQLException {
        ObservableList<Produk> produkList = FXCollections.observableArrayList();
        String query = "SELECT * FROM produk";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String idProduk = resultSet.getString("id_produk");
                String namaProduk = resultSet.getString("nama_produk");
                String harga = resultSet.getString("harga");
                int kategori = resultSet.getInt("kategori");

                produkList.add(new Produk(idProduk, namaProduk, harga, kategori));
            }
        }
        return produkList;
    }
}
