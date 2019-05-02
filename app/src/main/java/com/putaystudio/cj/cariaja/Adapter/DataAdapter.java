package com.putaystudio.cj.cariaja.Adapter;

import android.app.LauncherActivity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.putaystudio.cj.cariaja.Model.ModelSiswa;
import com.putaystudio.cj.cariaja.R;
import com.putaystudio.cj.cariaja.ResultActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.viewHolder>{
    private ArrayList<ModelSiswa> list_histori;
    public DataAdapter(ArrayList<ModelSiswa> list_histori){
        this.list_histori = list_histori;
    }

    @NonNull
    @Override
    public DataAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, final int position) {
        holder.tv_result_nama.setText(list_histori.get(position).getNama());
        holder.tvTanggal.setText("NIS : "+list_histori.get(position).getNis()+"  Tanggal : "+list_histori.get(position).getCreated_at());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                    Intent detail = new Intent(v.getContext(), ResultActivity.class);
                    detail.putExtra("NAMA",list_histori.get(position).getNama());
                    detail.putExtra("NIS",list_histori.get(position).getNis());
                    detail.putExtra("FOTO",list_histori.get(position).getFoto());
                    detail.putExtra("RAYON",list_histori.get(position).getRayon());
                    detail.putExtra("ROMBEL",list_histori.get(position).getRombel());
                    v.getContext().startActivity(detail);
            }
        });
    }





    @Override
    public int getItemCount() {
        return list_histori.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView tv_result_nama,tvTanggal;
        private CardView linearLayout;
        private CircleImageView fotoSiswa;
        public viewHolder(View view) {
            super(view);
            tv_result_nama = view.findViewById(R.id.tvResultNama);
            tvTanggal = view.findViewById(R.id.tvResultTanggal);
            linearLayout = view.findViewById(R.id.linearme);
            fotoSiswa = view.findViewById(R.id.fotoSiswa);
        }
    }
}
