package login.deprecated;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import login.StringEncrypter;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class UserData {
    public UserData() {
    }

    public static void saveUserData(User user) {
        encryptUser(user);
        File file = new File("src/main/java/login" + user.getEncryptedUserName() + ".dat");
        if (file.isFile() && JOptionPane.showConfirmDialog((Component)null, "The Username already exists! \nOverwrite?", "Save", 0) == 1) {
            saveUserData(user);
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(new User(user.getEncryptedUserName(), user.getEncryptedUserPassword(), true));
                oos.close();
                fos.close();
                JOptionPane.showMessageDialog((Component)null, "Successfully saved", "Save", 1);
            } catch (FileNotFoundException var4) {
                var4.printStackTrace();
            } catch (IOException var5) {
                throw new RuntimeException(var5);
            }

        }
    }

    public static User getUser(String userName, String userPassword) {
        User testUser = encryptUser(new User(userName, userPassword));
        File file = new File("src/main/java/login" + testUser.getEncryptedUserName() + ".dat");
        if (!file.isFile()) {
            JOptionPane.showMessageDialog((Component)null, "User doesn't exist!", "Load", 0);
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            User user = (User)ois.readObject();
            if (user.getEncryptedUserPassword().equals(testUser.getEncryptedUserPassword())) {
                return user;
            } else {
                JOptionPane.showMessageDialog((Component)null, "Wrong password!", "Load", 0);
                fis.close();
                ois.close();
                return null;
            }
        } catch (ClassNotFoundException | IOException var7) {
            throw new RuntimeException(var7);
        }
    }

    public static void saveUserData(String userName, String userPassword) {
        saveUserData(new User(userName, userPassword));
    }

    private static User encryptUser(User user) {
        String userName = user.getEncryptedUserName();
        String userPassword = user.getEncryptedUserPassword();
        if (!user.isEncrypted()) {
            user.setUserName(StringEncrypter.encryptString(userName));
            user.setUserPassword(StringEncrypter.encryptString(userPassword));
            user.setEncrypted(true);
        }

        return user;
    }
}
