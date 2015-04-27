package iastate.se329.MP3TaggerGUI;

import iastate.se329.MP3Tagger.MP3Tagger;
import iastate.se329.MP3Tagger.MP3TaggerController;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MP3TaggerGUI extends JFrame
{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_fileStructureInput;
    private JTextField txt_sourceDir;
    private JTextField txt_destinationDir;
    private JFileChooser fileChooser;
    private MP3TaggerController tagger;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
    	
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                try
                {
                    MP3TaggerGUI frame = new MP3TaggerGUI();
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MP3TaggerGUI()
    {
        // Initialization
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 449, 480);
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        
        // JMenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);
        
        JMenuItem mntmClose = new JMenuItem("Close");
        mnFile.add(mntmClose);
        
        mntmClose.addActionListener(new ActionListener() {
        	//listens to enter on the text box, updates the tagger
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
        });

        JMenu mnEdit = new JMenu("Edit");
        menuBar.add(mnEdit);

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);
        
        JMenuItem mntmAbout = new JMenuItem("About");
        mnHelp.add(mntmAbout);
        
        mntmAbout.addActionListener(new ActionListener() {
        	//listens to enter on the text box, updates the tagger
			@Override
			public void actionPerformed(ActionEvent e) {
				String version = "Music Tagger Application\n\n"
						+ "Version: 3.0\n"
						+ "Release Date: 4-30-15";
				
				JOptionPane.showMessageDialog(null, version, "About", JOptionPane.DEFAULT_OPTION);
			}
        });
        tagger = new MP3TaggerController();
        
        // Initialize contentPane (the JPanel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
       

        // Copy Mode Check
        JCheckBox copyCheck = new JCheckBox("Copy Files");
        copyCheck.setSelected(true);
        copyCheck.setBounds(10, 260, 150, 21);
        contentPane.add(copyCheck);
        
        // AlbumArt Update Mode Check
        JCheckBox artCheck = new JCheckBox("Embed Art");
        artCheck.setSelected(false);
        artCheck.setBounds(10, 280, 150, 21);
        contentPane.add(artCheck);
        
     	// Metadata Update Mode Check
        JCheckBox metadataCheck = new JCheckBox("Update Metadata");
        metadataCheck.setSelected(false);
        metadataCheck.setBounds(10, 300, 150, 21);
        contentPane.add(metadataCheck);
        
        // Options Label
        JLabel optionsLabel = new JLabel("Options");
        optionsLabel.setBounds(10, 240, 100, 21);
        contentPane.add(optionsLabel);
        
        
        //Progress Bar
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(10, 350, 200, 20);
        //progressBar.setIndeterminate(true);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        contentPane.add(progressBar);
        
        
        // File structure input
        JLabel lbl_fileStructureInput = new JLabel("File Structure");
        lbl_fileStructureInput.setToolTipText("Input the desired file structure to be created");
        lbl_fileStructureInput.setBounds(10, 79, 100, 21);
        contentPane.add(lbl_fileStructureInput);

        txt_fileStructureInput = new JTextField();
        txt_fileStructureInput.setToolTipText("Any item followed by a '/' or '\\' is a folder name. The file name is designated by the last option.  Valid options include: %A (Artist), %a (Album), %T (Track Title), %t (TrackNumber, and %Y (Year)");
        txt_fileStructureInput.setText("%A\\%a\\%T.mp3");
        txt_fileStructureInput.setBounds(10, 99, 200, 21);
        txt_fileStructureInput.addActionListener(new ActionListener() {
        	//listens to enter on the text box, updates the tagger
			@Override
			public void actionPerformed(ActionEvent e) {
				//tagger.setFileStructurePattern(txt_fileStructureInput.getText());
			}
        });
        
        //String username = System.getProperty("user.name");
        contentPane.add(txt_fileStructureInput);
        txt_fileStructureInput.setColumns(10);

        // Source directory input
        JLabel lbl_sourceDir = new JLabel("Source Directory");
        lbl_sourceDir.setBounds(10, 131, 100, 21);
        contentPane.add(lbl_sourceDir);

        txt_sourceDir = new JTextField();
       // txt_sourceDir.setText("C:\\Users\\UserName\\CurMusic");
        txt_sourceDir.setText("Choose the Source Directory");
        txt_sourceDir.setBounds(10, 150, 200, 21);
        contentPane.add(txt_sourceDir);
        txt_sourceDir.setColumns(10);

        // Destination directory input
        JLabel lbl_destinationDir = new JLabel("Destination Directory");
        lbl_destinationDir.setBounds(10, 182, 200, 21);
        contentPane.add(lbl_destinationDir);

        txt_destinationDir = new JTextField();
        //txt_destinationDir.setText("C:\\Users\\UserName\\TaggedMusic");
        txt_destinationDir.setText("Choose the Destination Directory");
        txt_destinationDir.setColumns(10);
        txt_destinationDir.setBounds(10, 202, 200, 21);
        contentPane.add(txt_destinationDir);

        // File browser buttons
        JButton btn_destinationFileBrowser = new JButton("Browse");
        btn_destinationFileBrowser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int returnVal = fileChooser.showOpenDialog(MP3TaggerGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    txt_destinationDir.setText(file.getAbsolutePath());
                    //tagger.setDestinationFolderPath(txt_destinationDir.getText());
                }
            }
        });
        btn_destinationFileBrowser.setBounds(220, 201, 89, 23);
        contentPane.add(btn_destinationFileBrowser);

        JButton btn_sourcefilebrowser = new JButton("Browse");
        btn_sourcefilebrowser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int returnVal = fileChooser.showOpenDialog(MP3TaggerGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    txt_sourceDir.setText(file.getAbsolutePath());
                    //tagger.setSourceFolderPath(txt_sourceDir.getText());
                }
            }
        });
        btn_sourcefilebrowser.setBounds(220, 149, 89, 23);
        contentPane.add(btn_sourcefilebrowser);
        
        
        // Start and stop buttons
        JButton btn_start = new JButton("Start");
        btn_start.setActionCommand("start");
        btn_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tagger.setDestinationFolderPath(txt_destinationDir.getText());
            	tagger.setSourceFolderPath(txt_sourceDir.getText());
            	tagger.setFileStructurePattern(txt_fileStructureInput.getText());
            	tagger.setCopyMode(copyCheck.isSelected());
            	tagger.setMetadataUpdate(metadataCheck.isSelected());
            	if(tagger.getReady())
            	{
            		progressBar.setIndeterminate(true);
            		
            		
            		//tagger.addPropertyChangeListener("start");
            		tagger.execute();
            		//progressBar.setIndeterminate(false);
            	}
            }
            
           
        });
        
        
        btn_start.setBounds(10, 380, 89, 23);
        contentPane.add(btn_start);

        JButton btn_stop = new JButton("Stop");
        btn_stop.setBounds(109, 380, 89, 23);
        contentPane.add(btn_stop);
        btn_stop.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		progressBar.setIndeterminate(false);
        	}
        });
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		System.exit(0);
        	}
        });
        btnExit.setBounds(335, 380, 89, 23);
        contentPane.add(btnExit);
        
        JLabel lblMusicTagger = new JLabel("Music Tagger");
        lblMusicTagger.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lblMusicTagger.setBounds(84, 11, 300, 49);
        
        contentPane.add(lblMusicTagger);
    }
}
