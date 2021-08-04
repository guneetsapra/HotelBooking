package com.hotelbooking.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.hotelbooking.AdminMessageActivity;
import com.hotelbooking.R;
import com.hotelbooking.Utils;
import com.hotelbooking.model.Chat;

import java.util.List;

public class AdminChatAdapter extends BaseAdapter {
    List<Chat> ar;
    Context cnt;
    SharedPreferences sharedPreferences;
    String session;

    public AdminChatAdapter(List<Chat> ar, Context cnt) {
        this.ar = ar;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return ar.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup) {
        LayoutInflater obj1 = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2 = obj1.inflate(R.layout.child_adminchatadapter, null);

        sharedPreferences = cnt.getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("uname", "def-val");

        TextView tv_pid = (TextView) obj2.findViewById(R.id.tv_pid);
        tv_pid.setText("Hotel Id #:" + ar.get(pos).getHid());

        TextView tv_from = (TextView) obj2.findViewById(R.id.tv_from);
        tv_from.setText("Me :" + ar.get(pos).getEto());



        TextView tv_to = (TextView) obj2.findViewById(R.id.tv_to);
        tv_to.setText("To :" + ar.get(pos).getFrm());


        CardView my_inbox=(CardView)obj2.findViewById(R.id.my_inbox);

        my_inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, AdminMessageActivity.class);
                intent.putExtra("hid",ar.get(pos).getHid());
                intent.putExtra("from",ar.get(pos).getFrm());
                intent.putExtra("to",ar.get(pos).getEto());
                cnt.startActivity(intent);

            }
        });

//        Button btn_reply=(Button)obj2.findViewById(R.id.btn_reply);
//        btn_reply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(cnt, ReplyActivity.class);
//                intent.putExtra("bid",ar.get(pos).getBid());
//                intent.putExtra("uname",session);
//                cnt.startActivity(intent);
//            }
//        });


        return obj2;
    }
}