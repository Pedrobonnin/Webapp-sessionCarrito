<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="pab.servlet.webapp.session.carrito.models.*"%>
<%
Carro carro = (Carro) session.getAttribute("carro");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./style.css">
    <title>Carrito</title>
</head>
<body>
<!-- <p>Me ha llegado <%=session.getAttribute("carro") %></p> -->
<% if( carro == null || carro.getItems().isEmpty() ) { %>
    <h2>No hay productos en el carrito de compras</h2>
    <h2>¡ Debes iniciar sesión y agregar productos !</h2>
<% } else { %>
    <div class="container">
        <h1>Carrito</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Total</th>
            </tr>
            <% for( ItemCarro item: carro.getItems() ) { %>
            <tr>
                <td><%=item.getProducto().getId() %></td>
                <td><%=item.getProducto().getNombre() %></td>
                <td><%=item.getCantidad() %></td>
                <td><%=item.getProducto().getPrecio() %></td>
                <td><%=item.getImporte() %></td>
            </tr>
            <% } %>
            <tr>
                <td>Total</td>
                <td colspan="3"></td>
                <td><%=carro.getTotal() %></td>
            </tr>
        </table>
    </div>
<% } %>
    <div id="lista">
        <ul>
            <li><a href="<%=request.getContextPath() %>/productos.html">comprar</a></li>
            <li><a href="<%=request.getContextPath() %>/index.html">Inicio</a></li>
        </ul>
    </div>
</body>
</html>