/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Controlador;
import Modelo.CLOOK;
import Modelo.CSCAN;
import Modelo.Disco;
import Modelo.Estrategia;
import Modelo.FIFO;
import Modelo.FSCAN;
import Modelo.LOOK;
import Modelo.NSTEPSCAN;
import Modelo.SCAN;
import Modelo.SSTF;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.TableView.TableRow;

/**
 *
 * @author Fede
 */
public class PantallaPrincipal extends javax.swing.JFrame {
    Controlador controlador = new Controlador();
    DefaultTableModel modelo = new DefaultTableModel();
    PantallaSalida pantallaSalida;
    JTextArea salida = new JTextArea();
    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal() {
        pantallaSalida = new PantallaSalida();
        modelo.addColumn("Nro de peticion");
        modelo.addColumn("Nro de pista requerido");
   
        initComponents();
        
        jTablaPeticiones.setModel(modelo);
        jPanelFSCAN.setVisible(false);
        jPanelSTEPSCAN.setVisible(false);
        
        aplicarFiltroSoloNumeros(jVelocidadRotacion);
        aplicarFiltroSoloNumeros(jTamanioBloque);
        aplicarFiltroSoloNumeros(jTotalPistas);
        aplicarFiltroSoloNumeros(jPosicionCabezal);
        aplicarFiltroSoloNumeros(jLongitudListas);
        aplicarFiltroSoloNumeros(jCantElementosPrimerLista);
        aplicarFiltroSoloNumeros(jNumeroPeticion);
        aplicarFiltroSoloNumeros(jPeticionesAleatorias);
        aplicarFiltroSoloNumeros(jLongitudListas);
        aplicarFiltroSoloNumeros(jCantElementosPrimerLista);

    }

