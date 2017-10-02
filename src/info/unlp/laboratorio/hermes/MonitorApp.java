package info.unlp.laboratorio.hermes;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MonitorApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField crearEtiquetaInput;
	private JTextField newNameLabel;
	private static JTable table;
	private static FactoryDAO factory = new FactoryDAO();
	private List<Notificacion> notifies;
	private static JComboBox<String> deleteLabel = new JComboBox<String>();
	private static JComboBox<String> asignLabel = new JComboBox<String>();
	private static JComboBox<String> renameLabel = new JComboBox<String>();
	private static JComboBox<String> labels = new JComboBox<String>();
	private static JComboBox<String> contenidos = new JComboBox<String>();
	private static JComboBox<String> contextos = new JComboBox<String>();
	private static JComboBox<String> categorias = new JComboBox<String>();
	private static JComboBox<String> childs = new JComboBox<String>();
	private JSpinner date1 = new JSpinner();
	private JSpinner date2 = new JSpinner();
	private JScrollPane notificaciones = new JScrollPane();
	private TableRowSorter<MyTableModel> sorter;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonitorApp frame = new MonitorApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MonitorApp() {
		initialize();
		setResizable(false);
		setTitle("Hermes Monitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 724);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelFiltros = new JPanel();
		panelFiltros.setBackground(Color.LIGHT_GRAY);
		panelFiltros.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panelEtiquetas = new JPanel();
		panelEtiquetas.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), "Etiquetas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEtiquetas.setBackground(Color.LIGHT_GRAY);
		
		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.LIGHT_GRAY);
		panelTable.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2, true), "Notificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panelFiltros, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panelEtiquetas, GroupLayout.PREFERRED_SIZE, 452, Short.MAX_VALUE))
						.addComponent(panelTable, GroupLayout.PREFERRED_SIZE, 1014, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(panelFiltros, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelEtiquetas, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelTable, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
		);
		panelTable.setLayout(null);
		
		notificaciones.setBounds(12, 24, 990, 350);
		panelTable.add(notificaciones);
		
		notifies = factory.getNotifyDAO().getAllNotifies();
		/* crearTabla(notifies);*/
        MyTableModel tabla = new MyTableModel();
        sorter = new TableRowSorter<MyTableModel>(tabla);
        crearTabla(tabla, sorter);

        
        
		contenidos.setModel(contenidosSelect());
		contenidos.setSelectedIndex(0);
		
		JLabel lblCrearEtiqueta = new JLabel("Crear Etiqueta:");
		
		crearEtiquetaInput = new JTextField();
		crearEtiquetaInput.setColumns(10);
		
		JButton crearButton = new JButton("Crear");
		crearButton.addActionListener(new crearEtiqueta());
		
		JLabel lblEliminarEtiqueta = new JLabel("Eliminar Etiqueta:");
		
		deleteLabel.setModel(labelsSelect());
		deleteLabel.setSelectedItem(-1);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new borrarEtiqueta());
		
		JLabel lblAsignarEtiqueta = new JLabel("Asignar Etiqueta:");
		
		asignLabel.setModel(labelsSelect());
		asignLabel.setSelectedItem(-1);
		
		JButton btnAsignar = new JButton("Asignar");
		
		JLabel lblRenombrarEtiqueta = new JLabel("Renombrar Etiqueta:");
		
		renameLabel.setModel(labelsSelect());
		renameLabel.setSelectedItem(-1);
		
		JLabel lblNuevoNombre = new JLabel("Nuevo nombre:");
		
		newNameLabel = new JTextField();
		newNameLabel.setColumns(10);
		
		JButton btnRenombrar = new JButton("Renombrar");
		btnRenombrar.addActionListener(new renombrarEtiqueta());
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		JSeparator separator_2 = new JSeparator();
		GroupLayout gl_panelEtiquetas = new GroupLayout(panelEtiquetas);
		gl_panelEtiquetas.setHorizontalGroup(
			gl_panelEtiquetas.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelEtiquetas.createSequentialGroup()
					.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelEtiquetas.createSequentialGroup()
							.addGap(12)
							.addComponent(lblCrearEtiqueta))
						.addGroup(gl_panelEtiquetas.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEliminarEtiqueta)
								.addComponent(lblAsignarEtiqueta)))
						.addGroup(gl_panelEtiquetas.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRenombrarEtiqueta))
						.addGroup(gl_panelEtiquetas.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNuevoNombre)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.LEADING, false)
						.addComponent(newNameLabel, 0, 0, Short.MAX_VALUE)
						.addComponent(renameLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(asignLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(deleteLabel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(crearEtiquetaInput, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
					.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.LEADING)
						.addComponent(crearButton, Alignment.TRAILING)
						.addComponent(btnEliminar, Alignment.TRAILING)
						.addComponent(btnAsignar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRenombrar, Alignment.TRAILING))
					.addContainerGap())
				.addGroup(gl_panelEtiquetas.createSequentialGroup()
					.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelEtiquetas.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panelEtiquetas.createSequentialGroup()
								.addContainerGap()
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelEtiquetas.createSequentialGroup()
								.addGap(50)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))))
					.addGap(24))
		);
		gl_panelEtiquetas.setVerticalGroup(
			gl_panelEtiquetas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEtiquetas.createSequentialGroup()
					.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelEtiquetas.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCrearEtiqueta)
								.addComponent(crearEtiquetaInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_panelEtiquetas.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(crearButton)
							.addGap(6)))
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelEtiquetas.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEliminarEtiqueta)
								.addComponent(deleteLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_panelEtiquetas.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
							.addComponent(btnEliminar)
							.addGap(6)))
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAsignarEtiqueta)
						.addComponent(asignLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAsignar))
					.addGap(8)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelEtiquetas.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRenombrarEtiqueta)
								.addComponent(renameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(gl_panelEtiquetas.createParallelGroup(Alignment.BASELINE)
								.addComponent(newNameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNuevoNombre))
							.addContainerGap(22, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelEtiquetas.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRenombrar)
							.addGap(34))))
		);
		gl_panelEtiquetas.linkSize(SwingConstants.VERTICAL, new Component[] {crearEtiquetaInput, newNameLabel});
		gl_panelEtiquetas.linkSize(SwingConstants.VERTICAL, new Component[] {crearButton, btnEliminar, btnAsignar, btnRenombrar});
		gl_panelEtiquetas.linkSize(SwingConstants.VERTICAL, new Component[] {deleteLabel, asignLabel});
		panelEtiquetas.setLayout(gl_panelEtiquetas);
		
		JLabel lblNewLabel = new JLabel("Contenido:");
		
		JLabel lblNewLabel_1 = new JLabel("Contexto:");
		
		JLabel lblNi = new JLabel("Niñ@:");
		
		JLabel lblFechahora = new JLabel("Fecha/Hora");
		
		JLabel lblDesde = new JLabel("desde:");
		
		JLabel lblHasta = new JLabel("hasta:");
		
		JLabel lblEtiqueta = new JLabel("Etiqueta:");
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new Filtrar());
	
		categorias.setModel(categoriasSelect("Pista"));
		categorias.setSelectedIndex(0);
		
		contextos.setModel(contextosSelect());
		contextos.addItemListener(new ItemListener(){
            
            public void itemStateChanged(ItemEvent e) {
                if ( e.getStateChange() == ItemEvent.SELECTED ){
                    Object item = e.getItem();
                    categorias.setModel(categoriasSelect(item.toString()));
                }
            }

         });
		contextos.setSelectedIndex(0);

		childs.setModel(childsSelect());
		childs.setSelectedIndex(0);
		
		labels.setModel(labelsSelect());
		labels.setSelectedIndex(0);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.HOUR_OF_DAY);
		SpinnerDateModel model2 = new SpinnerDateModel();
		model2.setCalendarField(Calendar.HOUR_OF_DAY);

		date1.setModel(model);
		date1.setEditor(new JSpinner.DateEditor(date1, "dd/MM/yyyy hh:mm"));
		date2.setModel(model2);
		date2.setEditor(new JSpinner.DateEditor(date2, "dd/MM/yyyy hh:mm"));
		
		
		JButton btnDesfiltrar = new JButton("Desfiltrar");
		btnDesfiltrar.addActionListener(new Desfiltrar());
		
		GroupLayout gl_panelFiltros = new GroupLayout(panelFiltros);
		gl_panelFiltros.setHorizontalGroup(
			gl_panelFiltros.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFiltros.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFiltros.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFiltros.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panelFiltros.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelFiltros.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panelFiltros.createSequentialGroup()
										.addComponent(lblDesde)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(date1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblHasta)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(date2, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panelFiltros.createSequentialGroup()
										.addComponent(contenidos, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 224, Short.MAX_VALUE))))
							.addComponent(lblFechahora))
						.addGroup(gl_panelFiltros.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panelFiltros.createSequentialGroup()
								.addComponent(lblEtiqueta)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(labels, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDesfiltrar))
							.addGroup(Alignment.LEADING, gl_panelFiltros.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnFiltrar, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelFiltros.createSequentialGroup()
									.addGroup(gl_panelFiltros.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panelFiltros.createSequentialGroup()
											.addComponent(lblNi, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(childs, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panelFiltros.createSequentialGroup()
											.addComponent(lblNewLabel_1)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(contextos, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)))
									.addGap(27)
									.addComponent(lblCategoria, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(categorias, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_panelFiltros.setVerticalGroup(
			gl_panelFiltros.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFiltros.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelFiltros.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(contenidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panelFiltros.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFiltros.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addComponent(categorias, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelFiltros.createSequentialGroup()
							.addGap(2)
							.addComponent(contextos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFiltros.createSequentialGroup()
							.addGap(5)
							.addComponent(lblCategoria)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelFiltros.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNi, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(childs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelFiltros.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechahora)
						.addComponent(date2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHasta)
						.addComponent(date1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDesde))
					.addGap(29)
					.addGroup(gl_panelFiltros.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEtiqueta)
						.addComponent(labels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDesfiltrar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnFiltrar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panelFiltros.setLayout(gl_panelFiltros);
		contentPane.setLayout(gl_contentPane);
		
		//factory.desconectar();
	}
	
	private void initialize(){
		factory.conectar();
		configurar();
		Loader loader = new Loader();
		loader.load();
	}
	
	private DefaultComboBoxModel<String> categoriasSelect(String contexto){
       DefaultComboBoxModel<String>  listmodel = new DefaultComboBoxModel<String> ();
       listmodel.addElement("");
       for(String cate : factory.getCategoryDAO().selectAllStrings(contexto)){
            listmodel.addElement(cate);                
       }
       return listmodel;
    }
	
	public DefaultComboBoxModel<String> contextosSelect(){
       DefaultComboBoxModel<String>  listmodel = new DefaultComboBoxModel<String> ();  
       listmodel.addElement("");
       for(String c : factory.getContextDAO().selectAllStrings()){
            listmodel.addElement(c);                
       }
       return listmodel;
    }
	
	public DefaultComboBoxModel<String> contenidosSelect(){
	       DefaultComboBoxModel<String>  listmodel = new DefaultComboBoxModel<String> ();
	       listmodel.addElement("");
	       for(String c : factory.getNotifyDAO().recuperarContenidos()){
	            listmodel.addElement(c);                
	       }
	       return listmodel;
	    }
	
	public DefaultComboBoxModel<String> childsSelect(){
	       DefaultComboBoxModel<String>  listmodel = new DefaultComboBoxModel<String> (); 
	       listmodel.addElement("");
	       for(String c : factory.getChildDAO().recuperarChilds()){
	            listmodel.addElement(c);                
	       }
	       return listmodel;
	    }
	
	public static DefaultComboBoxModel<String> labelsSelect(){
	       DefaultComboBoxModel<String>  listmodel = new DefaultComboBoxModel<String> ();  
	       listmodel.addElement("");
	       for(String c : factory.getLabelDAO().recuperarLabels()){
	            listmodel.addElement(c);                
	       }
	       return listmodel;
	    }
	
	private class MyTableModel extends AbstractTableModel {
		private String[] columnNames = {"Fecha/Hora", "Contenido", "Contexto", "Categoria", "Niñ@", "Etiquetas"};
		private Object[][] data = new Object[notifies.size()][6];

		public MyTableModel(){
			for(int i=0; i<notifies.size(); i++){
				data[i][0] = notifies.get(i).getFechaHoraEnvio();
				data[i][1] = notifies.get(i).getContenido();
				data[i][2] = factory.getContextDAO().getDescripcion(notifies.get(i).getIdContext());
				data[i][3] = factory.getCategoryDAO().getDescripcion(notifies.get(i).getIdCategory());
				data[i][4] = factory.getChildDAO().getNombre(notifies.get(i).getIdChild());
				data[i][5] = "";
				
				for(String desc : factory.getNotifyDAO().recuperarEtiquetas(notifies.get(i).getId())){
					data[i][5] = data[i][5] + desc + ",";
				}
				int longi = data[i][5].toString().length();
				if(longi > 0){
					data[i][5] = data[i][5].toString().substring(0, (longi - 1));
				}
			}
		}
		public String getColumnName(int col) {
		       return columnNames[col];
		}
			 
		public int getColumnCount() {
				// TODO Auto-generated method stub
	          return columnNames.length;
		}
		public int getRowCount() {
				// TODO Auto-generated method stub
	        return data.length;
		}
		public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
	            return data[rowIndex][columnIndex];
		}
	};
		
	private void crearTabla(MyTableModel tabla, TableRowSorter<MyTableModel> sorter){
		table = new JTable(tabla);
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(1).setResizable(true);
		table.getColumnModel().getColumn(2).setResizable(true);
		table.getColumnModel().getColumn(3).setResizable(true);
		table.getColumnModel().getColumn(4).setResizable(true);
		table.getColumnModel().getColumn(5).setResizable(true);
		table.setRowSorter(sorter);
		table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		notificaciones.setViewportView(table);
		table.repaint();
	}

	private class crearEtiqueta implements ActionListener{
		
		public crearEtiqueta(){
		}
		
		
		public void actionPerformed(ActionEvent e) { 
			if (e.getSource() instanceof JButton){ 
				if(factory.getLabelDAO().addLabel(crearEtiquetaInput.getText())){
					JOptionPane.showMessageDialog(null, "Etiqueta Agragada");
				}
			} 
		}
	}
	
	private class borrarEtiqueta implements ActionListener{
		
		public borrarEtiqueta(){
		}
		
		
		public void actionPerformed(ActionEvent e) { 
			if (e.getSource() instanceof JButton){ 
				if(factory.getLabelDAO().deleteLabel(deleteLabel.getSelectedItem().toString())){
					JOptionPane.showMessageDialog(null, "Etiqueta Borrada");
				}
			} 
		}
	}
	
	private class renombrarEtiqueta implements ActionListener{
		
		public renombrarEtiqueta(){
		}
		
		
		public void actionPerformed(ActionEvent e) { 
			if (e.getSource() instanceof JButton){ 
				if(factory.getLabelDAO().editLabel(renameLabel.getSelectedItem().toString(),newNameLabel.getText())){
					JOptionPane.showMessageDialog(null, "Etiqueta Modificada");
				}
			} 
		}
	}
	
	private class Filtrar implements ActionListener{
				
		public void actionPerformed(ActionEvent e) { 
			if (e.getSource() instanceof JButton){ 
				List<String> lista = new LinkedList<String>();
				JSpinner.DateEditor de;
				RowFilter<MyTableModel, Object> rf = null;
				RowFilter<MyTableModel, Object> filtro = null;
				LinkedList<RowFilter<MyTableModel, Object>> filtros = new LinkedList<RowFilter<MyTableModel, Object>>();
				try{
					if(contenidos.getSelectedItem().toString() != ""){
						lista.add(contenidos.getSelectedItem().toString());
					}
					if(contextos.getSelectedItem().toString() != ""){
						lista.add(contextos.getSelectedItem().toString());
					}
					if(categorias.getSelectedItem().toString() != ""){
						lista.add(categorias.getSelectedItem().toString());
					}
					if(childs.getSelectedItem().toString() != ""){
						lista.add(childs.getSelectedItem().toString());
					}
					if(labels.getSelectedItem().toString() != ""){
						lista.add(labels.getSelectedItem().toString());
					}
					if(date1.getValue().toString() != ""){
						de = new JSpinner.DateEditor(date1, "dd/MM/yyyy hh:mm");
						lista.add(de.getFormat().format(date1.getValue()));
					}
					if(date2.getValue().toString() != ""){
						de = new JSpinner.DateEditor(date2, "dd/MM/yyyy hh:mm");
						lista.add(de.getFormat().format(date2.getValue()));
					}
					for(String i: lista){
						rf = RowFilter.regexFilter(i);
						filtros.add(rf);
					}
						
					filtro = RowFilter.orFilter(filtros);
				}
				catch (java.util.regex.PatternSyntaxException e1){
					System.out.println("Parametro incorrecto");
				}
				
				finally{
			        sorter.setRowFilter(filtro);
				}
			} 
		}
	}
	

	private class Desfiltrar implements ActionListener{
		
		public void actionPerformed(ActionEvent e) { 
			RowFilter<MyTableModel, Object> rf = null;
	        sorter.setRowFilter(rf);

			} 
		}

public static void repintarComboBoxes(){
		deleteLabel.setModel(labelsSelect());
		deleteLabel.repaint();
		asignLabel.setModel(labelsSelect());
		asignLabel.repaint();
		labels.setModel(labelsSelect());
		labels.repaint();
		renameLabel.setModel(labelsSelect());
		renameLabel.repaint();
}
	
private void configurar(){
		this.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				    int confirmed = JOptionPane.showConfirmDialog(null, 
				        "Seguro que desea salir?", "Cerrar programa",
				        JOptionPane.YES_NO_OPTION);

				    if (confirmed == JOptionPane.YES_OPTION) {
				    	factory.getNotifyDAO().truncate();
				    	factory.desconectar();
				    	dispose();
				    	System.exit(0);
				    }
				  }
				});
	}
}
