package pab.servlet.webapp.session.carrito.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginService {
    //obtener sesion por username
    Optional<String> getUsername(HttpServletRequest req);
}
