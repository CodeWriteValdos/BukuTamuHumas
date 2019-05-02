package com.putaystudio.cj.cariaja.Model;

public class JSONResponse {
    private  ModelSiswa[] list_histori;

    public JSONResponse(ModelSiswa[] list_histori) {
        this.list_histori = list_histori;
    }

    public ModelSiswa[] getList_histori() {
        return list_histori;
    }

    public void setList_histori(ModelSiswa[] list_histori) {
        this.list_histori = list_histori;
    }
}
