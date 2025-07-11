package com.example.baith_thu3;

public class khachHang {
    private String tenKhachHang;
    private String maKH;
    private String diaChi;
    private String soDienThoai;

    public khachHang(String tenKhachHang, String maKH, String diaChi, String soDienThoai) {
        this.tenKhachHang = tenKhachHang;
        this.maKH = maKH;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }
    public khachHang(){}

    @Override
    public String toString() {
        return "khachHang{" +
                "tenKhachHang='" + tenKhachHang + '\'' +
                ", maKH='" + maKH + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                '}';
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
