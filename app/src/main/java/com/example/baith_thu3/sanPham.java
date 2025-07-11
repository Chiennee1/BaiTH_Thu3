package com.example.baith_thu3;

import android.view.View;
import android.widget.ImageView;

public class sanPham {
    private String tenSP;
    private String maSp;
    private double giaSP;
    private int soLuong;
    private ImageView hinhAnh;


    public sanPham(String tenSP, String maSp, double giaSP, int soLuong, ImageView hinhAnh) {
        this.tenSP = tenSP;
        this.maSp = maSp;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
    }
    public sanPham (){
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "tenSP='" + tenSP + '\'' +
                ", maSp='" + maSp + '\'' +
                ", giaSP=" + giaSP +
                ", soLuong=" + soLuong +
                ", hinhAnh=" + hinhAnh +
                '}';
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }

    public void setHinhAnh(ImageView hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getMaSp() {
        return maSp;
    }

    public double getGiaSP() {
        return giaSP;
    }

    public ImageView getHinhAnh() {
        return hinhAnh;
    }
}
