/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author royum
 */
public class MenuInicio extends JFrame {
    
   MenuInicio menu;
    UserManager usuarios;
    private GuardarPlayers guardarplayers;
    public String registrar;
    public ArrayList<String> logeados = new ArrayList<>();

    private JPanel PanelMenu;
    private JButton BotonDeLog;
    private JButton BotonDeCrearPlayer;
    private JButton BotonDeSalida;

    public MenuInicio() {

        guardarplayers = new UserManager();

        setTitle("Menu Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        
         
        PanelMenu = new JPanel();
        PanelMenu.setLayout(null); 
        PanelMenu.setBackground(Color.red);

        BotonDeLog = new JButton("Iniciar Sesion");
        BotonDeLog.setBounds(150, 50, 200, 40);     
        BotonDeLog.setBackground(Color.BLUE);
        BotonDeLog.setForeground(Color.white);
        PanelMenu.add(BotonDeLog);

        BotonDeCrearPlayer = new JButton("Crear Usuario");
        BotonDeCrearPlayer.setBounds(150, 110, 200, 40);
        BotonDeCrearPlayer.setBackground(Color.ORANGE);
        BotonDeCrearPlayer.setForeground(Color.white);
        PanelMenu.add(BotonDeCrearPlayer);

        BotonDeSalida = new JButton("Salir");
        BotonDeSalida.setBounds(150, 170, 200, 40);
        BotonDeSalida.setBackground(Color.GREEN);
        BotonDeSalida.setForeground(Color.white);
        PanelMenu.add(BotonDeSalida);

    
        add(PanelMenu);

        setVisible(true);

        BotonDeLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogIn LogInxd = new LogIn(guardarplayers);
                dispose();
            }
        });

        BotonDeCrearPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearPlayer crearplayerxd = new CrearPlayer(guardarplayers);
                setVisible(false);
            }
        });

        BotonDeSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}
