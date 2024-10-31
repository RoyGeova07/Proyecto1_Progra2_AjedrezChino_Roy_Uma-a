/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

/**
 *
 * @author royum
 */
public class Torre extends Pieza {

    private Tablero tablero;

    public Torre(Bando bando, Tablero tablero) {
        super(bando);
        this.tablero = tablero;
    }

    @Override
    public boolean MovimientoPiezas(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {

      
        Pieza piezaDestino = tablero.obtenerPieza(filaDestino, columDestino);
        if (piezaDestino != null) {
    
            if (piezaDestino.getBando() == this.getBando()) {
                return false;

            }
        }


        if (filaOrigen == filaDestino || columOrigen == columDestino) {

            if (!caminoBloqueado(filaOrigen, columOrigen, filaDestino, columDestino)) {
                return true; 
            }
        }

        return false; 
    }

   
    private boolean caminoBloqueado(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {

        if (filaOrigen == filaDestino) {
            int minCol = Math.min(columOrigen, columDestino);
            int maxCol = Math.max(columOrigen, columDestino);
            for (int col = minCol + 1; col < maxCol; col++) {
                if (tablero.hayPieza(filaOrigen, col)) {
                    return true; 
                }
            }
        }

        if (columOrigen == columDestino) {
            int minFila = Math.min(filaOrigen, filaDestino);
            int maxFila = Math.max(filaOrigen, filaDestino);
            for (int fila = minFila + 1; fila < maxFila; fila++) {
                if (tablero.hayPieza(fila, columOrigen)) {
                    return true;
                }
            }
        }

        return false; 
    }

}
