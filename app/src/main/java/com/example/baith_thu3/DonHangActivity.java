package com.example.baith_thu3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DonHangActivity extends AppCompatActivity {
    EditText edtTenNguoiNhan, edtSoDienThoai, edtDiaChi;
    RadioGroup hinhThucThanhToan;
    Button btnThanhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_don_hang);
        findView();
        btnThanhToan.setOnClickListener(v -> {
            XuLyThanhToan();
        });

    }
    private void XuLyThanhToan() {
        String tenNguoiNhan = edtTenNguoiNhan.getText().toString();
        String soDienThoai = edtSoDienThoai.getText().toString();
        String diaChi = edtDiaChi.getText().toString();
        int checkedRadioButtonId = hinhThucThanhToan.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(checkedRadioButtonId);
        String hinhThuc = radioButton.getText().toString();
        String maKh = generateMaKH();
        khachHang kh = new khachHang(tenNguoiNhan, maKh, diaChi, soDienThoai);
        if (tenNguoiNhan.isEmpty() || soDienThoai.isEmpty() || diaChi.isEmpty()) {
            // Hiển thị thông báo lỗi nếu có trường nào đó trống
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Thanh toán thành công cho khách hàng: " + kh.getTenKhachHang(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DonHangActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private String generateMaKH() {
        int randomNumber = (int) (Math.random() * 10000);
        long currentTimeMillis = System.currentTimeMillis();
        return "KH" + randomNumber + currentTimeMillis;
    }

    public void findView() {
        edtTenNguoiNhan = findViewById(R.id.edt_nguoi_nhan);
        edtSoDienThoai = findViewById(R.id.edt_so_dien_thoai);
        edtDiaChi = findViewById(R.id.edt_dia_chi);
        hinhThucThanhToan = findViewById(R.id.rg_thanh_toan);
        btnThanhToan = findViewById(R.id.btn_thanh_toan);
    }
}
