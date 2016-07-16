import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.InputStream;
import java.util.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.sound.sampled.*;

public class Calculator {
	
	private static final Color FOREGROUND_COLOR = (Color.WHITE);
	private static final Color BACKGROUND_COLOR = new Color(0,95,124);
	
	private static final Border WHITE_BORDER = BorderFactory.createLineBorder(FOREGROUND_COLOR);
	private static final Border BLUE_BORDER = BorderFactory.createLineBorder(BACKGROUND_COLOR);
	
	private static final int BUTTON_FONT_SIZE = 30;
	private static final int DISPLAY_FONT_SIZE = 30;
	
	private static final Font BUTTON_FONT = new Font("Courier New",Font.PLAIN, BUTTON_FONT_SIZE);
	private static final Font INPUT_FONT = new Font("Courier New",Font.BOLD, DISPLAY_FONT_SIZE);
	private static final Font OUTPUT_FONT = new Font("Courier New",Font.BOLD + Font.ITALIC, DISPLAY_FONT_SIZE);
	
	private static final double TOLERANCE = .0000001;
	
	private static final String INPUT_TEXT = "[input]";
	private static final String OUTPUT_TEXT = "[output]";
	private static final String B0_TEXT = "0";
	private static final String B1_TEXT = "1";
	private static final String B2_TEXT = "2";
	private static final String B3_TEXT = "3";
	private static final String B4_TEXT = "4";
	private static final String B5_TEXT = "5";
	private static final String B6_TEXT = "6";
	private static final String B7_TEXT = "7";
	private static final String B8_TEXT = "8";
	private static final String B9_TEXT = "9";
	private static final String BPI_TEXT = "\u03C0";
	private static final String BDEC_TEXT = ".";
	private static final String BADD_TEXT = "+";
	private static final String BSUB_TEXT = "-";
	private static final String BMULT_TEXT = "*";
	private static final String BDIV_TEXT = "/";
	private static final String ENTER_TEXT = "=";
	private static final String EQUALS = "= ";
	private static final String DEL_TEXT = "DEL";
	private static final String CLEAR_TEXT = "CLEAR";
	private static final String ERROR_TEXT = "Error";
	private static final String BOPENPAR_TEXT = "(";
	private static final String BCLOSEPAR_TEXT = ")";
	private static final String BRNG_TEXT = "?";
	
	
	private static JFrame calcFrame;
	private static Container cp;
	private static GridBagConstraints c;
	
	private static JTextField input;
	private static JTextField output;
	
	private static JButton b0;
	private static JButton b1;
	private static JButton b2;
	private static JButton b3;
	private static JButton b4;
	private static JButton b5;
	private static JButton b6;
	private static JButton b7;
	private static JButton b8;
	private static JButton b9;
	private static JButton bPi;
	private static JButton bDec;
	
	private static JButton bAdd;
	private static JButton bSub;
	private static JButton bMult;
	private static JButton bDiv;
	private static JButton enter;
	
	private static JButton delete;
	private static JButton clear;
	private static JButton bOpenPar;
	private static JButton bClosePar;
	private static JButton bRng;
	
	
	
	private static ArrayList<JButton> buttons = new ArrayList<>();
	private static ScriptEngineManager mgr;
	
	private static boolean entered = true;
	
	private void append(JTextField f, String text){
		f.setText(f.getText() + text);
	}
	
	private String formatExpression(String str){
		str = str.replaceAll("\\s+","");
		str = str.replaceAll("\u03C0", "Math.PI");
		return str;
	}
	
	private String roundToString(double num, double tol){
		return Double.toString(Math.round(num/tol)*tol).substring(0,Double.toString(Math.round(num/tol)*tol).indexOf(".") + 4);
	}
	
	private String roundString(String str, double tol){
		if(str.substring(str.indexOf(".") + 1).length() >= (int)Math.log10(1/tol))
			return str.substring(0,str.indexOf(".") + (int)Math.log10(1/tol) + 1);
		return str;
	}
	
