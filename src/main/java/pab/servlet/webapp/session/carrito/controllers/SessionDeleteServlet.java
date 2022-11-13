package pab.servlet.webapp.session.carrito.controllers;

import pab.servlet.webapp.session.carrito.service.LoginService;
import pab.servlet.webapp.session.carrito.service.LoginServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class SessionDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImp();
        Optional<String> usernameSession = auth.getUsername(req);
        //Para cerrar la cookie
        if (usernameSession.isPresent()) {
            HttpSession session = req.getSession();    //Obtengo la sesión
            session.invalidate();   //BORRA TODO LO QUE TENGA EN SESIÓN DE USUARIO
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
