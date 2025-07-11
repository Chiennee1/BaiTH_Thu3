package com.example.baith_thu3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnMore;
    private List<sanPham> sanPhamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        btnMore = findViewById(R.id.btt_more);

        sanPhamList = SanPhamAdapter.initSanPham(this);

        // Thiết lập GridLayoutManager với 2 cột để tạo lưới 4x2 (8 phần bằng nhau)
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        SanPhamAdapter adapter = new SanPhamAdapter(this, sanPhamList, new SanPhamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "Bạn đã chọn: " + sanPhamList.get(position).getTenSP(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBuyClick(int position) {
                try {
                    sanPham sp = sanPhamList.get(position);
                    String maHoaDon = hoaDon.generateMaHDString();

                    // Lấy số lượng từ EditText một cách an toàn
                    int soLuong = 1;
                    try {
                        View itemView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
                        if (itemView != null) {
                            EditText edtSoLuong = itemView.findViewById(R.id.edt_soLuong);
                            if (edtSoLuong != null) {
                                String soLuongStr = edtSoLuong.getText().toString().trim();
                                if (!soLuongStr.isEmpty()) {
                                    soLuong = Integer.parseInt(soLuongStr);
                                }
                            }
                        }
                    } catch (Exception e) {
                        // Nếu có lỗi, mặc định số lượng là 1
                        soLuong = 1;
                        e.printStackTrace();
                    }

                    // Tính thành tiền
                    double thanhTien = sp.getGiaSP() * soLuong;

                    // Chuyển đến trang đặt hàng
                    Intent intent = new Intent(MainActivity.this, DatHangActivity.class);
                    intent.putExtra("maHD", maHoaDon);
                    intent.putExtra("maSP", sp.getMaSp());
                    intent.putExtra("soLuong", soLuong);
                    intent.putExtra("thanhTien", thanhTien);
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onAddToCartClick(int position) {
                sanPham sp = sanPhamList.get(position);
                Toast.makeText(MainActivity.this, "Đã thêm " + sp.getTenSP() + " vào giỏ hàng",
                        Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        btnMore.setOnClickListener(v -> {
            showMoreOptionsMenu(v);
        });
    }

    private void showMoreOptionsMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_more, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.menu_gio_hang) {
                Toast.makeText(this, "Đang mở giỏ hàng", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.menu_theo_doi_don_hang) {
                Toast.makeText(this, "Đang mở trang theo dõi đơn hàng", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.menu_thong_tin_ca_nhan) {
                Toast.makeText(this, "Đang mở trang thông tin cá nhân", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        popupMenu.show();
    }
}
