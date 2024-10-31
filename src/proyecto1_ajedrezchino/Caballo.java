/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

/**
 *
 * @author royum
 */
public class Caballo extends Pieza {

    private Tablero tablero;

    public Caballo(Bando bando, Tablero tablero) {
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

        
        int diffFilas = Math.abs(filaDestino - filaOrigen);
        int diffColumnas = Math.abs(columDestino - columOrigen);
        


        
        if ((diffFilas == 2 && diffColumnas == 1) || (diffFilas == 1 && diffColumnas == 2)) {

   
            if (diffFilas == 2) {
      
                int filaIntermedia = (filaDestino + filaOrigen) / 2;
                if (tablero.hayPieza(filaIntermedia, columOrigen)) {
                    return false; 
                }
            } else {
            
                int columIntermedia = (columDestino + columOrigen) / 2;
                if (tablero.hayPieza(filaOrigen, columIntermedia)) {
                    return false; 
                }
            }
           
            return true;
        }

        return false; 
    }

}
