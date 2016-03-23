package com.jt.funny.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jt.funny.foreground.ActivityStackManager;

/**
 * Created by jiangtao on 16/3/23.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class BlankFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(container.getContext());
        textView.setText("ok");
        textView.setTextSize(30);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("JT", ActivityStackManager.getInstance().dump());
            }
        });
        return textView;
    }
}
