package com.putaystudio.cj.cariaja.Model;

public class ModelSiswa {
    String id,nis,nama,foto,rayon_id,rombel_id,qrcode,rayon,rombel,created_at;

    public ModelSiswa(String id, String nis, String nama, String foto, String rayon_id, String rombel_id, String qrcode, String rayon, String rombel, String created_at) {
        this.id = id;
        this.nis = nis;
        this.nama = nama;
        this.foto = foto;
        this.rayon_id = rayon_id;
        this.rombel_id = rombel_id;
        this.qrcode = qrcode;
        this.rayon = rayon;
        this.rombel = rombel;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getRayon_id() {
        return rayon_id;
    }

    public void setRayon_id(String rayon_id) {
        this.rayon_id = rayon_id;
    }

    public String getRombel_id() {
        return rombel_id;
    }

    public void setRombel_id(String rombel_id) {
        this.rombel_id = rombel_id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getRombel() {
        return rombel;
    }

    public void setRombel(String rombel) {
        this.rombel = rombel;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
