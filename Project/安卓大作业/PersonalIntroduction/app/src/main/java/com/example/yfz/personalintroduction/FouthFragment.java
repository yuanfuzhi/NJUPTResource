package com.example.yfz.personalintroduction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FouthFragment extends Fragment {
    public FouthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fouth, container, false);
        ImageButton button=(ImageButton) view.findViewById(R.id.imageButton2);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Uri uri = Uri.parse("http://weibo.com/5754571264/profile?topnav=1&wvr=6");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        ImageButton button2=(ImageButton) view.findViewById(R.id.imageButton);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Uri uri = Uri.parse("https://user.qzone.qq.com/1552800131/main");
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent2);

            }
        });

        return view;

    }
}
