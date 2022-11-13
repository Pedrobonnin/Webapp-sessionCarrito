package pab.servlet.webapp.session.carrito.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    private List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
    }

    //La línea debe ser única, por ello debo ver si ya fue añadido el item
    public void addItemCarro(ItemCarro itemCarro) {
        //Debo implementar el "equals" para el objeto, me interesa comparar por "id" y "nombre"
        if (items.contains(itemCarro)) {
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))
                    .findAny();
            if (optionalItemCarro.isPresent()) { //Modifico la cantidad del item que ya está
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad() + 1);
            }
        } else {
            this.items.add(itemCarro);
        }
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    //Calculo precio total de todos los artículos
    public int getTotal() {
        return items.stream()
                .mapToInt(i -> i.getImporte()).sum();
    }
}
