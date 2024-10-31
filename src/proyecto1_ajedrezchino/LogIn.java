/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_ajedrezchino;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author royum
 */
public class LogIn extends JFrame {

    UserManager usu;
    public MenuInicio menu;
    User usuario;

    private GuardarPlayers guardarplayers;

    private JTextField TextoNombre;
    private JPasswordField TextoContrasena;

    public LogIn(GuardarPlayers players) {
        this.guardarplayers = players;

        setTitle("Log In");
        setSize(600, 400);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel PanelMenu = new JPanel();
        PanelMenu.setLayout(null);
        PanelMenu.setBackground(Color.PINK);

        JLabel Nombre = new JLabel("Ingrese su nombre de usuario");
        Nombre.setBounds(20, 50, 200, 30);
        PanelMenu.add(Nombre);

        TextoNombre = new JTextField(20);
        TextoNombre.setBounds(260, 50, 200, 30);
        PanelMenu.add(TextoNombre);

        JLabel Contrasena = new JLabel("Ingrese su contraseña");
        Contrasena.setBounds(20, 120, 250, 30);
        PanelMenu.add(Contrasena);

        TextoContrasena = new JPasswordField(20);
        TextoContrasena.setBounds(260, 120, 200, 30);
        PanelMenu.add(TextoContrasena);

        JButton IniciarSesion = new JButton("Iniciar Sesion");
        IniciarSesion.setBackground(Color.magenta);
        IniciarSesion.setBounds(320, 240, 120, 40);
        IniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                INICIAR();
            }
        });
        PanelMenu.add(IniciarSesion);

        JButton Regresar = new JButton("Regresar");
        Regresar.setBackground(Color.LIGHT_GRAY);
        Regresar.setBounds(320, 300, 120, 40);
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuInicio m = new MenuInicio();
                dispose();
            }
        });
        PanelMenu.add(Regresar);

        add(PanelMenu);
        setVisible(true);

        usu = new UserManager(); 

    }

    private void INICIAR() {


            String nombre = TextoNombre.getText().trim();
            String Contrasena = new String(TextoContrasena.getPassword());

            if (nombre.isEmpty() || Contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try{
            
            if (!guardarplayers.ExisteUsuario(nombre)) {
                JOptionPane.showMessageDialog(null, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User usuarioLogueado = guardarplayers.IniciarSesion(nombre, Contrasena);

            if (usuarioLogueado == null) {
                JOptionPane.showMessageDialog(null, "La contraseña debe tener exactamente 5 caracteres.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, "Inicio Sesion exitoso", "Exito", JOptionPane.INFORMATION_MESSAGE);
            MenuPrincipal menu = new MenuPrincipal(usuarioLogueado, usu);
            menu.setVisible(true);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado","ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

}
