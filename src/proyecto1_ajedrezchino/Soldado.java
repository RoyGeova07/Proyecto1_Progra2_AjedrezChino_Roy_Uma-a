/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

/**
 *
 * @author royum
 */
public class Soldado extends Pieza {

    private Tablero tablero;

    public Soldado(Bando bando, Tablero tablero) {
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

        
        if (bando == Bando.ROJO) {
            if (filaOrigen >= 5) {
              
                if (filaDestino == filaOrigen - 1 && columDestino == columOrigen) {
                    return true;
                }
            } else {
               
                if ((filaDestino == filaOrigen - 1 && columDestino == columOrigen)
                        || // Avanzar
                        (filaDestino == filaOrigen && Math.abs(columDestino - columOrigen) == 1)) { // Moverse horizontalmente
                    return true;
                }
            }
        }

        if (bando == Bando.NEGRO) {
            if (filaOrigen <= 4) {
            
                if (filaDestino == filaOrigen + 1 && columDestino == columOrigen) {
                    return true;
                }
            } else {
              
                if ((filaDestino == filaOrigen + 1 && columDestino == columOrigen)
                        || // Avanzar
                        (filaDestino == filaOrigen && Math.abs(columDestino - columOrigen) == 1)) { // Moverse horizontalmente
                    return true;
                }
            }
        }
        

        return false;
    }

}
