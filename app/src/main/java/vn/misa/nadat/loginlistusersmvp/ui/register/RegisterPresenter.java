package vn.misa.nadat.loginlistusersmvp.ui.register;

public class RegisterPresenter implements IRegisterPresenter {
    private RegisterModel mRegisterModel;
    private IRegisterView mIRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.mIRegisterView = iRegisterView;
        mRegisterModel = new RegisterModel(this);
    }

    public void registerNewUser(String username, String password, String confirm) {
        mRegisterModel.createNewUser(username, password, confirm);
    }

    @Override
    public void onSuccess(String username, String password) {
        mIRegisterView.onRegisterSuccess(username, password);
    }

    @Override
    public void onDuplicate() {
        mIRegisterView.onDuplicateUser();
    }

    @Override
    public void onConfig() {
        mIRegisterView.onConfigPassword();
    }
}
