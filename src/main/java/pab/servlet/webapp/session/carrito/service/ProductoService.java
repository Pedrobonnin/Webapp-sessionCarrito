package pab.servlet.webapp.session.carrito.service;

import pab.servlet.webapp.session.carrito.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> listar();

    Optional<Producto> porId(Long id);
}
