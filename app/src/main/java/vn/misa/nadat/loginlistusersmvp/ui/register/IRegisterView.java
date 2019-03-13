package vn.misa.nadat.loginlistusersmvp.ui.register;

public interface IRegisterView {
    void onRegisterSuccess(String username, String password);

    void onDuplicateUser();

    void onConfigPassword();
}
