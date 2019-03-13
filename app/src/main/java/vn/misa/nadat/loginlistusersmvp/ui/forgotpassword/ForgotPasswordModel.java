package vn.misa.nadat.loginlistusersmvp.ui.forgotpassword;

public class ForgotPasswordModel {
    private IForgotPasswordPresenter mIForgotPasswordPresenter;

    public ForgotPasswordModel(IForgotPasswordPresenter iForgotPasswordPresenter) {
        this.mIForgotPasswordPresenter = iForgotPasswordPresenter;
    }

    public void updatePasswordUser(String username, String password, String confirm) {
        if (password.equals(confirm)) {
            mIForgotPasswordPresenter.onSuccess(username, password);
        } else {
            mIForgotPasswordPresenter.onConfig();
        }
    }
}
