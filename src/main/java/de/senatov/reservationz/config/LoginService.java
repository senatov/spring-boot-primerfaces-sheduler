package de.senatov.reservationz.config;



import org.springframework.stereotype.Service;



@Service
public class LoginService {

    public String validateUser(LoginConfig loginBean) {

        String userName = loginBean.getUserName();
        String password = loginBean.getPassword();
        if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("aaa")) {
            return "true";
        } else {
            return "false";
        }
    }

}
