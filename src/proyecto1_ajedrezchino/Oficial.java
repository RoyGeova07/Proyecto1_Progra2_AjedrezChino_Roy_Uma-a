/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

/**
 *
 * @author royum
 */
public class Oficial extends Pieza {

    private Tablero tablero;

    public Oficial(Bando bando, Tablero tablero) {
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
        
        if (Math.abs(filaDestino - filaOrigen) == 1 && Math.abs(columDestino - columOrigen) == 1) {

           
            if (bando == Bando.ROJO) {
           
                if (filaDestino >= 7 && filaDestino <= 9 && columDestino >= 3 && columDestino <= 5) {
                    return true; 
                }
            } else if (bando == Bando.NEGRO) {
                
                if (filaDestino >= 0 && filaDestino <= 2 && columDestino >= 3 && columDestino <= 5) {
                    return true; 
                }
            }
        }

        return false; 
    }
    
    

}
