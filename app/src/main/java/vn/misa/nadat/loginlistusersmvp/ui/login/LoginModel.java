package vn.misa.nadat.loginlistusersmvp.ui.login;

public class LoginModel {
    private ILoginPresenter mILoginPresenter;

    public LoginModel(ILoginPresenter iLoginPresenter) {
        this.mILoginPresenter = iLoginPresenter;
    }

    public void checkUsernamePassword(String username, String password) {
        if (username.equals(password)) {
            mILoginPresenter.onSuccess();
        } else {
            mILoginPresenter.onError();
        }
        if (!username.equals(password)) {
            mILoginPresenter.onEmpty();
        }
    }
}
