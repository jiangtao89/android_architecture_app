package com.jt.funny.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jt.funny.navigator.Route;
import com.jt.funny.navigator.Routers;
import com.jt.funny.navigator.router.DefaultPageRouter;

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
//                Routers.Builder builder = new Routers.Builder();
//                Routers routers
//                        = builder.context(getActivity())
//                        .registerRouter("http://www.baidu.com/page/main?tab=1", new DefaultPageRouter())
//                        .debug(true)
//                        .build();
//                Routers.setSingletonInstance(routers);

                Routers.getInstances().setContext(getActivity());
                Routers.getInstances().registerRouter("funny", new DefaultPageRouter());
                Routers.getInstances().registerRoute("funny://www.baidu.com/page/main?tab=1", BlankActivity.class);

                new Route.Builder()
                        .withUrl("funny://www.baidu.com/page/main")
                        .build()
                        .open();

            }
        });
        return textView;
    }
}
