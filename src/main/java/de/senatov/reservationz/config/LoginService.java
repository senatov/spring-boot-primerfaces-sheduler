package de.senatov.reservationz.config;



import org.springframework.stereotype.Service;



@Service
public class LoginService {

    public String validateUser(LoginConfig loginBean) {

        String userName = loginBean.getUserName();
        String password = loginBean.getPassword();
        return isLoggedAsAdmin(userName, password);
    }



    /**
     * is user logged as Admin
     *
     * @param userName
     * @param password
     * @return
     */
    private String isLoggedAsAdmin(String userName, String password) {

        Boolean isAdmin = userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("aaa");
        return isAdmin.toString().toLowerCase();
    }

}
