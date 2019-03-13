package vn.misa.nadat.loginlistusersmvp.utils;

import android.widget.EditText;

public class Utils {
    public Utils() {
        // This utility class is not publicly instantiable
    }

    public static boolean isNotEmpty(EditText editText) {
        if (0 < editText.getText().toString().trim().length()) {
            return true;
        } else {
            editText.requestFocus();
            editText.setError("Please fill in the information!");
            return false;
        }
    }
}
