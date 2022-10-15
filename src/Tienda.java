public class Tienda {
  Contenedor contenedor_frijol = new Contenedor("Frijol", 10);
  Contenedor contenedor_arroz = new Contenedor("Arroz", 10);
  Contenedor contenedor_maiz = new Contenedor("Maiz", 10);

  boolean contenedores_llenos = false;

  public synchronized void rellenarContenedor(String producto, int cantidad) {
    if (producto.equals("Frijol")) {
      contenedor_frijol.rellenar(cantidad);
    } else if (producto.equals("Arroz")) {
      contenedor_arroz.rellenar(cantidad);
    } else if (producto.equals("Maiz")) {
      contenedor_maiz.rellenar(cantidad);
    }

    if (contenedor_frijol.lleno && contenedor_arroz.lleno && contenedor_maiz.lleno) {
      contenedores_llenos = true;
      notifyAll();
    }
  }

  public synchronized boolean comprarProducto(String[] producto, int[] cantidad){
    int i;
    int PRODUCTO_LENGTH = producto.length;
    boolean lista_de_compra_completa = true;
    for (i = 0; i < PRODUCTO_LENGTH; i++) {
      if(producto[i] == "Frijol"){
        if(!contenedor_frijol.retirarProductoDelContenedor(cantidad[i])){
          lista_de_compra_completa = false;
          contenedores_llenos = false;
        }
      }
      if(producto[i] == "Arroz"){
        if(!contenedor_arroz.retirarProductoDelContenedor(cantidad[i])){
          lista_de_compra_completa = false;
          contenedores_llenos = false;
        }
      }
      if(producto[i] == "Maiz"){
        if(!contenedor_maiz.retirarProductoDelContenedor(cantidad[i])){
          lista_de_compra_completa = false;
          contenedores_llenos = false;
        }
      }
    }
    return lista_de_compra_completa;
  }

  public boolean getContenedoresLlenos() {
    return contenedores_llenos;
  }

  public boolean getContenedorLleno(String producto) {
    if (producto.equals("Frijol")) {
      return contenedor_frijol.lleno;
    } else if (producto.equals("Arroz")) {
      return contenedor_arroz.lleno;
    } else if (producto.equals("Maiz")) {
      return contenedor_maiz.lleno;
    }
    return false;
  }
}
