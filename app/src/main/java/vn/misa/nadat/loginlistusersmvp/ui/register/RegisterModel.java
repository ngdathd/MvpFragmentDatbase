package vn.misa.nadat.loginlistusersmvp.ui.register;

public class RegisterModel {
    private IRegisterPresenter mIRegisterPresenter;

    public RegisterModel(IRegisterPresenter iRegisterPresenter) {
        this.mIRegisterPresenter = iRegisterPresenter;
    }

    public void createNewUser(String username, String password, String confirm) {
        if (username == "1") {
            mIRegisterPresenter.onDuplicate();
        }
        if (password.equals(confirm)) {
            mIRegisterPresenter.onSuccess(username, password);
        } else {
            mIRegisterPresenter.onConfig();
        }
    }
}
