package com.example.iiuisecurityapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class NewActivity extends BottomSheetDialogFragment {
    TextView ok;
    private NewActivityListerner mListener;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_new,container,false);
        TextView ok=v.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserintent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.who.int/health-topics/coronavirus#tab=tab_1"));
                startActivity(browserintent);
            }
        });
        return v;

    }
    public  interface NewActivityListerner{
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener=(NewActivityListerner) context;
        }catch (ClassCastException e){
//            throw new ClassCastException(context.toString()+"must implement");
        }
    }
    }