	private void createButtons(){
		
		
		b0 = new JButton(new AbstractAction(B0_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,B0_TEXT);
			}
		});
		
		b1 = new JButton(new AbstractAction(B1_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,B1_TEXT);
			}
		});
		
		b2 = new JButton(new AbstractAction(B2_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,B2_TEXT);
			}
		});
		
		b3 = new JButton(new AbstractAction(B3_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,B3_TEXT);
			}
		});
		
		b4 = new JButton(new AbstractAction(B4_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,B4_TEXT);
			}
		});
		
		b5 = new JButton(new AbstractAction(B5_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,B5_TEXT);
			}
		});
		
		b6 = new JButton(new AbstractAction(B6_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,B6_TEXT);
			}
		});
		
		b7 = new JButton(new AbstractAction(B7_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,B7_TEXT);
			}
		});
		
		b8 = new JButton(new AbstractAction(B8_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,B8_TEXT);
			}
		});

		b9 = new JButton(new AbstractAction(B9_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,B9_TEXT);
			}
		});
		
		bPi = new JButton(new AbstractAction(BPI_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,BPI_TEXT);
			}
		});
		
		bDec = new JButton(new AbstractAction(BDEC_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(entered){
					input.setText("");
					output.setText("");
				}
				entered = false;
				append(input,BDEC_TEXT);
			}
		});
		
		bAdd = new JButton(new AbstractAction(BADD_TEXT){
			
			public void actionPerformed(ActionEvent e){
				append(input,BADD_TEXT);
			}
		});
		
		bSub = new JButton(new AbstractAction(BSUB_TEXT){
			
			public void actionPerformed(ActionEvent e){
				append(input,BSUB_TEXT);
			}
		});
		
		bMult = new JButton(new AbstractAction(BMULT_TEXT){
			
			public void actionPerformed(ActionEvent e){
				append(input,BMULT_TEXT);
			}
		});
		
		bDiv = new JButton(new AbstractAction(BDIV_TEXT){
			
			public void actionPerformed(ActionEvent e){
				append(input,BDIV_TEXT);
			}
		});
		
		enter = new JButton(new AbstractAction(ENTER_TEXT){
			
			public void actionPerformed(ActionEvent e){
				try {
					calculate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		clear = new JButton(new AbstractAction(CLEAR_TEXT){
			
			public void actionPerformed(ActionEvent e){
				input.setText("");
				output.setText("");
			}
		});
		
		delete = new JButton(new AbstractAction(DEL_TEXT){
			
			public void actionPerformed(ActionEvent e){
				if(input.getText().length() > 0)
					input.setText(input.getText().substring(0, input.getText().length()-1));
			}
		});
		
		bOpenPar = new JButton(new AbstractAction(BOPENPAR_TEXT){
			
			public void actionPerformed(ActionEvent e){
				append(input,BOPENPAR_TEXT);
			}
		});
		
		bClosePar = new JButton(new AbstractAction(BCLOSEPAR_TEXT){
			
			public void actionPerformed(ActionEvent e){
				append(input,BCLOSEPAR_TEXT);
			}
		});
		
		bRng = new JButton(new AbstractAction(BRNG_TEXT){
			
			public void actionPerformed(ActionEvent e){
				Random rngesus = new Random();
				input.setText("");
				output.setText("");
				for(int i = 0; i <= rngesus.nextInt(9); i++){
					append(input, rngesus.nextInt(10) + "");
				}
			}
		});
		
		buttons.add(b0);
		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		buttons.add(b4);
		buttons.add(b5);
		buttons.add(b6);
		buttons.add(b7);
		buttons.add(b8);
		buttons.add(b9);
		buttons.add(bPi);
		buttons.add(bDec);
		buttons.add(bAdd);
		buttons.add(bSub);
		buttons.add(bMult);
		buttons.add(bDiv);
		buttons.add(enter);
		buttons.add(clear);
		buttons.add(delete);
		buttons.add(bOpenPar);
		buttons.add(bClosePar);
		buttons.add(bRng);
	}
	
	private void calculate() throws Exception{
		mgr = new ScriptEngineManager();
	    final ScriptEngine engine = mgr.getEngineByName("JavaScript");
		
		String text = input.getText().replaceAll("life", "42");
		try{
			if(text.equalsIgnoreCase("jo cushing"))
				output.setText(EQUALS + "<3");
			else if(text.equalsIgnoreCase("romil")){
				output.setText(EQUALS + "the coolest");
				File mlg = new File("./src/airhorn.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(mlg));
				clip.start();
			}
			else if(text.equalsIgnoreCase("mr.harris")){
				File bwang = new File("./src/INCEPTION_SOUND.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(bwang));
				clip.start();
			}
			else if(text.equalsIgnoreCase("johncena")){
				File cena = new File("./src/john_cena.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(cena));
				clip.start();
			}
			else{
				if((engine.eval(formatExpression(text)) + "").contains("."))
					output.setText(EQUALS + roundString(engine.eval(
									formatExpression(text)) + "", TOLERANCE)
												+ " ");
				else
					output.setText(EQUALS + engine.eval(
							formatExpression(text)) + " ");
			}
			
		} catch (ScriptException e1) {
			output.setText(ERROR_TEXT + " ");
		} catch (NumberFormatException e2){
			output.setText(ERROR_TEXT + " ");
		}
		entered = true;
	}
	
	private void setIndex(int y, int x, double weight, boolean tf){
		if(tf){
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		c.weightx = weight;
		c.gridx = x;
		c.gridy = y;
	}
	
	public void createFrame() {
		
		calcFrame = new JFrame();
		
		input = new JTextField(INPUT_TEXT);
		input.setFont(INPUT_FONT);
		input.setForeground(BACKGROUND_COLOR);
		input.setBackground(FOREGROUND_COLOR);
		input.setBorder(BLUE_BORDER);
		input.setHorizontalAlignment(SwingConstants.RIGHT);
		Action calcOnEnter = new AbstractAction(){
		    public void actionPerformed(ActionEvent e) {
		        	try {
						calculate();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    }
		};
		input.addActionListener(calcOnEnter);

		output = new JTextField(OUTPUT_TEXT);
		output.setEditable(false);
		output.setFont(OUTPUT_FONT);
		output.setForeground(BACKGROUND_COLOR);
		output.setBackground(FOREGROUND_COLOR);
		output.setBorder(BLUE_BORDER);
		output.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		cp = calcFrame.getContentPane();
		cp.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
	}
	
	private void addButtons(){
		
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		//input
		c.gridwidth = 4;
		calcFrame.add(input, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		
		//output
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 4;
		calcFrame.add(output, c);
		
		
		//clear delete
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		calcFrame.add(clear, c);

		c.gridx += 2;
		calcFrame.add(delete, c);
		c.gridwidth = 1;
		
		// ( ) ?
		c.gridy++;
		c.gridx = 0;
		calcFrame.add(bRng, c);
		
		c.gridx++;
		calcFrame.add(bOpenPar, c);
		
		c.gridx++;
		calcFrame.add(bClosePar, c);
		
		c.gridx++;
		calcFrame.add(bAdd, c);
		
		//7 8 9 -
		c.gridy++;
		c.gridx = 0;
		calcFrame.add(b7,c);
		
		c.gridx++;
		calcFrame.add(b8,c);
		
		c.gridx++;
		calcFrame.add(b9,c);
		
		c.gridx++;
		calcFrame.add(bSub,c);
		
		//4 5 6 *
		c.gridy++;
		c.gridx = 0;
		calcFrame.add(b4,c);
		
		c.gridx++;
		calcFrame.add(b5,c);
		
		c.gridx++;
		calcFrame.add(b6,c);
		
		c.gridx++;
		calcFrame.add(bMult,c);
		
		//1 2 3 /
		c.gridy++;
		c.gridx = 0;
		calcFrame.add(b1, c);
		
		c.gridx++;
		calcFrame.add(b2, c);
		
		c.gridx++;
		calcFrame.add(b3, c);
		
		c.gridx++;
		calcFrame.add(bDiv, c);
		
		//PI 0 . =
		c.gridy++;
		c.gridx = 0;
		calcFrame.add(bPi, c);
		
		c.gridx++;
		calcFrame.add(b0, c);
		
		c.gridx++;
		calcFrame.add(bDec, c);
		
		c.gridx++;
		calcFrame.add(enter, c);
		
		for(JButton i:buttons){
			i.setFont(BUTTON_FONT);
			i.setForeground(FOREGROUND_COLOR);
			i.setBackground(BACKGROUND_COLOR);
			i.setBorder(WHITE_BORDER);
		}
		
		calcFrame.setTitle("");
		calcFrame.setSize(300,328);
		calcFrame.setLocation(1270,220);
		
		calcFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public void create(){
		createButtons();
		createFrame();
		addButtons();
	}
	
	public void setVisible(boolean x){
			calcFrame.setVisible(x);
	}
	
	public void dispose(){
		calcFrame.dispose();
	}
}