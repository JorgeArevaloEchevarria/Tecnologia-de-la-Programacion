package es.ucm.fdi.tp.demo;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import es.ucm.fdi.tp.extra.jboard.JBoard;
import es.ucm.fdi.tp.extra.jcolor.ColorChooser;

public class MainDemoP5 extends JFrame{	

	//-----------------------Atributos---------------------
	private static final long serialVersionUID = 1174265718323260856L;
	private int numFil;
	private int numCol;
	private Integer[][] tablero;
	private JBoard tabView;
	
	//---------------------Constructor---------------------
	
	public MainDemoP5() {
		super("Tablero Juego");
		this.iniTab();
	}

	//-----------------------Main-------------------------- 
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				new MainDemoP5(); 
			}
		});
	}
	
	//----------------------Metodos------------------------
	
	private void crearTablero(int numFil, int numCol) {
		this.numFil = numFil;
		this.numCol = numCol;
		this.tablero = new Integer[this.numFil][this.numCol];
		
		 for (int i = 0; i< this.numFil; i++) {
	            for (int j = 0; j < this.numCol; j++) {
	            	this.tablero[i][j] = null;
	            	if(j < 4) this.tablero[0][2 * j + 1] = 1; //ovejas
	            }
	        }
	   
	       this.tablero[7][0] = 0; //lobo
	}
	
	protected Integer getPosition(int row, int col) {
		return tablero[row][col];
	}

	protected Color getColor(int player) {
		return player == 0 ? Color.GREEN: Color.MAGENTA;
	}

	public void iniTab() {
		
		crearTablero(8,8);
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		this.tabView = new JBoard(){
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
				System.out.println("Mouse: " + clickCount + "clicks at position (" + row + "," + col + ") with Button "
						+ mouseButton);
			}

			@Override
			protected void keyTyped(int keyCode) {
				System.out.println("Key " + keyCode + " pressed ..");
			}

			@Override
			protected Shape getShape(int player) {
				return Shape.CIRCLE;
			}

			@Override
			protected Integer getPosition(int row, int col) {
				return MainDemoP5.this.getPosition(row, col);
			}

			@Override
			protected int getNumRows() {
				return numFil;
			}

			@Override
			protected int getNumCols() {
				return numCol;
			}

			@Override
			protected Color getColor(int player) {
				return MainDemoP5.this.getColor(player);
			}

			@Override
			protected Color getBackground(int row, int col) {
				return (row+col) % 2 == 0 ? Color.LIGHT_GRAY : Color.BLACK;
			}

			@Override
			protected int getSepPixels() {
				return 1; 
			}
		};
		
		mainPanel.add(this.tabView, BorderLayout.CENTER);
		
		
		JPanel panelDer = new JPanel(new BorderLayout()); // Panel derecha Incluye JTextArea + JTable (colores)
		
		JPanel panelDerArriba = new JPanel(new BorderLayout()); // Solo incluye el JTextArea
		JTextArea miTexto = new JTextArea();
		miTexto.setEditable(false); // deshabilitar el modo escritura en el JTextArea
		miTexto.setLineWrap(true);	// Se usa para saltar de linea en el JTextArea
		miTexto.setText("Selected (3,2). Click on destination cell or ESC to cancel selection."); // Metodo para añadir mensajes a JTextArea;
		panelDerArriba.setBorder(BorderFactory.createTitledBorder("Status Messages")); // Metodo para dar titulo a la particion;
		JScrollPane barraScroll = new JScrollPane(miTexto); // Añadimos la barra de Scroll al JTextArea
		panelDerArriba.add(barraScroll, BorderLayout.CENTER); //Añadimos el JTextArea con Scroll al panel
		
		JPanel panelDerAbajo = new JPanel(new BorderLayout()); // Solo incluye el JTable
		JTable jTable = new JTable();
		ColorChooser miColor = new ColorChooser(new JFrame(), "Choose Line Color", Color.BLACK);
		
		 jTable.setModel(new DefaultTableModel(
            new Object [][] {
                {0, miColor.getColor()}, {1, miColor.getColor()} },
            new String [] { "#Player", "Color"}
        ));

		 
		panelDerAbajo.setBorder(BorderFactory.createTitledBorder("Player Information")); // Metodo para dar titulo a la particion;
		panelDerAbajo.add(jTable, BorderLayout.CENTER);
		
		panelDer.add(panelDerArriba, BorderLayout.CENTER);
		panelDer.add(panelDerAbajo, BorderLayout.SOUTH);
		
		mainPanel.add(panelDer, BorderLayout.EAST);
		
		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 500);
		this.setVisible(true);
	}
}
