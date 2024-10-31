/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

import javax.swing.JOptionPane;

/**
 *
 * @author royum
 */
public class Elefante extends Pieza {

    private Tablero tablero;

    public Elefante(Bando bando, Tablero tablero) {
        super(bando);
        this.tablero = tablero;
    }

    @Override
    public boolean MovimientoPiezas(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        

        int diffFilas = Math.abs(filaDestino - filaOrigen);
        int diffColumnas = Math.abs(columDestino - columOrigen);

      
        if (diffFilas == 2 && diffColumnas == 2) {

            
            if (bando == Bando.ROJO && filaDestino < 4) { 
                
                return false; 
            }
            if (bando == Bando.NEGRO && filaDestino > 5) {
                
                return false; 
            }

            
            int filaIntermedia = (filaOrigen + filaDestino) / 2;
            int columIntermedia = (columOrigen + columDestino) / 2;
            System.out.println("Verificando casilla intermedia: (" + filaIntermedia + ", " + columIntermedia + ")");
            
            
            if (tablero.hayPieza(filaIntermedia, columIntermedia)) {
                System.out.println("Movimiento bloqueado, hay una pieza en la casilla intermedia.");
                return false;
            }
            
           
            Pieza piezaDestino=tablero.obtenerPieza(filaDestino, columDestino);
            if(piezaDestino!=null){
               
                if(piezaDestino.getBando()==this.getBando()){
                    return false;
                    
                }
            }

     
           
            return true; 
        }

      
        
        return false;
    }
}