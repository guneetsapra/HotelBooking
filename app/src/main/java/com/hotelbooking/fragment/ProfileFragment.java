package com.hotelbooking.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hotelbooking.R;
import com.hotelbooking.Utils;
import com.hotelbooking.api.ApiService;
import com.hotelbooking.api.RetroClient;
import com.hotelbooking.model.MyProfilePojo;
import com.hotelbooking.model.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    EditText et_name, et_username, et_phone, et_password;
    ProgressDialog loading;
    Button btnUpdate;
    SharedPreferences sharedPreferences;
    View view;

    List<MyProfilePojo> myProfilePojos;
    ResponseData responseData;

    public static ProfileFragment profileFragment() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My Info");
        et_name = view.findViewById(R.id.et_name);
        et_username = view.findViewById(R.id.et_username);
        et_phone = view.findViewById(R.id.et_phone);
        et_password = view.findViewById(R.id.et_password);
        getProfile();
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        return view;
    }

    public void getProfile() {
        sharedPreferences = getActivity().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        String sessionname = sharedPreferences.getString("user_name", "def-val");
        loading = new ProgressDialog(getContext());
        loading.setMessage("Please wait Loading....");
        loading.show();
      //  Toast.makeText(getContext(), "" + sessionname, Toast.LENGTH_LONG).show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<MyProfilePojo>> call = service.getProfile(sessionname);
        call.enqueue(new Callback<List<MyProfilePojo>>() {
            @Override
            public void onResponse(Call<List<MyProfilePojo>> call, Response<List<MyProfilePojo>> response) {
                loading.dismiss();

              //  Toast.makeText(getContext(), "" + response.body().toString(), Toast.LENGTH_LONG).show();
                myProfilePojos = response.body();
                MyProfilePojo user1 = myProfilePojos.get(0);
                et_name.setText(user1.getName());
                et_username.setText(user1.getEmail());
                et_phone.setText(user1.getPhone());
                et_password.setText(user1.getPass());

            }

            @Override
            public void onFailure(Call<List<MyProfilePojo>> call, Throwable error) {
                loading.dismiss();
                Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateProfile() {
        String name = et_name.getText().toString();
        String email = et_username.getText().toString();
        String phone = et_phone.getText().toString();
        String password = et_password.getText().toString();

        loading = new ProgressDialog(getContext());
        loading.setMessage("Loading....");
        loading.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.update_profile(name, email, phone, password);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                loading.dismiss();
                responseData = response.body();

                if (response.body().status.equals("true")) {
                    Toast.makeText(getContext(), response.body().message, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}