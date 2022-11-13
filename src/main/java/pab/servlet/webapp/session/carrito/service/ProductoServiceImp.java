package pab.servlet.webapp.session.carrito.service;

import pab.servlet.webapp.session.carrito.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImp implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "NoteBook", "Computación", 17500),
                new Producto(2L, "Yeyian CPU", "Computación", 15000),
                new Producto(3L, "Mesa Escritorio", "Oficina", 10000));
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(p -> p.getId().equals(id)).findAny();
    }
}
