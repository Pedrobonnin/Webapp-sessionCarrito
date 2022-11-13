package pab.servlet.webapp.session.carrito.controllers;

import pab.servlet.webapp.session.carrito.models.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pab.servlet.webapp.session.carrito.service.LoginService;
import pab.servlet.webapp.session.carrito.service.LoginServiceImp;
import pab.servlet.webapp.session.carrito.service.ProductoService;
import pab.servlet.webapp.session.carrito.service.ProductoServiceImp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImp();
        List<Producto> products = service.listar();

        LoginService serviceLoginSession = new LoginServiceImp();
        Optional<String> usernameOptional = serviceLoginSession.getUsername(req);


        resp.setContentType("text/html;charset=UTF-8");//definimos el tipo de respuesta

        try (PrintWriter out = resp.getWriter()) { //Imprimimos la estructura HTML ---mostrando los "productos" en una tabla----

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("     <meta charset='UTF-8'>");
            out.println("        <link rel=\"stylesheet\" href=\"./style.css\">");
            out.println("   </head>");
            out.println("   <body>");

            if (usernameOptional.isPresent()) {
                out.println("        <div class=\"container\">");
                out.println("        <h1>Listado de productos</h1>");
                out.println("       <table>");
                out.println("           <thead>");
                out.println("               <tr>");
                out.println("                   <th> ID </th>");
                out.println("                   <th> Nombre </th>");
                out.println("                   <th> Tipo </th>");
                out.println("                   <th> Precio </th>");
                out.println("                   <th> Carrito </th>");
                out.println("                </tr>");
                out.println("           </thead>");
                products.forEach(p -> {
                    out.println("         <tr>");
                    out.println("           <td>" + p.getId() + "</td>");
                    out.println("           <td>" + p.getNombre() + "</td>");
                    out.println("           <td>" + p.getTipo() + "</td>");
                    out.println("           <td>" + p.getPrecio() + "</td>");
                    out.println("           <td><a href=\"" + req.getContextPath() + "/agregar-carro?id=" + p.getId() + "\" >" +
                            "<button class=\"btn\">Agregar</button></a></td>");
                    out.println("         </tr>");
                });
                out.println("        </table>");
                out.println("        </div>");
                out.println("        <br>");
                out.println("        </div>");
                out.println("        <div id=\"lista\">");
                out.println("           <ul> ");
                out.println("               <li><a href='" + req.getContextPath() + "/index.html'>Volver</a></li>");
                out.println("                <li><a href='" + req.getContextPath() + "/ver-carro'>Ver Carrito </a></li></li>");
                out.println("               <li><a href='" + req.getContextPath() + "/logout'>Cerrar sesion</a></li>");
                out.println("           </ul> ");

            } else { // si no inicio sesion
                out.println("        <div id=\"lista\">");
                out.println("        <h1>Inicie sesion para ver los productos </h1>");
                out.println("        <ul> ");
                out.println("           <li><a  href=\"/webapp-session\">volver</a></li>");
                out.println("           <li><a  href=\"/webapp-session/login\">Login</a></li>");
                out.println("        </ul> ");
            }
            out.println("   </body>");
            out.println("</html>");
        }
    }
}
