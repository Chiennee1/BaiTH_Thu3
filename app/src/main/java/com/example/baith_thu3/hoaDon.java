package com.example.baith_thu3;

public class hoaDon {
    private int maHD;
    private String maSP;
    private int soluong;
    private double thanhTien;
    private String hinhThucTT;

    private static int lastMaHD = 1000; // Bắt đầu từ 1000

    public hoaDon(int maHD, String maSP, int soluong, double thanhTien, String hinhThucTT) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soluong = soluong;
        this.thanhTien = thanhTien;
        this.hinhThucTT = hinhThucTT;
    }

    // Constructor tự động tạo mã hóa đơn
    public hoaDon(String maSP, int soluong, double thanhTien, String hinhThucTT) {
        this.maHD = generateMaHD();
        this.maSP = maSP;
        this.soluong = soluong;
        this.thanhTien = thanhTien;
        this.hinhThucTT = hinhThucTT;
    }

    public hoaDon() {
    }

    public static int generateMaHD() {
        return ++lastMaHD;
    }

    public static String generateMaHDString() {
        return "HD" + generateMaHD();
    }
    public String getMaHDString() {
        return "HD" + maHD;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getHinhThucTT() {
        return hinhThucTT;
    }

    public void setHinhThucTT(String hinhThucTT) {
        this.hinhThucTT = hinhThucTT;
    }

    @Override
    public String toString() {
        return "Hóa Đơn{" +
                "maHD=" + maHD +
                ", maSP='" + maSP + '\'' +
                ", soluong=" + soluong +
                ", thanhTien=" + thanhTien +
                ", hinhThucTT='" + hinhThucTT + '\'' +
                '}';
    }
}
