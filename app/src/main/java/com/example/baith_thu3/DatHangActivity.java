package com.example.baith_thu3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DatHangActivity extends AppCompatActivity {

    private TextView tvMaHD, tvTenSP, tvDonGia, tvSoLuong, tvThanhTien, tvTongTien;
    private RadioGroup rgThanhToan;
    private Button btnDatHang;
    private String maHD;
    private sanPham sanPhamDatHang;
    private int soLuong;
    private double thanhTien;
    private double phiVanChuyen = 30000; // Phí vận chuyển mặc định

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dat_hang);

        anhXaView();

        nhanDuLieuIntent();

        hienThiDuLieu();
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDatHang();
            }
        });
    }
    private void anhXaView() {
        try {
            tvMaHD = findViewById(R.id.tv_ma_hd);
            tvTenSP = findViewById(R.id.tv_ten_sp);
            tvDonGia = findViewById(R.id.tv_don_gia);
            tvSoLuong = findViewById(R.id.tv_so_luong);
            tvThanhTien = findViewById(R.id.tv_thanh_tien);
            tvTongTien = findViewById(R.id.tv_tong_tien);
            rgThanhToan = findViewById(R.id.rg_thanh_toan);
            btnDatHang = findViewById(R.id.btn_dat_hang);
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi khi ánh xạ view: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private void nhanDuLieuIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            // Kiểm tra xem có đối tượng hoaDon được truyền qua không
            if (intent.hasExtra("hoaDon")) {
                // Lấy đối tượng hoaDon trực tiếp từ Intent (nếu đã được truyền qua)
                hoaDon hd = (hoaDon) intent.getSerializableExtra("hoaDon");
                if (hd != null) {
                    maHD = hd.getMaHDString();

                    // Lấy mã sản phẩm từ hóa đơn
                    String maSP = hd.getMaSP();
                    soLuong = hd.getSoluong();
                    thanhTien = hd.getThanhTien();

                    // Tìm thông tin sản phẩm từ mã sản phẩm trong hóa đơn
                    List<sanPham> danhSachSanPham = SanPhamAdapter.initSanPham(this);
                    for (sanPham sp : danhSachSanPham) {
                        if (sp.getMaSp().equals(maSP)) {
                            sanPhamDatHang = sp;
                            break;
                        }
                    }
                    // Xác định hình thức thanh toán đã chọn
                    String hinhThucTT = hd.getHinhThucTT();
                    if (hinhThucTT != null) {
                        // Chọn RadioButton tương ứng với hình thức thanh toán
                        if (hinhThucTT.contains("COD")) {
                            rgThanhToan.check(R.id.rb_cod);
                        } else if (hinhThucTT.contains("khoản")) {
                            rgThanhToan.check(R.id.rb_banking);
                        } else if (hinhThucTT.contains("điện tử")) {
                            rgThanhToan.check(R.id.rb_momo);
                        }
                    }
                }
            } else {
                // Nếu không có đối tượng hoaDon, lấy các thông tin riêng lẻ như trước
                // Lấy mã hóa đơn từ Intent
                maHD = intent.getStringExtra("maHD");
                if (maHD == null) {
                    // Nếu không có mã hóa đơn, tạo mã mới
                    maHD = hoaDon.generateMaHDString();
                }

                // Lấy mã sản phẩm từ Intent
                String maSP = intent.getStringExtra("maSP");
                if (maSP != null) {
                    // Lấy thông tin sản phẩm từ danh sách sản phẩm
                    List<sanPham> danhSachSanPham = SanPhamAdapter.initSanPham(this);
                    for (sanPham sp : danhSachSanPham) {
                        if (sp.getMaSp().equals(maSP)) {
                            sanPhamDatHang = sp;
                            break;
                        }
                    }
                }

                // Lấy số lượng từ Intent
                soLuong = intent.getIntExtra("soLuong", 1);
            }
        }
        // Nếu không có thông tin sản phẩm, quay lại màn hình trước
        if (sanPhamDatHang == null) {
            Toast.makeText(this, "Không có thông tin sản phẩm", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Tính thành tiền nếu chưa có
            if (thanhTien == 0) {
                thanhTien = sanPhamDatHang.getGiaSP() * soLuong;
            }
        }
    }

    private void hienThiDuLieu() {
        // Hiển thị mã hóa đơn
        tvMaHD.setText(maHD);

        // Hiển thị thông tin sản phẩm
        if (sanPhamDatHang != null) {
            // Hiển thị tên sản phẩm
            tvTenSP.setText(sanPhamDatHang.getTenSP());

            // Định dạng và hiển thị đơn giá
            NumberFormat formatTien = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
            String donGiaStr = formatTien.format(sanPhamDatHang.getGiaSP()) + " VNĐ";
            tvDonGia.setText(donGiaStr);

            // Hiển thị số lượng
            tvSoLuong.setText(String.valueOf(soLuong));

            // Tính và hiển thị thành tiền
            if (thanhTien == 0) {
                thanhTien = sanPhamDatHang.getGiaSP() * soLuong;
            }
            String thanhTienStr = formatTien.format(thanhTien) + " VNĐ";
            tvThanhTien.setText(thanhTienStr);

            // Hiển thị phí vận chuyển
            String phiVanChuyenStr = formatTien.format(phiVanChuyen) + " VNĐ";
            TextView tvPhiVanChuyen = findViewById(R.id.tv_phi_van_chuyen);
            if (tvPhiVanChuyen != null) {
                tvPhiVanChuyen.setText(phiVanChuyenStr);
            }

            // Tính và hiển thị tổng tiền (thành tiền + phí vận chuyển)
            double tongTien = thanhTien + phiVanChuyen;
            String tongTienStr = formatTien.format(tongTien) + " VNĐ";
            tvTongTien.setText(tongTienStr);
        }
    }
    private void xuLyDatHang() {
        // Lấy phương thức thanh toán được chọn
        int selectedId = rgThanhToan.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        String hinhThucThanhToan = radioButton.getText().toString();

        // Tạo đối tượng hóa đơn
        hoaDon hd = new hoaDon(sanPhamDatHang.getMaSp(), soLuong, thanhTien, hinhThucThanhToan);

        // Hiển thị thông báo đặt hàng thành công
        Toast.makeText(this, "Đặt hàng thành công! Mã hóa đơn: " + hd.getMaHDString(), Toast.LENGTH_LONG).show();

        // Chuyển đến trang xác nhận đơn hàng
        Intent intent = new Intent(DatHangActivity.this, DonHangActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
