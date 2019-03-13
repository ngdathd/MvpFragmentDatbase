package vn.misa.nadat.loginlistusersmvp.ui.login;

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

public class LoginFragment extends Fragment implements View.OnClickListener, ILoginView {
    private static final String TAG = LoginFragment.class.getSimpleName();
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    private String mUsername;
    private String mPassword;

    private OnLoginFragmentListener mListener;
    private EditText mEdtUsername;
    private EditText mEdtPassword;

    private LoginPresenter mLoginPresenter;

    public LoginFragment() {
        mLoginPresenter = new LoginPresenter(this);
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public static LoginFragment newInstance(String username, String password) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, username);
        args.putString(PASSWORD, password);
        loginFragment.setArguments(args);
        return loginFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUsername = getArguments().getString(USERNAME);
            mPassword = getArguments().getString(PASSWORD);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppCompatButton btnLogin = view.findViewById(R.id.btn_login);
        AppCompatButton btnRegister = view.findViewById(R.id.btn_register);
        AppCompatButton btnForgotPassword = view.findViewById(R.id.btn_change_password);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnForgotPassword.setOnClickListener(this);

        mEdtUsername = view.findViewById(R.id.edt_username);
        mEdtPassword = view.findViewById(R.id.edt_password);

        if (getArguments() != null) {
            mEdtUsername.setText(mUsername);
            mEdtPassword.setText(mPassword);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context instanceof OnLoginFragmentListener) {
                mListener = (OnLoginFragmentListener) context;
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
            case R.id.btn_login: {
                if (Utils.isNotEmpty(mEdtUsername) && Utils.isNotEmpty(mEdtPassword)) {
                    mLoginPresenter.validateUsernamePassword(
                            Objects.requireNonNull(mEdtUsername.getText()).toString(),
                            Objects.requireNonNull(mEdtPassword.getText()).toString());
                }
                break;
            }
            case R.id.btn_register: {
                if (mListener != null) {
                    mListener.moveRegisterFragment();
                }
                break;
            }
            case R.id.btn_change_password: {
                if (mListener != null) {
                    mListener.moveForgotPasswordFragment();
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onLoginSuccess() {
        if (mListener != null) {
            mListener.moveMainFragment();
        }
    }

    @Override
    public void onLoginError() {
        Toast.makeText(getContext(), "Username or password invalidate!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyUser() {
        Toast.makeText(getContext(), "Database empty!", Toast.LENGTH_SHORT).show();
    }

    public interface OnLoginFragmentListener {
        void moveMainFragment();

        void moveRegisterFragment();

        void moveForgotPasswordFragment();
    }
}
