package vn.misa.nadat.loginlistusersmvp.ui.register;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import vn.misa.nadat.loginlistusersmvp.R;
import vn.misa.nadat.loginlistusersmvp.utils.Utils;

public class RegisterFragment extends Fragment implements View.OnClickListener, IRegisterView {
    private static final String TAG = RegisterFragment.class.getSimpleName();

    private OnRegisterFragmentListener mListener;
    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private EditText mEdtConfirm;

    private RegisterPresenter mRegisterPresenter;

    public RegisterFragment() {
        mRegisterPresenter = new RegisterPresenter(this);
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mEdtUsername = view.findViewById(R.id.edt_username);
        mEdtPassword = view.findViewById(R.id.edt_password);
        mEdtConfirm = view.findViewById(R.id.edt_confirm);

        AppCompatButton btnRegister = view.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context instanceof OnRegisterFragmentListener) {
                mListener = (OnRegisterFragmentListener) context;
            } else {
                Log.i(TAG, "Must implement OnLoginFragmentListener");
            }
        } catch (RuntimeException e) {
            Log.i(TAG, e.getMessage() + " must implement OnLoginFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register: {
                if (Utils.isNotEmpty(mEdtUsername) && Utils.isNotEmpty(mEdtPassword) && Utils.isNotEmpty(mEdtConfirm)) {
                    mRegisterPresenter.registerNewUser(
                            Objects.requireNonNull(mEdtUsername.getText()).toString(),
                            Objects.requireNonNull(mEdtPassword.getText()).toString(),
                            Objects.requireNonNull(mEdtConfirm.getText()).toString());
                }

                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onRegisterSuccess(String username, String password) {
        if (mListener != null) {
            mListener.moveLoginFragmentFromRegister(username, password);
        }
    }

    @Override
    public void onDuplicateUser() {
        Toast.makeText(getContext(), "Username already exist!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigPassword() {
        Toast.makeText(getContext(), "Confirm invalidate!", Toast.LENGTH_SHORT).show();
    }

    public interface OnRegisterFragmentListener {
        void moveLoginFragmentFromRegister(String username, String password);
    }
}
