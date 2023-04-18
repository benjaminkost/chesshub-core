package login.deprecated;
import java.io.Serializable;

public class User implements Serializable {

    private String userName;
    private String userPassword;
    private Object data;
    private boolean encrypted;

    public User(String userName, String userPassword) {
        this(userName, userPassword, false);
    }

    public User(String userName, String userPassword, boolean encrypted) {
        this.encrypted = encrypted;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getEncryptedUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }
}
