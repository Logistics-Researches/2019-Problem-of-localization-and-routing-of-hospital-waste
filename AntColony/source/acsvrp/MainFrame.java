package acsvrp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.FormatStyle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import acsvrp.tools.Dbg;
import acsvrp.tools.Def;
import acsvrp.ui.AFile;
import acsvrp.ui.VrpFilter;


/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainFrame extends JFrame {

	{
		//Set Look & Feel
		try {
//			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
			MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
			javax.swing.UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 2269971701250845501L;

	static final Logger logger = Logger.getLogger(MainFrame.class);

	static public JLabel statusBar;

	AGraph agraph;
	GraphModel model;
	GraphLayoutCache view;
	BorderLayout agraphLayout;
	
	ShowPheromon showPheromon;
	Parameters parameters;
	private boolean startACO = false;

	private String urlPrefix = " http://www.poredi.com/def/";

	private JMenuItem jMenuItemOpen;
	private JMenuItem jMenuItemSave;
	private JMenuItem jMenuItemAbout;
	private JMenuItem jMenuItemHelp;
	private JMenu jMenuHelp;
	private JMenuItem jMenuItemStart;
	private JMenuItem jMenuItemInitPara;
	private JMenu jMenuTools;
	private JCheckBoxMenuItem jCheckBoxMenuItemShowPheromon;
	private JMenu jMenuView;
	private JMenuItem jMenuItemExit;
	private JSeparator jSeparatorFile;
	private JMenuItem jMenuItemSaveAs;
	private JMenuItem jMenuItemNew;
	private JMenu jMenu1;
	private JMenuBar jMenuBar1;
	private JScrollPane jScrollPane;
	private JButton jButtonOpen;
	static public 	JButton jButtonStart;
	private JButton jButtonAdd;
	private JButton jButtonSave;
	private JMenuItem jMenuItemLoadUrl;
	private JMenuItem jMenuItemAn53k7;
	private JMenuItem jMenuItemPn16k8;
	private JMenuItem jMenuItemAn37k6;
	private JMenu jMenuLoadUrl;
	private JButton jButtonNew;
	private JToolBar jToolBar1;
	private JComboBox<String> costTypeList;

	public MainFrame() {

		//System.setProperty("sun.java2d.d3d", "false");
		PropertyConfigurator.configure("log4j.properties");
		
//		model = new DefaultGraphModel();
//		view = new GraphLayoutCache(model,new DefaultCellViewFactory(),true);

		this.setSize(1320, 720);
		//this.setLocation(new java.awt.Point(0,0));
		
	this.setLocationRelativeTo(null); // center on screen
		this.setTitle("Vechicle routing system using Ant Colony System optimization - Nour Mestouri");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		{
//			agraph = new AGraph(model, view);
			initGraph();
//			jScrollPane = new JScrollPane(agraph);
//			getContentPane().add(jScrollPane, BorderLayout.CENTER);
//			jScrollPane.setPreferredSize(new java.awt.Dimension(712, 447));
//			jScrollPane.setSize(712, 468);
		}
		statusBar = new JLabel("Prêt");
        statusBar.setForeground(Color.GREEN);
		add(statusBar, BorderLayout.SOUTH);
		statusBar.setText(" Ctrl-P pour plus d'informations sur Pheromon");

		{
			jToolBar1 = new JToolBar();
            getContentPane().setBackground(Constants.BG_COLOR);
			getContentPane().add(jToolBar1, BorderLayout.NORTH);
			jToolBar1.setFloatable(false);
			jToolBar1.setOpaque(false);
			{
				jButtonNew = new JButton();
                jButtonNew.setBackground(Constants.BG_COLOR);
				BorderLayout jButtonNewLayout = new BorderLayout();
				jButtonNew.setLayout(jButtonNewLayout);
				jToolBar1.add(jButtonNew);
				jButtonNew.setToolTipText("Ouvrir un nouveau fichier");
				jButtonNew.setHorizontalAlignment(SwingConstants.LEFT);
				jButtonNew.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/Application-edit.gif")));
				jButtonNew.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						// new graph
						//TODO NOW
						getContentPane().remove(jScrollPane);
						//logger.debug("New");
						jScrollPane.remove(agraph);
						initGraph();
						statusBar.setText("Nouveau graph créé.");
						getContentPane().repaint();
					}
				});
			}
			{
				jButtonOpen = new JButton();
                jButtonOpen.setBackground(Constants.BG_COLOR);
				BorderLayout jButtonOpenLayout = new BorderLayout();
				jButtonOpen.setLayout(jButtonOpenLayout);
				jToolBar1.add(jButtonOpen);
				jButtonOpen.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/Folder.gif")));
				jButtonOpen.setToolTipText("Ouvrir un fichier");
				jButtonOpen.setHorizontalAlignment(SwingConstants.LEFT);
				jButtonOpen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						OpenActionPerformed(evt);
					}
				});
			}
			{
				jButtonSave = new JButton();
                jButtonSave.setBackground(Constants.BG_COLOR);
				BorderLayout jButtonSaveLayout = new BorderLayout();
				jButtonSave.setLayout(jButtonSaveLayout);
				jToolBar1.add(jButtonSave);
				jButtonSave.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/Save.gif")));
				jButtonSave.setHorizontalAlignment(SwingConstants.LEFT);
                jButtonSave.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        //TODO save
                    }
                });
                
			}
			{
				jToolBar1.addSeparator();
			}			
			{
				jButtonAdd = new JButton();
                jButtonAdd.setBackground(Constants.BG_COLOR);
				BorderLayout jButtonAddLayout = new BorderLayout();
				jButtonAdd.setLayout(jButtonAddLayout);
				jToolBar1.add(jButtonAdd);
				jButtonAdd.setToolTipText("Add node");
				jButtonAdd.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/Doc-Add.gif")));
				jButtonAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButtonAddActionPerformed(evt);
					}
				});
			}
			{
				jToolBar1.addSeparator();
			}						
			{
				jButtonStart = new JButton();
                jButtonStart.setBackground(Constants.BG_COLOR);
                jButtonStart.setForeground(Constants.FG_COLOR);
				BorderLayout jButtonStartLayout = new BorderLayout();
				jButtonStart.setLayout(jButtonStartLayout);
				jToolBar1.add(jButtonStart);
				jButtonStart.setText("Start");
				jButtonStart.setEnabled(false);
				jButtonStart.setToolTipText("Start process");
				jButtonStart.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/objects_017.gif")));
				jButtonStart.setPreferredSize(new java.awt.Dimension(80, 28));
				jButtonStart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						StartActionPerformed(evt);
					}
				});
			}
			{
				jToolBar1.addSeparator();
			}	
			{
                JLabel costType = new JLabel("Type de coût: ");
                costType.setForeground(Constants.FG_COLOR);
				jToolBar1.add(costType);
				final String[] costTypes = { "Distance", "Temps"};

				costTypeList = new JComboBox<String>(costTypes);
                costTypeList.setBackground(Constants.BG_COLOR);
                costTypeList.setForeground(Constants.FG_COLOR);
				costTypeList.setSelectedIndex(0);
                costTypeList.setMaximumSize(new Dimension(120, 25));
				//costTypeList.setPreferredSize(new java.awt.Dimension(80, 28));
				costTypeList.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						JComboBox<String> cb = (JComboBox<String>) arg0.getSource();
				        String costType = (String)cb.getSelectedItem();
				        logger.debug("Cost type changed to "+costType);
				        if (costType.equals(costTypes[0])) {
				        	AntColony.costType = Cost.Type.TYPE_DESTINATION;
				        } else {
				        	AntColony.costType = Cost.Type.TYPE_TIME;
				        }
					}
				});
				jToolBar1.add(costTypeList);
			}
			{
//				jToolBar1.addSeparator();
//				jToolBar1.add(new JLabel(". "));
			}	
		}		

		{
			jMenuBar1 = new JMenuBar();
			setJMenuBar(jMenuBar1);
			{
                jMenuBar1.setBackground(Constants.BG_COLOR);
				jMenu1 = new JMenu();
                jMenu1.setBackground(Constants.BG_COLOR);
                jMenu1.setForeground(Constants.FG_COLOR);
				jMenuBar1.add(jMenu1);
				jMenu1.setText("Fichier");
				jMenu1.setMnemonic('F');
				{
					jMenuItemNew = new JMenuItem();
                    jMenuItemNew.setBackground(Constants.BG_COLOR);
                    jMenuItemNew.setForeground(Constants.FG_COLOR);
					jMenu1.add(jMenuItemNew);
					jMenuItemNew.setText("Nouveau");
				}
				{
					jMenuItemOpen = new JMenuItem();
                    jMenuItemOpen.setBackground(Constants.BG_COLOR);
                    jMenuItemOpen.setForeground(Constants.FG_COLOR);
					jMenu1.add(jMenuItemOpen);
					jMenuItemOpen.setText("Ouvrir un fichier");
					jMenuItemOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
					jMenuItemOpen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							OpenActionPerformed(evt);
						}
					});
				}
				{
					jMenuItemSave = new JMenuItem();
                    jMenuItemSave.setBackground(Constants.BG_COLOR);
                    jMenuItemSave.setForeground(Constants.FG_COLOR);
					jMenu1.add(jMenuItemSave);
					jMenuItemSave.setText("Enregistrer");
				}
				{
					jMenuItemSaveAs = new JMenuItem();
                    jMenuItemSaveAs.setBackground(Constants.BG_COLOR);
                    jMenuItemSaveAs.setForeground(Constants.FG_COLOR);
					jMenu1.add(jMenuItemSaveAs);
					jMenuItemSaveAs.setText("Enregistrer sous");
				}
				{
					jMenuLoadUrl = new JMenu();
                    jMenuLoadUrl.setBackground(Constants.BG_COLOR);
                    jMenuLoadUrl.setForeground(Constants.FG_COLOR);
//					jMenu1.add(jMenuLoadUrl);
					jMenuLoadUrl.setText("Charger une URL");
					{
						jMenuItemPn16k8 = new JMenuItem();
                        jMenuItemPn16k8.setBackground(Constants.BG_COLOR);
                        jMenuItemPn16k8.setForeground(Constants.FG_COLOR);
						jMenuLoadUrl.add(jMenuItemPn16k8);
						jMenuItemPn16k8.setText("mednine.vrp");
						jMenuItemPn16k8.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								AntColony.FILE_NAME = urlPrefix+jMenuItemPn16k8.getText();
								createAnodes(AntColony.FILE_NAME);
							}
						});
					}
					{
						jMenuItemAn37k6 = new JMenuItem();
                        jMenuItemAn37k6.setBackground(Constants.BG_COLOR);
                        jMenuItemAn37k6.setForeground(Constants.FG_COLOR);
						jMenuLoadUrl.add(jMenuItemAn37k6);
						jMenuItemAn37k6.setText("mednine.vrp");
						jMenuItemAn37k6.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								AntColony.FILE_NAME = urlPrefix+jMenuItemAn37k6.getText();
								createAnodes(AntColony.FILE_NAME);
							}
						});
					}
					{
						jMenuItemAn53k7 = new JMenuItem();
                        jMenuItemAn53k7.setBackground(Constants.BG_COLOR);
                        jMenuItemAn53k7.setForeground(Constants.FG_COLOR);
						jMenuLoadUrl.add(jMenuItemAn53k7);
						jMenuItemAn53k7.setText("mednine.vrp");
						jMenuItemAn53k7.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								AntColony.FILE_NAME = urlPrefix+jMenuItemAn53k7.getText();
								createAnodes(AntColony.FILE_NAME);
							}
						});
					}
					{
						jMenuItemLoadUrl = new JMenuItem();
                        jMenuItemLoadUrl.setBackground(Constants.BG_COLOR);
                        jMenuItemLoadUrl.setForeground(Constants.FG_COLOR);
						jMenuLoadUrl.add(jMenuItemLoadUrl);
						jMenuItemLoadUrl.setText("Charger à partir URL...");
						jMenuItemLoadUrl.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								String s = "http://ipanasiuk.webs.com/def/P-n19-k2.vrp";
								s = (String)JOptionPane.showInputDialog(null,"Choisir un fichier VRP","Entrer URL:",JOptionPane.PLAIN_MESSAGE, null,null,s);
								createAnodes(s);
							}
						});
					}
				}
				{
					jSeparatorFile = new JSeparator();
                    jSeparatorFile.setBackground(Constants.SEPARATOR_COLOR);
					jMenu1.add(jSeparatorFile);
				}
				{
					jMenuItemExit = new JMenuItem();
                    jMenuItemExit.setBackground(Constants.BG_COLOR);
                    jMenuItemExit.setForeground(Constants.FG_COLOR);
					jMenu1.add(jMenuItemExit);
					jMenuItemExit.setText("Quitter");
					jMenuItemExit.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
					jMenuItemExit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//logger.debug("jMenuItemExit.actionPerformed, event="+evt);
							System.exit(0);
						}
					});
				}
			}
			{
				jMenuView = new JMenu();
                jMenuView.setBackground(Constants.BG_COLOR);
                jMenuView.setForeground(Constants.FG_COLOR);
				jMenuBar1.add(jMenuView);
				jMenuView.setText("Affichage");
				jMenuView.setMnemonic('V');
				{
					jCheckBoxMenuItemShowPheromon = new JCheckBoxMenuItem();
                    jCheckBoxMenuItemShowPheromon.setBackground(Constants.BG_COLOR);
                    jCheckBoxMenuItemShowPheromon.setForeground(Constants.FG_COLOR);
					jMenuView.add(jCheckBoxMenuItemShowPheromon);
					jCheckBoxMenuItemShowPheromon.setText("Afficher Pheromone");
					jCheckBoxMenuItemShowPheromon.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
					jCheckBoxMenuItemShowPheromon
					.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (AntColony.DIPSLAY_LEVEL > 0) {
								if (!showPheromon.isVisible()) {
									showPheromon.setVisible(true);
								} else {
									showPheromon.setVisible(false);
								}
							}
						}
					});
				}
			}
			{
				jMenuTools = new JMenu();
                jMenuTools.setBackground(Constants.BG_COLOR);
                jMenuTools.setForeground(Constants.FG_COLOR);
				jMenuBar1.add(jMenuTools);
				jMenuTools.setText("Outils");
				jMenuTools.setMnemonic('U');
				{
					jMenuItemInitPara = new JMenuItem();
                    jMenuItemInitPara.setBackground(Constants.BG_COLOR);
                    jMenuItemInitPara.setForeground(Constants.FG_COLOR);
					jMenuTools.add(jMenuItemInitPara);
					jMenuItemInitPara.setText("Init Paramètres");
					jMenuItemInitPara.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
					jMenuItemInitPara.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							parameters.setVisible(true);
						}
					});
				}
				{
					jMenuItemStart = new JMenuItem();
                    jMenuItemStart.setBackground(Constants.BG_COLOR);
                    jMenuItemStart.setForeground(Constants.FG_COLOR);
					jMenuTools.add(jMenuItemStart);
					jMenuItemStart.setText("Démarrer");
					jMenuItemStart.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
					jMenuItemStart.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							StartActionPerformed(evt);
						}
					});
				}
			}
			{
				jMenuHelp = new JMenu();
                jMenuHelp.setBackground(Constants.BG_COLOR);
                jMenuHelp.setForeground(Constants.FG_COLOR);
//				jMenuBar1.add(jMenuHelp);
				jMenuHelp.setText("Aide");
				jMenuHelp.setMnemonic('H');
				{
					jMenuItemHelp = new JMenuItem();
                    jMenuItemHelp.setBackground(Constants.BG_COLOR);
                    jMenuItemHelp.setForeground(Constants.FG_COLOR);
					jMenuHelp.add(jMenuItemHelp);
					jMenuItemHelp.setText("Aide");
				}
				{
					jMenuItemAbout = new JMenuItem();
                    jMenuItemAbout.setBackground(Constants.BG_COLOR);
                    jMenuItemAbout.setForeground(Constants.FG_COLOR);
					jMenuHelp.add(jMenuItemAbout);
					jMenuItemAbout.setText("A propos");
				}
			}
		}
		startACO = AntColony.AUTO_START;

		this.setVisible(true);

		if (AntColony.FILE_NAME.replaceAll(" ", "") != "") { 
			if (startACO) {
				createAnodes(AntColony.FILE_NAME);	
			}
			else {
				String message = "Voulez vous charger '"+AntColony.FILE_NAME+"'";
				int a = JOptionPane.showConfirmDialog(null, message, "Ouvrir", JOptionPane.YES_NO_OPTION);
				
				if (a==0) {
					// Default file could be defined in acsvrp.properties in key FILE_NAME
					statusBar.setText(AntColony.FILE_NAME);
					createAnodes(AntColony.FILE_NAME);
				}
			}
		}


		while (true) {
			if (startACO) {
				logger.info("Started!");
				double best_found = Double.MAX_VALUE;

				/***********************************************************************
				 ***************************** OVO MENJAO *******************************
				 ************************************************************************/

//				int best_found_time = Integer.MAX_VALUE;

				// TESTING PURPOSE
				double minRo = AntColony.RO; 		double maxRo = AntColony.RO;
				double minBeta = AntColony.BETA; 	double maxBeta = AntColony.BETA;				
				if (AntColony.DIPSLAY_LEVEL==0) {
					minRo = 0.1f; maxRo = 0.1f;
					minBeta = 2; maxBeta = 2; 
				}
				for (double ro= minRo; ro <= maxRo ; ro=ro+0.1) {
					for (double beta= minBeta; beta <= maxBeta; beta=beta+0.5) {
						AntColony.RO = ro;
						AntColony.BETA = beta;
						logger.debug("Beta: "+AntColony.BETA+" Ro: "+AntColony.RO);
						for (int testCount = 0; testCount < AntColony.LOOPS; testCount++) {
							Dbg.prn("#" + Def.df0(testCount+1));
							Process pro = new Process(agraph);

							/***********************************************************************
							 * ************************** OVO MENJAO *******************************
							 ************************************************/

//							logger.info("; (" + Def.df2(best_found) + ") ");
							if (best_found > pro.bestAnt.getCost()) {
								best_found = pro.bestAnt.getCost();
								logger.info("["+Def.df2(best_found)+"] New best found !!! ");
							} else if (best_found / pro.bestAnt.getCost() > 1 / 1.05) {
								for (int i=0; i < 100 * best_found / pro.bestAnt.getCost() - 95; i++) {
									//Dbg.prn("*  ");
								}
							}
							/*
							logger.info("; (" + Def.df2(best_found_time) + ") ");
							if (best_found_time > pro.bestAnt.time) {
								best_found_time = pro.bestAnt.time;
								logger.info("["+Def.df2(best_found_time)+"] New best found !!! ");
							} 
							 */
						}
					}  // beta
				} // ro
				startACO = false;
				//Dbg.prnl("Optimisation finished.");
				if (AntColony.DIPSLAY_LEVEL==0) {
					System.exit(0);
				}
				logger.info("Waiting for new start ");
			}
			try {Thread.sleep(500); } catch (InterruptedException ie) {}
		}
	}

	private void createAnodes(String vrpFile) {
		// Ucitaj sve gradove iz fajla FILE_NAME
		agraph.anodes = new ANodes();
		agraph.anodes = AFile.loadNodes(vrpFile);
		statusBar.setText(agraph.anodes.size() + " cities loaded. Drawing all rutes...");
		// Draw a AGraph graph
		if (agraph.draw()) {
			if (AntColony.DIPSLAY_LEVEL > 0) {
				showPheromon = new ShowPheromon(agraph.anodes);
				parameters = new Parameters(MainFrame.this);
			}
			statusBar.setText((agraph.anodes.size()-1) + " cities loaded."); // centrali i (size-1) gradova	
			jButtonStart.setEnabled(true);
		}
	}

	public void start() {
		startACO = true;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainFrame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			logger.error("Couldn't find file: " + path);
			logger.error(MainFrame.class.getResource(path));
			return null;
		}
	}

	private void OpenActionPerformed(ActionEvent evt) {
		statusBar.setText("Import du fichier. Patientez SVP...");
		final JFileChooser fc = new JFileChooser("./def");
		fc.addChoosableFileFilter(new VrpFilter());
		int returnVal = fc.showOpenDialog(MainFrame.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			createAnodes(fc.getSelectedFile().getAbsoluteFile().toString());
		}
	}

	private void jButtonAddActionPerformed(ActionEvent evt) {
		logger.debug("jButtonAdd.actionPerformed, event=" + evt);
		if(agraph.anodes != null)
		{
			agraph.addNewNode(150 * (agraph.anodes.size() + 1), 30 * (agraph.anodes.size() + 1));
		}
		else
		{
			agraph.addNewNode(150, 30);
		}


		// posle ubacivanja novih node-ova instanciranje prozora
		showPheromon = new ShowPheromon(agraph.anodes);
		parameters = new Parameters(MainFrame.this);
	}

	private void StartActionPerformed(ActionEvent evt) {            
		//logger.info("jButtonStart.actionPerformed, event=" + evt);
		startACO = (agraph.anodes.size()>0);
		if (jButtonStart.getText().equals("Pause")) {
			AntColony.STOP = true;
		} else {
			AntColony.STOP = false;
		}
		if (AntColony.STOP) {
			jButtonStart.setText("Démarrer");
			jButtonStart.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/objects_017.gif")));
		} else {
			jButtonStart.setText("Pause");
			jButtonStart.setIcon(new ImageIcon(getClass().getClassLoader().getResource("acsvrp/resources/Error.gif")));
		}
		logger.info("start: "+startACO);
	}
	
	private void initGraph() {
		model = new DefaultGraphModel();
		view = new GraphLayoutCache(model,new DefaultCellViewFactory(),true);
		agraph = new AGraph(model, view);
		agraphLayout = new BorderLayout();
		agraph.setLayout(agraphLayout);
		jScrollPane = new JScrollPane(agraph);
		jScrollPane.setPreferredSize(new java.awt.Dimension(1000, 500));
		jScrollPane.setSize(999,490);
		getContentPane().add(jScrollPane, BorderLayout.CENTER);
	}

}