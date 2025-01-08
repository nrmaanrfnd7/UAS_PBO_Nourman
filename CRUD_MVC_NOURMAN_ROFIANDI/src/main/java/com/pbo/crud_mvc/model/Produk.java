package com.pbo.crud_mvc.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produk {
    private final SimpleStringProperty idProduk;
    private final SimpleStringProperty nama;
    private final SimpleStringProperty harga;
    private final SimpleIntegerProperty kategori;

    public Produk(String idProduk, String nama, String harga, int kategori) {
        this.idProduk = new SimpleStringProperty(idProduk);
        this.nama = new SimpleStringProperty(nama);
        this.harga = new SimpleStringProperty(harga);
        this.kategori = new SimpleIntegerProperty(kategori);
    }

    // Getters
    public String getIDProduk() {
        return idProduk.get();
    }

    public String getNama() {
        return nama.get();
    }

    public String getHarga() {
        return harga.get();
    }

    public int getKategori() {
        return kategori.get();
    }

    public String getKategoriNama() {
        switch (kategori.get()) {
            case 1: return "Makanan Ringan";
            case 2: return "Sembako";
            case 3: return "Minuman";
            case 4: return "Alat Sekolah";
            default: return "Tidak Diketahui";
        }
    }

    // Property Getters for Binding
    public SimpleStringProperty idProdukProperty() {
        return idProduk;
    }

    public SimpleStringProperty namaProperty() {
        return nama;
    }

    public SimpleStringProperty hargaProperty() {
        return harga;
    }

    public SimpleStringProperty kategoriNamaProperty() {
        return new SimpleStringProperty(getKategoriNama());
    }
}
