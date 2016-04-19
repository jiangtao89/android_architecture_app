package com.jt.funny.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jt.funny.router.*;

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
                Routers.getInstances().registerRouter("http", new DefaultWebRouter());
                Routers.getInstances().registerRoute("funny://www.baidu.com/page/main?tab=1", BlankActivity.class);
                Routers.getInstances().registerRoute("http://www.baidu.com", new IRouteHandler() {
                    @Override
                    public boolean open(Route route) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(route.getUri());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | route.getFlags());
                        getContext().startActivity(intent);
                        return false;
                    }
                });

                new Route.Builder()
                        .withUrl("http://www.baidu.com")
                        .build()
                        .open();

            }
        });
        return textView;
    }
}
