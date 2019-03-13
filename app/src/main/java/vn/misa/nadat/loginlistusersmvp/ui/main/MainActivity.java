package vn.misa.nadat.loginlistusersmvp.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import vn.misa.nadat.loginlistusersmvp.R;
import vn.misa.nadat.loginlistusersmvp.ui.forgotpassword.ForgotPasswordFragment;
import vn.misa.nadat.loginlistusersmvp.ui.login.LoginFragment;
import vn.misa.nadat.loginlistusersmvp.ui.register.RegisterFragment;

public class MainActivity extends AppCompatActivity
        implements LoginFragment.OnLoginFragmentListener,
        RegisterFragment.OnRegisterFragmentListener,
        ForgotPasswordFragment.OnForgotPasswordFragmentListener {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private MainFragment mMainFragment = MainFragment.newInstance();
    private LoginFragment mLoginFragment = LoginFragment.newInstance();
    private RegisterFragment mRegisterFragment = RegisterFragment.newInstance();
    private ForgotPasswordFragment mForgotPasswordFragment = ForgotPasswordFragment.newInstance();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.add(R.id.layout_main, mLoginFragment);
        mFragmentTransaction.commit();
    }

    @Override
    public void moveMainFragment() {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.layout_main, mMainFragment, MainFragment.class.getSimpleName());
        mFragmentTransaction.addToBackStack(MainFragment.class.getSimpleName());
        mFragmentTransaction.commit();
    }

    @Override
    public void moveRegisterFragment() {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.layout_main, mRegisterFragment, RegisterFragment.class.getSimpleName());
        mFragmentTransaction.addToBackStack(RegisterFragment.class.getSimpleName());
        mFragmentTransaction.commit();
    }

    @Override
    public void moveForgotPasswordFragment() {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.layout_main, mForgotPasswordFragment, ForgotPasswordFragment.class.getSimpleName());
        mFragmentTransaction.addToBackStack(ForgotPasswordFragment.class.getSimpleName());
        mFragmentTransaction.commit();
    }

    @Override
    public void moveLoginFragmentFromRegister(String username, String password) {
        moveToLoginFragment(username, password);
    }

    @Override
    public void moveLoginFragmentFromForgot(String username, String password) {
        moveToLoginFragment(username, password);
    }

    private void moveToLoginFragment(String username, String password) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.layout_main, LoginFragment.newInstance(username, password), LoginFragment.class.getSimpleName());
        mFragmentTransaction.addToBackStack(LoginFragment.class.getSimpleName());
        mFragmentTransaction.commit();
    }
}
