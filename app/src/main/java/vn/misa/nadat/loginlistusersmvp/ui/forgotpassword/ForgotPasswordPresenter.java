package vn.misa.nadat.loginlistusersmvp.ui.forgotpassword;

public class ForgotPasswordPresenter implements IForgotPasswordPresenter {
    private ForgotPasswordModel forgotPasswordModel;
    private IForgotPasswordView mIForgotPasswordView;

    public ForgotPasswordPresenter(IForgotPasswordView iForgotPasswordView) {
        this.mIForgotPasswordView = iForgotPasswordView;
        forgotPasswordModel = new ForgotPasswordModel(this);
    }

    public void changePasswordUser(String username, String password, String confirm) {
        forgotPasswordModel.updatePasswordUser(username, password, confirm);
    }

    @Override
    public void onSuccess(String username, String password) {
        mIForgotPasswordView.onChangeSuccess(username, password);
    }

    @Override
    public void onConfig() {
        mIForgotPasswordView.onConfigPassword();
    }
}
