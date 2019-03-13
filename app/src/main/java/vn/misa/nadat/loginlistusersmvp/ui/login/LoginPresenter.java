package vn.misa.nadat.loginlistusersmvp.ui.login;

public class LoginPresenter implements ILoginPresenter {
    private LoginModel mLoginModel;
    private ILoginView mILoginView;

    public LoginPresenter(ILoginView iLoginView) {
        mLoginModel = new LoginModel(this);
        this.mILoginView = iLoginView;
    }

    public void validateUsernamePassword(String username, String password) {
        mLoginModel.checkUsernamePassword(username, password);
    }

    @Override
    public void onSuccess() {
        mILoginView.onLoginSuccess();
    }

    @Override
    public void onError() {
        mILoginView.onLoginError();
    }

    @Override
    public void onEmpty() {
        mILoginView.onEmptyUser();
    }
}
