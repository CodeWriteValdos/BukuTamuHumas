package com.putaystudio.cj.cariaja.Model;

public class ModelHistori {
    String id,nis,created_at;

    public ModelHistori(String id, String nis, String created_at) {
        this.id = id;
        this.nis = nis;
        this.created_at = created_at;
    }

    public ModelHistori() {
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
