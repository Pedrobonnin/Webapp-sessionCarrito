package pab.servlet.webapp.session.carrito.controllers;

import pab.servlet.webapp.session.carrito.service.LoginService;
import pab.servlet.webapp.session.carrito.service.LoginServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "123456";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService serviceAuth = new LoginServiceImp();
        Optional<String> usernameOptional = serviceAuth.getUsername(req);

        if (usernameOptional.isPresent()) {  // con isPresent verificamos si existe la sesion "si hay usuario presente inicia"
            resp.setContentType("text/html;charset=UTF-8");  // definimos el tipo de respuesta
            try (PrintWriter out = resp.getWriter()) {  //Impimimos la estructura html
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("     <meta charset='UTF-8'>");
                out.println("        <link rel=\"stylesheet\" href=\"./style.css\">");
                out.println("     <title>Session</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("        <h1> Hola " + usernameOptional.get() + " ya has iniciado sesión! </h1>");
                out.println("        <div id=\"lista\">");
                out.println("            <ul>");
                out.println("               <li><a href='" + req.getContextPath() + "/index.html'>volver</a></li>");
                out.println("               <li><a href='" + req.getContextPath() + "/productos.html'>Lista de Productos</a></li>");
                out.println("               <li><a href='" + req.getContextPath() + "/logout'>cerrar sesion</a></li>");
                out.println("            <ul>");
                ;
                out.println("        </div>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username"); //obtine el nombre
        String password = req.getParameter("password"); //obtine la clave

        List<String> errores = new ArrayList<>();
        if (username == null || username.isEmpty()) { //valida el ingleso de dato

            errores.add("El usuario no puede ser vacio");
        }

        if (password == null || password.isEmpty()) {

            errores.add("La contraseña no puede ser vacio");
        }

        if (errores.isEmpty()) {

            if (USERNAME.equals(username) && PASSWORD.equals(password)) { //comprueba que la clave y el usuario sean correctos
                HttpSession session = req.getSession(); //Obtine la sesion
                session.setAttribute("username", username); //establece atributo de la sesion
                resp.sendRedirect(req.getContextPath() + "/login.html");


            } else {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, no esta autorizado para ingresar a esta pagina!!");
            }

        } else {

            req.setAttribute("errores", errores);//atributos de request. Nos permiten pasar datos de un servlet a una JSP o de servlet a servlet

            //Redireccionamos a la JSP desde el servlet
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);//metodo para cargar la JSP-> forward

        }
    }
}
