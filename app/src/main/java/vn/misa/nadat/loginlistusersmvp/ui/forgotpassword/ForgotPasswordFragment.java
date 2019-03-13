package vn.misa.nadat.loginlistusersmvp.ui.forgotpassword;

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

public class ForgotPasswordFragment extends Fragment implements View.OnClickListener, IForgotPasswordView {
    private static final String TAG = ForgotPasswordFragment.class.getSimpleName();

    private OnForgotPasswordFragmentListener mListener;
    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private EditText mEdtConfirm;

    private ForgotPasswordPresenter mForgotPasswordPresenter;

    public ForgotPasswordFragment() {
        mForgotPasswordPresenter = new ForgotPasswordPresenter(this);
    }

    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mEdtUsername = view.findViewById(R.id.edt_username);
        mEdtPassword = view.findViewById(R.id.edt_password);
        mEdtConfirm = view.findViewById(R.id.edt_confirm);

        AppCompatButton btnChangePassword = view.findViewById(R.id.btn_change_password);
        btnChangePassword.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context instanceof OnForgotPasswordFragmentListener) {
                mListener = (OnForgotPasswordFragmentListener) context;
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
            case R.id.btn_change_password: {
                if (Utils.isNotEmpty(mEdtUsername) && Utils.isNotEmpty(mEdtPassword) && Utils.isNotEmpty(mEdtConfirm)) {
                    mForgotPasswordPresenter.changePasswordUser(
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
    public void onChangeSuccess(String username, String password) {
        if (mListener != null) {
            mListener.moveLoginFragmentFromForgot(username, password);
        }
    }

    @Override
    public void onConfigPassword() {
        Toast.makeText(getContext(), "Confirm invalidate!", Toast.LENGTH_SHORT).show();
    }

    public interface OnForgotPasswordFragmentListener {
        void moveLoginFragmentFromForgot(String username, String password);
    }
}
