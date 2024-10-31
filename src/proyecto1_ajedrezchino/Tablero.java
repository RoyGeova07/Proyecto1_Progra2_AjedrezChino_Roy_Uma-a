/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author royum
 */
// LA ESENCIAAAAA DEL PROYECTOOOOO
public final class Tablero extends JFrame {
    

    private JButton[][] botonesTablero = new JButton[10][9];
    private Pieza[][] piezasTablero = new Pieza[10][9]; 
    private String rutaImagenes = "/img/";
    private User jugadorLogueado;
    private User oponente;
    private Bando turnoActual = Bando.ROJO; 
    private JLabel turnoLabel;
    private JTextArea areaPiezasComidas1;
    private JTextArea areaPiezasComidas2;
    private UserManager usermanager;
    
      private JPanel AreaImagenesCapturadas2; 
      private JPanel  AreaImagenesCapturadas1;
    private JPanel areaPiezasComidas2Imagenes;

    private int filaSeleccionada = -1;
    private int columnaSeleccionada = -1;
//-------------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static List<String> LogsPartidas = new ArrayList<>();  
//-------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public Tablero(User jugadorLogueado, User oponente, UserManager usermanager) {
        this.jugadorLogueado = jugadorLogueado;
        this.oponente = oponente;
        this.usermanager = usermanager;

        setTitle("Tablero");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
        setLayout(new BorderLayout());


