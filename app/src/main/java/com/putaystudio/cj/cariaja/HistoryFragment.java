package com.putaystudio.cj.cariaja;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.putaystudio.cj.cariaja.Adapter.DataAdapter;
import com.putaystudio.cj.cariaja.Model.JSONResponse;
import com.putaystudio.cj.cariaja.Model.ModelSiswa;
import com.putaystudio.cj.cariaja.RetroServer.ApiSiswa;
import com.putaystudio.cj.cariaja.RetroServer.ApiUser;
import com.putaystudio.cj.cariaja.RetroServer.RetroServer;
import com.putaystudio.cj.cariaja.SessionManager.SessionManager;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<ModelSiswa> data;
    private DataAdapter dataAdapter;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.myRecycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(view.getContext()));
        SessionManager sessionManager = new SessionManager(view.getContext());
        ApiUser apiUser = RetroServer.getClient().create(ApiUser.class);
        Call<JSONResponse> call = apiUser.getAllData(sessionManager.getKeyId());
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getList_histori()));
                dataAdapter = new DataAdapter(data);
                recyclerView.setAdapter(dataAdapter);
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("ERROR",t.getMessage());
            }
        });

        return view;
    }

    public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
