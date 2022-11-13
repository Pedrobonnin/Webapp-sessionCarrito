package pab.servlet.webapp.session.carrito.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceImp implements LoginService {

    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        HttpSession session = req.getSession();     //Obtenemos la Session
        String username = (String) session.getAttribute("username");  //Tiene muchos atributos el Session, parecido al MAP, por eso el cast
        if (username != null) {
            return Optional.of(username);  //Para transformar un objeto a un optional del tipo manejado
        }
        return Optional.empty();
    }

}