    private void aplicarFiltroSoloNumeros(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (esNumero(string)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (esNumero(text)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean esNumero(String text) {
                try {
                    Integer.parseInt(text);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jEjecutar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jNumeroPeticion = new javax.swing.JTextField();
        jAgregarPeticion = new javax.swing.JButton();
        jVaciarLista = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablaPeticiones = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPeticionesAleatorias = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jGenerarPeticiones = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jAlgoritmoPlanificacion = new javax.swing.JComboBox<>();
        jSeekTimeMedio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jVelocidadRotacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTiempoDeTransferencia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTamanioBloque = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTotalPistas = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPosicionCabezal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSentido = new javax.swing.JComboBox<>();
        jPanelSTEPSCAN = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLongitudListas = new javax.swing.JTextField();
        jPanelFSCAN = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jCantElementosPrimerLista = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulador de Planificación de Disco");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 130, 8, -1));

        jEjecutar.setText("Ejecutar el simulador");
        jEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEjecutarActionPerformed(evt);
            }
        });
        getContentPane().add(jEjecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 470, 164, 53));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Ingrese una petición:");

        jNumeroPeticion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNumeroPeticionActionPerformed(evt);
            }
        });

        jAgregarPeticion.setText("Agregar peticion a lista");
        jAgregarPeticion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAgregarPeticionActionPerformed(evt);
            }
        });

        jVaciarLista.setText("Vaciar lista");
        jVaciarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVaciarListaActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("Requerimientos");

        jTablaPeticiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nro Peticion", "Nro Pista"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTablaPeticiones);

        jLabel14.setText("Para generar peticiones aleatorias: ");

        jLabel16.setText("Cantidad de peticiones");

        jGenerarPeticiones.setText("Generar Peticiones");
        jGenerarPeticiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGenerarPeticionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(53, 53, 53)
                        .addComponent(jPeticionesAleatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jGenerarPeticiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jAgregarPeticion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(33, 33, 33)
                        .addComponent(jNumeroPeticion))
                    .addComponent(jVaciarLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jNumeroPeticion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jAgregarPeticion)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPeticionesAleatorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addComponent(jGenerarPeticiones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jVaciarLista)
                        .addGap(28, 28, 28))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 620, 430));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Parámetros del Simulador");

        jLabel11.setText("Algoritmo de Planificación");

        jAlgoritmoPlanificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FCFS", "SSTF", "SCAN", "C-SCAN", "LOOK", "C-LOOK", "F-SCAN", "N-STEP-SCAN" }));
        jAlgoritmoPlanificacion.setNextFocusableComponent(jSeekTimeMedio);
        jAlgoritmoPlanificacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jAlgoritmoPlanificacionItemStateChanged(evt);
            }
        });
        jAlgoritmoPlanificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAlgoritmoPlanificacionActionPerformed(evt);
            }
        });

        jSeekTimeMedio.setNextFocusableComponent(jVelocidadRotacion);
        jSeekTimeMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSeekTimeMedioActionPerformed(evt);
            }
        });

        jLabel3.setText("Seek Time Medio");

        jLabel4.setText("Velocidad de rotacion en RPM");

        jVelocidadRotacion.setNextFocusableComponent(jTiempoDeTransferencia);

        jLabel5.setText("Tiempo de transferencia de 1 sector");

        jTiempoDeTransferencia.setNextFocusableComponent(jTamanioBloque);

        jLabel6.setText("Tamaño de cada bloque (en sectores)");

        jTamanioBloque.setNextFocusableComponent(jTotalPistas);

        jLabel7.setText("Total de pistas del disco");

        jTotalPistas.setNextFocusableComponent(jPosicionCabezal);

        jLabel8.setText("Posición de la cabeza");

        jPosicionCabezal.setNextFocusableComponent(jSentido);

        jLabel9.setText("Sentido en el que está avanzando el cabezal");

        jSentido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Creciente", "Decreciente" }));

        jLabel13.setText("Longitud de las listas");

        javax.swing.GroupLayout jPanelSTEPSCANLayout = new javax.swing.GroupLayout(jPanelSTEPSCAN);
        jPanelSTEPSCAN.setLayout(jPanelSTEPSCANLayout);
        jPanelSTEPSCANLayout.setHorizontalGroup(
            jPanelSTEPSCANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSTEPSCANLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(167, 167, 167)
                .addComponent(jLongitudListas, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSTEPSCANLayout.setVerticalGroup(
            jPanelSTEPSCANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSTEPSCANLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelSTEPSCANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLongitudListas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setText("Cantidad de elementos de la primer lista");

        javax.swing.GroupLayout jPanelFSCANLayout = new javax.swing.GroupLayout(jPanelFSCAN);
        jPanelFSCAN.setLayout(jPanelFSCANLayout);
        jPanelFSCANLayout.setHorizontalGroup(
            jPanelFSCANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFSCANLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(64, 64, 64)
                .addComponent(jCantElementosPrimerLista, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
        );
        jPanelFSCANLayout.setVerticalGroup(
            jPanelFSCANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFSCANLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanelFSCANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jCantElementosPrimerLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSentido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPosicionCabezal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTotalPistas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTamanioBloque, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTiempoDeTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jVelocidadRotacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeekTimeMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(136, 136, 136)
                            .addComponent(jAlgoritmoPlanificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFSCAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelSTEPSCAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jAlgoritmoPlanificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSeekTimeMedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jVelocidadRotacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTiempoDeTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTamanioBloque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTotalPistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jPosicionCabezal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jSentido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelSTEPSCAN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelFSCAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 450, 430));
        getContentPane().add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 530, -1, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSeekTimeMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSeekTimeMedioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSeekTimeMedioActionPerformed

    private void jEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEjecutarActionPerformed
        // TODO add your handling code here:
        if (jSeekTimeMedio.getText().isEmpty() 
                || jVelocidadRotacion.getText().isEmpty() 
                || jTiempoDeTransferencia.getText().isEmpty()
                || jTamanioBloque.getText().isEmpty()
                || jTotalPistas.getText().isEmpty() 
                || jPosicionCabezal.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (jAlgoritmoPlanificacion.getSelectedItem().toString() == "F-SCAN" && jCantElementosPrimerLista.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
            }else if (jAlgoritmoPlanificacion.getSelectedItem().toString() == "N-STEP-SCAN" && jLongitudListas.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                pantallaSalida.setVisible(true);
                Character sentido;
                if(jSentido.getModel().getSelectedItem().toString().compareTo("Creciente") == 0){
                    sentido = 'C';
                }else{
                    sentido = 'D';
                }
                Disco disco = new Disco(
                        Integer.valueOf(jTotalPistas.getText()), 
                        Integer.valueOf(jPosicionCabezal.getText()), 
                        sentido, 
                        Integer.valueOf(jVelocidadRotacion.getText()), 
                        Float.valueOf(jTiempoDeTransferencia.getText()),
                        Integer.valueOf(jTamanioBloque.getText()), 
                        Float.valueOf(jSeekTimeMedio.getText()));

                switch(jAlgoritmoPlanificacion.getSelectedItem().toString()){
                    case "FCFS":
                        pantallaSalida.setVisible(false);
                        pantallaSalida.setVisible(true);
                        Estrategia fifo = new FIFO();
                        fifo.ejecutar(disco, controlador.obtenerListaPeticiones(), pantallaSalida);
                        break;
                    case "SSTF":
                        Estrategia sstf = new SSTF();
                        sstf.ejecutar(disco, controlador.obtenerListaPeticiones(), pantallaSalida);
                        break;
                    case "SCAN":
                        Estrategia scan = new SCAN();
                        scan.ejecutar(disco, controlador.obtenerListaPeticiones(), pantallaSalida);
                        break;
                    case "C-SCAN":
                        Estrategia cscan = new CSCAN();
                        cscan.ejecutar(disco, controlador.obtenerListaPeticiones(), pantallaSalida);
                        break;
                    case "LOOK":
                        Estrategia look = new LOOK();
                        look.ejecutar(disco, controlador.obtenerListaPeticiones(), pantallaSalida);
                        break;
                    case "C-LOOK":
                        Estrategia clook = new CLOOK();
                        clook.ejecutar(disco, controlador.obtenerListaPeticiones(),pantallaSalida);
                        break;
                    case "F-SCAN":
                        disco.setElementosEnPrimerLista_FSCAN(Integer.valueOf(jCantElementosPrimerLista.getText()));
                        Estrategia fscan = new FSCAN();
                        fscan.ejecutar(disco, controlador.obtenerListaPeticiones(), pantallaSalida);
                        break;
                    case "N-STEP-SCAN":
                        disco.setTamanioListaNSTEPSCAN(Integer.valueOf(jLongitudListas.getText()));
                        Estrategia nstepscan = new NSTEPSCAN();
                        nstepscan.ejecutar(disco, controlador.obtenerListaPeticiones(), pantallaSalida);
                        break;
                }
            }
        }
    }//GEN-LAST:event_jEjecutarActionPerformed

    private void jAgregarPeticionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAgregarPeticionActionPerformed
        if (jNumeroPeticion.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Debe ingresar un número de petición,", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            controlador.agregarPeticion(Integer.valueOf(jNumeroPeticion.getText()));
        
            int i = controlador.obtenerListaPeticiones().size() -1;

            modelo.addRow(new Object[]{i, controlador.obtenerListaPeticiones().get(i)});

            jNumeroPeticion.setText("");
            jNumeroPeticion.requestFocus();
        }
    }//GEN-LAST:event_jAgregarPeticionActionPerformed

    private void jAlgoritmoPlanificacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jAlgoritmoPlanificacionItemStateChanged
        // TODO add your handling code here:
        if(jAlgoritmoPlanificacion.getModel().getSelectedItem().toString().compareTo("F-SCAN") == 0){
            jPanelSTEPSCAN.setVisible(false);
            jPanelFSCAN.setVisible(true);
        }else if(jAlgoritmoPlanificacion.getModel().getSelectedItem().toString().compareTo("N-STEP-SCAN") == 0){
            jPanelSTEPSCAN.setVisible(true);
            jPanelFSCAN.setVisible(false);
        }else{
            jPanelSTEPSCAN.setVisible(false);
            jPanelFSCAN.setVisible(false);
        }
    }//GEN-LAST:event_jAlgoritmoPlanificacionItemStateChanged

    private void jAlgoritmoPlanificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAlgoritmoPlanificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAlgoritmoPlanificacionActionPerformed

    /**
        Vacia la lista al hacer click 
    */
    private void jVaciarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVaciarListaActionPerformed
        
        if (controlador.obtenerListaPeticiones().size() == 0){
            JOptionPane.showMessageDialog(this, "La lista ya está vacía.", "Atención", JOptionPane.WARNING_MESSAGE);
        }else{
            for(int i=controlador.obtenerListaPeticiones().size() -1; i>=0; i--){
                modelo.removeRow(i);
            }
            controlador.eliminarTodasLasPeticiones();
        }
    }//GEN-LAST:event_jVaciarListaActionPerformed

    private void jGenerarPeticionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGenerarPeticionesActionPerformed
        if (jPeticionesAleatorias.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad,", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(jTotalPistas.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Debe ingresar el total de pistas.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Double d = 0.1;
        
            for(int i=0; i <= Integer.valueOf(jPeticionesAleatorias.getText()); i++ ){
                Integer peticionGenerada = (int) (Math.random() * Integer.valueOf(jTotalPistas.getText())) + 1;
                controlador.agregarPeticion(peticionGenerada);
                int j = controlador.obtenerListaPeticiones().size() -1;

                modelo.addRow(new Object[]{j, controlador.obtenerListaPeticiones().get(i)});
            }
        }
    }//GEN-LAST:event_jGenerarPeticionesActionPerformed

    private void jNumeroPeticionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNumeroPeticionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNumeroPeticionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jAgregarPeticion;
    private javax.swing.JComboBox<String> jAlgoritmoPlanificacion;
    private javax.swing.JTextField jCantElementosPrimerLista;
    private javax.swing.JButton jEjecutar;
    private javax.swing.JButton jGenerarPeticiones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLongitudListas;
    private javax.swing.JTextField jNumeroPeticion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelFSCAN;
    private javax.swing.JPanel jPanelSTEPSCAN;
    private javax.swing.JTextField jPeticionesAleatorias;
    private javax.swing.JTextField jPosicionCabezal;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jSeekTimeMedio;
    private javax.swing.JComboBox<String> jSentido;
    private javax.swing.JTable jTablaPeticiones;
    private javax.swing.JTextField jTamanioBloque;
    private javax.swing.JTextField jTiempoDeTransferencia;
    private javax.swing.JTextField jTotalPistas;
    private javax.swing.JButton jVaciarLista;
    private javax.swing.JTextField jVelocidadRotacion;
    // End of variables declaration//GEN-END:variables
}
