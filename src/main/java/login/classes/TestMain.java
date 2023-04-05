package login.classes;
public class TestMain {
    public static void main(String[] args) {
        User user = new User("123", "123");
        UserData.saveUserData(user);
        //UserData.saveUserData("123", "123"); //Also possible

        String userName = "123";
        String userPassword = "123"; //Given through WEB-API

        User newUser = UserData.getUser(userName, userPassword);
    }
}