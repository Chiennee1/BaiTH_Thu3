package com.example.baith_thu3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    private List<sanPham> sanPhamList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onBuyClick(int position);
        void onAddToCartClick(int position);
    }

    public SanPhamAdapter(Context context, List<sanPham> sanPhamList, OnItemClickListener listener) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_section, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sanPham sp = sanPhamList.get(position);

        // Hiển thị dữ liệu sản phẩm trong EditText
        holder.edtTenSP.setText(sp.getTenSP());
        holder.edtGiaSP.setText(String.valueOf(sp.getGiaSP()));
        holder.edtSoLuong.setText(String.valueOf(sp.getSoLuong()));

        // Hiển thị hình ảnh nếu có
        if (sp.getHinhAnh() != null) {
            holder.ivIcon.setImageDrawable(sp.getHinhAnh().getDrawable());
        } else {
            // Hiển thị hình ảnh mặc định nếu không có
            holder.ivIcon.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        // Xử lý sự kiện cho nút Mua hàng
        holder.btnMuaHang.setOnClickListener(v -> {
            if (listener != null) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onBuyClick(adapterPosition);
                }
            }
        });

        // Xử lý sự kiện cho nút Thêm vào giỏ
        holder.btnAddToCart.setOnClickListener(v -> {
            if (listener != null) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onAddToCartClick(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public static List<sanPham> initSanPham(Context context) {
        List<sanPham> sanPhamList = new ArrayList<>();

        ImageView img1 = new ImageView(context);
        img1.setImageResource(android.R.drawable.ic_menu_call);

        ImageView img2 = new ImageView(context);
        img2.setImageResource(android.R.drawable.ic_menu_edit);

        ImageView img3 = new ImageView(context);
        img3.setImageResource(android.R.drawable.ic_menu_view);

        ImageView img4 = new ImageView(context);
        img4.setImageResource(android.R.drawable.ic_menu_camera);

        ImageView img5 = new ImageView(context);
        img5.setImageResource(android.R.drawable.ic_menu_compass);

        ImageView img6 = new ImageView(context);
        img6.setImageResource(android.R.drawable.ic_menu_directions);

        ImageView img7 = new ImageView(context);
        img7.setImageResource(android.R.drawable.ic_menu_manage);

        ImageView img8 = new ImageView(context);
        img8.setImageResource(android.R.drawable.ic_menu_gallery);

        // Thêm các sản phẩm với thông tin đầy đủ
        sanPhamList.add(new sanPham("iPhone 13", "DT001", 20000000, 10, img1));
        sanPhamList.add(new sanPham("Dell XPS", "LT001", 30000000, 5, img2));
        sanPhamList.add(new sanPham("iPad Pro", "TB001", 18000000, 8, img3));
        sanPhamList.add(new sanPham("Cáp Type-C", "PK001", 200000, 50, img4));
        sanPhamList.add(new sanPham("Apple Watch", "DH001", 10000000, 15, img5));
        sanPhamList.add(new sanPham("AirPods Pro", "TN001", 5000000, 20, img6));
        sanPhamList.add(new sanPham("Sạc 10000mAh", "SDP001", 500000, 30, img7));
        sanPhamList.add(new sanPham("Ốp iPhone 13", "OL001", 200000, 100, img8));

        return sanPhamList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        EditText edtTenSP, edtGiaSP, edtSoLuong;
        Button btnMuaHang, btnAddToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            edtTenSP = itemView.findViewById(R.id.edt_tenSP);
            edtGiaSP = itemView.findViewById(R.id.edt_giaSP);
            edtSoLuong = itemView.findViewById(R.id.edt_soLuong);
            btnMuaHang = itemView.findViewById(R.id.btt_MuaHang);
            btnAddToCart = itemView.findViewById(R.id.btn_addToCart);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}
