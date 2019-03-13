package vn.misa.nadat.loginlistusersmvp.ui.register;

public interface IRegisterPresenter {
    void onSuccess(String username, String password);

    void onDuplicate();

    void onConfig();
}
