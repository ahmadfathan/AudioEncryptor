package com.appdev.example;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JTree;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.CardLayout;
import javax.swing.DropMode;
import javax.swing.JTextField;  
import javax.swing.JDialog;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.ByteBuffer;
import java.util.Base64; 

public class Mainwindow extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	static JFileChooser fileChooser;
	static Mainwindow mainFrame; 
	private JTextField tf_message_in;
	
	// Global Variables
	private String audioInPathEnc;  
	private String audioInPathDec;
	private String audioOutPath;
	
	// Method to convert long to byte array
	public byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(x);
		return buffer.array();
	}
	
	// Method to convert byte array to long
	public long bytesToLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(bytes);
		buffer.flip();
		return buffer.getLong();
	}
	
	// Auto-generated Method
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainwindow frame = new Mainwindow();
					mainFrame = frame;
					frame.setVisible(true);
					fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainwindow() {
		setTitle("Audio Enc-Dec Tools");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 544, 531);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Encryption", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Decryption", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(116, Short.MAX_VALUE))
		);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 21, 498, 54);
		panel_5.setToolTipText("");
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Audio File Input", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.add(panel_5);
		
		JLabel lbl_dec_audio_in = new JLabel("No Files Selected");
		
		JButton button = new JButton("Select File");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Prepares file chooser dialog 
				int result = fileChooser.showOpenDialog(contentPane);
				if (result == JFileChooser.APPROVE_OPTION) {
				    // user selects a file
					File selectedFile = fileChooser.getSelectedFile();
					audioInPathDec = selectedFile.getAbsolutePath();
					
					//Makes sure selected audio file format is WAV
					if(audioInPathDec.contains(".wav")) {
						lbl_dec_audio_in.setText(audioInPathDec);		
					}else {
						// Throws a not supported format warning to user
						JOptionPane.showMessageDialog(mainFrame,
								"Please pick a '.wav' file!",
							    "File Format Not Supported",
							    JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		button.setHorizontalAlignment(SwingConstants.LEADING);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 506, Short.MAX_VALUE)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_dec_audio_in, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGap(0, 49, Short.MAX_VALUE)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_dec_audio_in)
						.addComponent(button))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(10, 78, 498, 133);
		panel_4.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lbl_message_out = new JLabel();
		lbl_message_out.setVerticalAlignment(SwingConstants.TOP);
		lbl_message_out.setBounds(10, 21, 478, 101);
		panel_6.add(lbl_message_out);
		
		JButton btn_decrypt = new JButton("Decrypt");
		btn_decrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(audioInPathDec == null || audioInPathDec.isEmpty()) {
					// Throws an error to user
					JOptionPane.showMessageDialog(mainFrame,
							"Error",
						    "Audio Input not Specified",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Decrypting messages
				try {

					// buffer to handle message length
			        long[] bufferIn = new long[64];
			        
			        // Open audio file
					WavFile wavIn = WavFile.openWavFile(new File(audioInPathDec));

					// Get number of channels
					int numChannels = wavIn.getNumChannels();
		        	
					// Read 64 bit LSB from the most beginning frames (as message length) 
					wavIn.readFrames(bufferIn, 64 / numChannels);

					// ==== Convert LSB bit of message length to byte array ==== 
					// Variable to store message length as byte
					byte[] bytes = new byte[8];
			       
		        	for(int i = 0, j = -1; i < 64; i++) {
		        		if( i % 8 == 0) j++;
		        		bytes[j] += (byte)(bufferIn[i] & 1) *  Math.pow(2, i % 8); 
		        	}
		        	// ==== End of the part ====
		        	
		        	// Convert byte array to long
		        	long msgLength = bytesToLong(bytes);
		        	
		        	// buffer to handle encrypted message
			        long[] bufferIn2 = new long[(int)msgLength * 8];
		        	
			        // Read whole message 
			        wavIn.readFrames(bufferIn2, (int)msgLength * 8 / numChannels);
		        	
			        // ==== Convert LSB bit of message to byte array ====
			        // Variable to store message as byte array
			        byte[] msgBytes = new byte[(int)msgLength];
		        	for(int i = 0, j = -1; i < (int)msgLength * 8; i++) {
		        		if( i % 8 == 0) j++;
		        		msgBytes[j] += (byte)(bufferIn2[i] & 1) *  Math.pow(2, i % 8); 
		        	}
		        	// ==== End of the part ====
		        
		        	// ==== Retrieve base64 encoded message as real message ====
		        	// Instantiate string from byte array
		        	String decMsg = new String(msgBytes);
		        	// Decode encoded message
		        	String realMsg = new String(Base64.getDecoder().decode(decMsg));
		        	// Display message to user
		        	lbl_message_out.setText("<html>" + realMsg.replace(" ", " <wbr>") + "</html>");
		        	// ==== End of the part ====
			        	
		        	wavIn.close();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(mainFrame,
							"An Error Occured: " + e.getMessage(),
							"Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_decrypt.setBounds(425, 215, 80, 23);
		panel_4.add(btn_decrypt);
		panel_2.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 76, 506, 55);
		panel_2.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		tf_message_in = new JTextField();
		tf_message_in.setBounds(10, 22, 486, 20);
		panel_1.add(tf_message_in);
		tf_message_in.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 16, 506, 49);
		panel_2.add(panel);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Audio File Input", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setToolTipText("");
		
		JLabel lbl_enc_audio_in = new JLabel("No Files Selected");
		
		JButton btn_select_audio = new JButton("Select File");
		
				btn_select_audio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int result = fileChooser.showOpenDialog(contentPane);
						if (result == JFileChooser.APPROVE_OPTION) {
						    // user selects a file
							File selectedFile = fileChooser.getSelectedFile();
							audioInPathEnc = selectedFile.getAbsolutePath();
							
							if(audioInPathEnc.contains(".wav")) {
								lbl_enc_audio_in.setText(audioInPathEnc);		
							}else {
								JOptionPane.showMessageDialog(mainFrame,
										"Please pick a '.wav' file!",
									    "File Format Not Supported",
									    JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				});
				btn_select_audio.setHorizontalAlignment(SwingConstants.LEADING);
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbl_enc_audio_in, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btn_select_audio, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_enc_audio_in)
								.addComponent(btn_select_audio))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				panel.setLayout(gl_panel);
				
				JPanel panel_3 = new JPanel();
				panel_3.setToolTipText("");
				panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Audio File Output", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_3.setBounds(6, 142, 506, 49);
				panel_2.add(panel_3);
				
				JLabel lbl_save_loc = new JLabel("No Save Location Specified");
				
				JButton btnBrowse = new JButton("Browse");
				btnBrowse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JFrame parentFrame = new JFrame();
						 
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setDialogTitle("Specify a file to save");   
						 
						int userSelection = fileChooser.showSaveDialog(parentFrame);
						 
						if (userSelection == JFileChooser.APPROVE_OPTION) {

							File fileToSave = fileChooser.getSelectedFile();
							audioOutPath = fileToSave.getAbsolutePath();
						    lbl_save_loc.setText("Save as file: " + fileToSave.getAbsolutePath());
						}
					}
				});
				GroupLayout gl_panel_3 = new GroupLayout(panel_3);
				gl_panel_3.setHorizontalGroup(
					gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbl_save_loc, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
				);
				gl_panel_3.setVerticalGroup(
					gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGap(0, 49, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_save_loc)
								.addComponent(btnBrowse))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				panel_3.setLayout(gl_panel_3);
				
				JButton btnNewButton = new JButton("Encypt");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(audioInPathEnc == null || audioInPathEnc.isEmpty()) {
							JOptionPane.showMessageDialog(mainFrame,
									"Audio Input not Specified",
									"Error",
								    JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if(tf_message_in.getText().isEmpty()) {
							JOptionPane.showMessageDialog(mainFrame,
									"Message Could not Be Empty",
									"Error",
								    JOptionPane.ERROR_MESSAGE);
							return;
						}

						if(audioOutPath == null || audioInPathEnc.isEmpty()) {
							JOptionPane.showMessageDialog(mainFrame,
									"Audio Output not Specified",
									"Error",
								    JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						try {
							// Original Message
							String message = tf_message_in.getText();
							// Base64 Encoded Message
							String encodedMessage = Base64.getEncoder().encodeToString(message.getBytes());
							
							// Debug Purpose
							System.out.println("Message: " + message);
					        System.out.println("Encoded: " + encodedMessage);
					         
					        // Get encoded message as byte array
							byte[] encodedMsgBytes = encodedMessage.getBytes();
							
							// Inserted encoded message
							byte[] formattedMsgBytes = new byte[8 + encodedMsgBytes.length];
							
							// Get encoded message length
							byte[] encodedMsgLen = longToBytes(encodedMsgBytes.length);

							
							for(int i = 0; i < 8; i++) {
								formattedMsgBytes[i] = encodedMsgLen[i];
								
							}
							
							for(int i = 0; i < encodedMsgBytes.length; i++) {
								formattedMsgBytes[8 + i] = encodedMsgBytes[i];	
							}
									
							// Extract encoded message byte array into bit 
							byte[] messageBits = new byte[(formattedMsgBytes.length * 8)];
							
					        for(int i = 0, bit_count = 0; i< formattedMsgBytes.length; i++) {
					        	for(int j = 0; j < 8; j++, bit_count++) {
					        		messageBits[bit_count] = (byte) ((formattedMsgBytes[i] & (byte) Math.pow(2, j)) >> j);
								}
					        }
					        
					        // Open WAV input
							WavFile wavIn = WavFile.openWavFile(new File(audioInPathEnc));
							wavIn.display();

					        // Get the number of audio channels in the WAV file
					        int numChannels = wavIn.getNumChannels();
					        long numFrames = wavIn.getNumFrames();
					        
							// Preparing output
							WavFile wavOut = WavFile.newWavFile(new File(
					        		audioOutPath), 
					        		numChannels, 
					        		numFrames, 
					        		wavIn.getValidBits(), 
					        		wavIn.getSampleRate());
							

					        // Create a buffer of 100 frames
					        long[] bufferIn = new long[100 * numChannels];
					        long[][] bufferOut = new long[numChannels][100];
					        
					        int messageBitsRemaining = messageBits.length;
					        
					        int framesRead;
					        do {
					           // Read frames into buffer
					           framesRead = wavIn.readFrames(bufferIn, 100);
					           long remaining = wavOut.getFramesRemaining();
					           int toWrite = (remaining > 100) ? 100 : (int) remaining;
					            
					           for (int i = 0, k = 0; i < toWrite ; i++)
					           {
					        	   for(int j = 0; j < numChannels; j++, k++, messageBitsRemaining--) {
					        		   if(messageBitsRemaining > 0) {
					        			   if((bufferIn[k] & 1) == 0) {
					        				   bufferIn[k] |= 1;      
					        			   }
					        			   bufferOut[j][i] = bufferIn[k] & (long) messageBits[messageBits.length - messageBitsRemaining];
					        			}else {
					        			   bufferOut[j][i] = bufferIn[k];   
					        		   }
					        		}
					           }
					           
					           wavOut.writeFrames(bufferOut, toWrite); 
					           
						    } while (framesRead != 0);
					         
					        wavIn.close();
					        wavOut.close();
					       
				        	JOptionPane.showMessageDialog(mainFrame,
									"Succesfully Encrpyt the Message",
									"Done",
								    JOptionPane.INFORMATION_MESSAGE);
						}catch (Exception e) {
							JOptionPane.showMessageDialog(mainFrame,
									"An Error Occured: " + e.getMessage(),
									"Error",
								    JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnNewButton.setBounds(425, 195, 80, 23);
				panel_2.add(btnNewButton);
		contentPane.setLayout(gl_contentPane);
	}
}
