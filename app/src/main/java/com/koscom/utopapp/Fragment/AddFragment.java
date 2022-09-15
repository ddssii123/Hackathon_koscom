package com.koscom.utopapp.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koscom.utopapp.Data.PreferenceManager;
import com.koscom.utopapp.Data.UserBean;
import com.koscom.utopapp.Data.UserDBHelper;
import com.koscom.utopapp.MainActivity;
import com.koscom.utopapp.R;

import java.util.ArrayList;

public class AddFragment extends Fragment {
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_add_fragment, container, false);

        return view;
    }


}