        JPanel panelTablero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillRect(0, getHeight() / 2 - 2, getWidth(), 4); //
            }
        };

        panelTablero.setLayout(new GridLayout(10, 9, 5, 5)); 
        panelTablero.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                botonesTablero[fila][columna] = new JButton();

                
                if (fila < 5) {
                    botonesTablero[fila][columna].setBackground(new Color(245, 222, 179));
                } else {
                    botonesTablero[fila][columna].setBackground(new Color(210, 180, 140));
                }

               
                if ((fila >= 0 && fila <= 2 && columna >= 3 && columna <= 5)
                        || (fila >= 7 && fila <= 9 && columna >= 3 && columna <= 5)) {
                    botonesTablero[fila][columna].setBackground(Color.WHITE);
                }

                
                asignarPiezas(fila, columna);

                
                final int f = fila; 
                final int c = columna;
                botonesTablero[fila][columna].addActionListener(e -> manejarClick(f, c));

                
                panelTablero.add(botonesTablero[fila][columna]);
            }
        }

         
        JPanel panelOpciones = new JPanel(new BorderLayout());
        JPanel panelInformacion = new JPanel(new GridLayout(4, 1, 10, 10));
        turnoLabel = new JLabel("Turno Jugador: " + (turnoActual == Bando.ROJO ? "ROJO: " + jugadorLogueado.nombre : "NEGRO: " + oponente.nombre), JLabel.CENTER);
        
       
        AreaImagenesCapturadas1 = new JPanel(new GridLayout(0, 1, 5, 10));
        AreaImagenesCapturadas1.setPreferredSize(new Dimension(150, 900));
        AreaImagenesCapturadas2 = new JPanel(new GridLayout(0, 1, 5, 10));
        AreaImagenesCapturadas2.setPreferredSize(new Dimension(150, 900));

       
        panelInformacion.add(new JLabel("Piezas comidas por NEGRO ⬇️", JLabel.CENTER));
        panelInformacion.add(new JScrollPane(AreaImagenesCapturadas1));
        panelInformacion.add(new JLabel("Piezas comidas por ROJO  ⬇️", JLabel.CENTER));
        panelInformacion.add(new JScrollPane(AreaImagenesCapturadas2));

        
        JButton abandonarButton = new JButton("Abandonar Partida");
        abandonarButton.setBackground(Color.RED);
        abandonarButton.setForeground(Color.WHITE);
        abandonarButton.setPreferredSize(new Dimension(150, 30));
        abandonarButton.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas abandonar la partida?", "Confirmar Abandono", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                AbandonarPartida();
            }
        });

        panelOpciones.add(panelInformacion, BorderLayout.CENTER);
        panelOpciones.add(abandonarButton, BorderLayout.SOUTH); 
       add(panelOpciones, BorderLayout.WEST);

      
        JPanel panelJugadores = new JPanel(new BorderLayout());
        panelJugadores.setPreferredSize(new Dimension(300, 600));

        JPanel panelPiezasComidas1 = new JPanel(new BorderLayout());
        JLabel nombreJugador1 = new JLabel("NEGRO Piezas comidas Jugador " + oponente.nombre, JLabel.CENTER);
        panelPiezasComidas1.add(nombreJugador1, BorderLayout.NORTH);
        areaPiezasComidas1 = new JTextArea(10, 20);
        areaPiezasComidas1.setEditable(false);
        panelPiezasComidas1.add(new JScrollPane(areaPiezasComidas1), BorderLayout.CENTER);

        JPanel panelPiezasComidas2 = new JPanel(new BorderLayout());
        JLabel nombreJugador2 = new JLabel("ROJO Piezas comidas Jugador " + jugadorLogueado.nombre, JLabel.CENTER);
        panelPiezasComidas2.add(nombreJugador2, BorderLayout.NORTH);
        areaPiezasComidas2 = new JTextArea(10, 20);
        areaPiezasComidas2.setEditable(false);
        panelPiezasComidas2.add(new JScrollPane(areaPiezasComidas2), BorderLayout.CENTER);

        panelJugadores.add(panelPiezasComidas1, BorderLayout.NORTH);
        panelJugadores.add(turnoLabel, BorderLayout.CENTER);
        panelJugadores.add(panelPiezasComidas2, BorderLayout.SOUTH);

        add(panelTablero, BorderLayout.CENTER);
        add(panelJugadores, BorderLayout.EAST);

        setVisible(true);
    }


    private void manejarClick(int fila, int columna) {
       
        if (filaSeleccionada == -1 && columnaSeleccionada == -1) {
            if (piezasTablero[fila][columna] != null && piezasTablero[fila][columna].getBando() == turnoActual) {
                filaSeleccionada = fila;
                columnaSeleccionada = columna;

               
                resaltarMovimientos(fila, columna);
            }
        } 
        else {
            moverPieza(filaSeleccionada, columnaSeleccionada, fila, columna);
            
            restaurarColoresTablero();
            filaSeleccionada = -1;
            columnaSeleccionada = -1;
        }
    }

   
    private void resaltarMovimientos(int filaOrigen, int columOrigen) {
        Pieza pieza = piezasTablero[filaOrigen][columOrigen];

        
        if (pieza != null) {
            System.out.println("Resaltando movimientos validos para la pieza: " + pieza.getClass().getSimpleName());
            for (int fila = 0; fila < 10; fila++) {
                for (int columna = 0; columna < 9; columna++) {
                    
                    if (pieza.MovimientoPiezas(filaOrigen, columOrigen, fila, columna)) {
                        botonesTablero[fila][columna].setBackground(Color.GREEN); 
                    }
                }
            }
        }
    }


    private void restaurarColoresTablero() {
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                if (fila < 5) {
                    botonesTablero[fila][columna].setBackground(new Color(245, 222, 179));
                } else {
                    botonesTablero[fila][columna].setBackground(new Color(210, 180, 140));
                }

                if ((fila >= 0 && fila <= 2 && columna >= 3 && columna <= 5)
                        || (fila >= 7 && fila <= 9 && columna >= 3 && columna <= 5)) {
                    botonesTablero[fila][columna].setBackground(Color.white);
                }
            }
        }
    }

    private void asignarPiezas(int fila, int columna) {
        if (fila == 0 || fila == 9) {
            
            if (columna == 0 || columna == 8) {
                piezasTablero[fila][columna] = new Torre(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 1 || columna == 7) {
                piezasTablero[fila][columna] = new Caballo(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 2 || columna == 6) {
                piezasTablero[fila][columna] = new Elefante(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 3 || columna == 5) {
                piezasTablero[fila][columna] = new Oficial(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            } else if (columna == 4) {
                piezasTablero[fila][columna] = new General(fila == 0 ? Bando.NEGRO : Bando.ROJO, this);
            }
        }

   
        if ((fila == 2 && (columna == 1 || columna == 7))
                || (fila == 7 && (columna == 1 || columna == 7))) {
            piezasTablero[fila][columna] = new Cañon(fila == 2 ? Bando.NEGRO : Bando.ROJO, this);
        }

        
        if ((fila == 3 && columna % 2 == 0) || (fila == 6 && columna % 2 == 0)) {
            piezasTablero[fila][columna] = new Soldado(fila == 3 ? Bando.NEGRO : Bando.ROJO, this);
        }

        
        if (piezasTablero[fila][columna] != null) {
            String imagen = obtenerImagenPieza(piezasTablero[fila][columna]);
            botonesTablero[fila][columna].setIcon(new ImageIcon(getClass().getResource(rutaImagenes + imagen)));
        }
    }

    
    private String obtenerImagenPieza(Pieza pieza) {
        if (pieza instanceof Torre) {
            return pieza.getBando() == Bando.ROJO ? "CarroRojo.png" : "CarroNegro.png";
        }
        if (pieza instanceof Caballo) {
            return pieza.getBando() == Bando.ROJO ? "CaballoRojo.png" : "CaballoNegro.png";
        }
        if (pieza instanceof Elefante) {
            return pieza.getBando() == Bando.ROJO ? "ElefanteRojo.png" : "ElefanteNegro.png";
        }
        if (pieza instanceof Oficial) {
            return pieza.getBando() == Bando.ROJO ? "OficialRojo.png" : "OficialNegro.png";
        }
        if (pieza instanceof Cañon) {
            return pieza.getBando() == Bando.ROJO ? "CañonRojo.png" : "CañonNegro.png";
        }
        if (pieza instanceof Soldado) {
            return pieza.getBando() == Bando.ROJO ? "SoldadoRojo.png" : "SoldadoNegro.png";
        }
        if (pieza instanceof General) {
            return pieza.getBando() == Bando.ROJO ? "ReyRojo.png" : "ReyNegro.png";
        }
        return "";
    }

    
    public boolean hayPieza(int fila, int columna) {
        return piezasTablero[fila][columna] != null;
    }

    public Pieza obtenerPieza(int fila, int columna) {
        if (fila >= 0 && fila < piezasTablero.length && columna >= 0 && columna < piezasTablero[0].length) {
            return piezasTablero[fila][columna];
        }
        return null;
    }
    
    public void moverPieza(int filaOrigen, int columOrigen, int filaDestino, int columDestino) {
        

        Pieza pieza = piezasTablero[filaOrigen][columOrigen];
        Pieza piezaCapturada = piezasTablero[filaDestino][columDestino];

        if (pieza != null && pieza.MovimientoPiezas(filaOrigen, columOrigen, filaDestino, columDestino)) {
        
            piezasTablero[filaDestino][columDestino] = pieza;
            piezasTablero[filaOrigen][columOrigen] = null;

            
            if (piezaCapturada instanceof General) {
                anunciarVictoria();
                return;  
            }

            
            if (!reyesNoPuedenVerse()) {
                piezasTablero[filaOrigen][columOrigen] = pieza;
                piezasTablero[filaDestino][columDestino] = piezaCapturada;
                JOptionPane.showMessageDialog(this, "Movimiento invalido: los generales no pueden verse en la misma fila.", "Movimiento no permitido", JOptionPane.WARNING_MESSAGE);
                return;
            }

      
            if (piezaCapturada != null) {
                if (turnoActual == Bando.ROJO) {
                    mostrarPiezaCapturada(piezaCapturada);
                    areaPiezasComidas2.append(jugadorLogueado.nombre + " ha capturado la pieza " + piezaCapturada.getClass().getSimpleName() + "\n");
                } else {
                    mostrarPiezaCapturada(piezaCapturada);
                    areaPiezasComidas1.append(oponente.nombre + " ha capturado la pieza " + piezaCapturada.getClass().getSimpleName() + "\n");
                }
            }

            String imagen = obtenerImagenPieza(pieza);
            botonesTablero[filaDestino][columDestino].setIcon(new ImageIcon(getClass().getResource(rutaImagenes + imagen)));
            botonesTablero[filaOrigen][columOrigen].setIcon(null);

            String mensajeMovimiento = (turnoActual == Bando.ROJO ? jugadorLogueado.nombre : oponente.nombre)
                    + " ha movido la pieza " + pieza.getClass().getSimpleName()
                    + " a la casilla (" + filaDestino + ", " + columDestino + ")\n";

            if (turnoActual == Bando.ROJO) {
                areaPiezasComidas2.append(mensajeMovimiento);
            } else {
                areaPiezasComidas1.append(mensajeMovimiento);
            }

            
         
            turnoActual = (turnoActual == Bando.ROJO) ? Bando.NEGRO : Bando.ROJO;
            turnoLabel.setText("Turno Jugador " + (turnoActual == Bando.ROJO ? "ROJO: " + jugadorLogueado.nombre : "NEGRO: " + oponente.nombre));
        }
    }

    public int obtenerFilaGeneral(Bando bando) {
        for (int fila = 0; fila < 10; fila++) {
            for (int col = 0; col < 9; col++) {
                Pieza pieza = piezasTablero[fila][col];
                if (pieza instanceof General && pieza.getBando() == bando) {
                    return fila;
                }
            }
        }
        return -1; 
    }

    public boolean reyesNoPuedenVerse() {
        int filaReyRojo = -1;
        int columnaReyRojo = -1;
        int filaReyNegro = -1;
        int columnaReyNegro = -1;

      
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                Pieza pieza = piezasTablero[fila][columna];
                if (pieza instanceof General) {
                    if (pieza.getBando() == Bando.ROJO) {
                        filaReyRojo = fila;
                        columnaReyRojo = columna;
                    } else if (pieza.getBando() == Bando.NEGRO) {
                        filaReyNegro = fila;
                        columnaReyNegro = columna;
                    }
                }
            }
        }

  
        if (columnaReyRojo == columnaReyNegro) {
            int minFila = Math.min(filaReyRojo, filaReyNegro);
            int maxFila = Math.max(filaReyRojo, filaReyNegro);

       
            for (int fila = minFila + 1; fila < maxFila; fila++) {
                if (piezasTablero[fila][columnaReyRojo] != null) {
                    return true;
                }
            }
            return false;
        }
        return true; 
    }

    private void mostrarPiezaCapturada(Pieza piezaCapturada) {
        String imagenPieza = obtenerImagenPieza(piezaCapturada);
        JLabel piezaIcono = new JLabel(new ImageIcon(getClass().getResource(rutaImagenes + imagenPieza)));

        if (piezaCapturada.getBando() == Bando.NEGRO) {
            AreaImagenesCapturadas2.add(piezaIcono); 
        } else {
            AreaImagenesCapturadas1.add(piezaIcono); 
        }

        AreaImagenesCapturadas1.revalidate();
        AreaImagenesCapturadas1.repaint();
        AreaImagenesCapturadas2.revalidate();
        AreaImagenesCapturadas2.repaint();
    }

    private void anunciarVictoria() {
        String ganador = (turnoActual == Bando.NEGRO) ? oponente.nombre : jugadorLogueado.nombre;
        LogsPartidas.add(jugadorLogueado.nombre+ " VS " + oponente.nombre+ " = gano "+ganador);
        
        JOptionPane.showMessageDialog(this, "¡ " + ganador + " HA GANADO LA PARTIDA +3 PUNTOS!", "JAQUE MATE", JOptionPane.INFORMATION_MESSAGE);

        if (turnoActual == Bando.NEGRO) {
            oponente.Sumarpuntos(3);
        } else {
            jugadorLogueado.Sumarpuntos(3);
        }
        dispose();
        MenuPrincipal m = new MenuPrincipal(jugadorLogueado, usermanager);
        m.setVisible(true);
    }



    private void AbandonarPartida() {

        String ganador = turnoActual == Bando.ROJO ? oponente.nombre : jugadorLogueado.nombre;
        LogsPartidas.add(jugadorLogueado.nombre+ " VS " + oponente.nombre+ " = gano "+ganador+ " (por abandono)");

        if (turnoActual == Bando.ROJO) {
            oponente.Sumarpuntos(3);
        } else {
            jugadorLogueado.Sumarpuntos(3);
        }

        JOptionPane.showMessageDialog(this, "El jugador " + (turnoActual == Bando.ROJO ? jugadorLogueado.nombre : oponente.nombre) + " ha abandonado la partida, Felicidades " + ganador + " has ganado 3 puntos ", "Partida Abandonada", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        MenuPrincipal m = new MenuPrincipal(jugadorLogueado, usermanager);
        m.setVisible(true);

    }

}
