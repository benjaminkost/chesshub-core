package UserTeamTests;

import BusinessObjects.User;
import DAO.UserDao;

public class UserTest {

    public static void main(String[] args) {
        for (User u : UserDao.getInstance().getAllUser()){
            System.out.println(u.getEmail());
        }
    }
}
