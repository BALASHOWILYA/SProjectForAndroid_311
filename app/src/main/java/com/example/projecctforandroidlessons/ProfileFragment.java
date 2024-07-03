package com.example.projecctforandroidlessons;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    private TextView userNameTextView;


    private void init(View view ){
        view.findViewById(R.id.userNameTextView);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUserName();
    }

    private void updateUserName() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String userName = sharedPreferences.getString("edit_pref", ""); // "edit_pref" is the key for EditTextPreference
        if (userNameTextView != null) {
            userNameTextView.setText(userName);
        }
    }
}
