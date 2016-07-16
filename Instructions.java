import java.awt.*;
import javax.swing.*;

public class Instructions {
	private JFrame frame;
	private JTextPane text;
	private JScrollPane scroll;
	private static final Color FOREGROUND_COLOR = (Color.WHITE);
	private static final Color BACKGROUND_COLOR = new Color(0,71,92);
	private static final Font FONT = new Font("Courier New",Font.PLAIN, 14);
	
	public void create(){
		frame = new JFrame();
		Container cp = frame.getContentPane();
		text = new JTextPane();
		text.setText(
		"\nINSTRUCTIONS FOR MATH GAME\n"
		+ "\n"
		+ "\t<><> How to Play <><>\n"
		+ "\t\tWelcome to math game, the least fun game ever, because your score\n"
		+ "\t\tis determined by how well you know math! This game is very simple:\n"
		+ "\n"
		+ "\t\t<1.\tOn the title frame, choose the types of math problems you would like to solve.\n"
		+ "\t\t<2.\tPress play. This will exit the title frame and show the game's main screen.\n"
		+ "\t\t<3.\tRead the problem and write your solution in the answer box.\n"
		+ "\t\t     \tPress 'Enter' or click the 'Submit' button to submit your answer.\n"
		+ "\t\t<4.\tAfter either celebrating or crying over your answer's correctness,\n"
		+ "\t\t     \tclick the 'Next' button or press 'Enter' again to move on to the next question.\n"
		+ "\t\t<5.\tRepeat steps 3 and 4 until you either get bored or die.\n"
		+ "\n"
		+ "\t<><> Some Things to Know <><>\n"
		+ "\t\t->  On the geometry section, a calculator will pop up. Feel free to use it,\n"
		+ "\t\t    as you will need to when solving circle-related problems.\n"
		+ "\t\t->  Every answer must be rounded accurately to three decimal places.\n"
		+ "\t\t->  All questions are randomly generated. If your questions are consistently too\n"
		+ "\t\t    easy or difficult, blame RNGesus. He is a fickle god.\n"
		+ "\n"
		+ "\t<><> Using the calculator <><>\n"
		+ "\t\t->  Press buttons on calculator to input corresponding characters\n"
		+ "\t\t->  Alternatively, manually input numbers and operators using keyboard\n"
		+ "\t\t->  Press '=' key or press 'Enter' key to produce output\n"
		+ "\t\t->  After output has been displayed, calculator will automatically clear upon button input\n"
		+ "\t\t->  The following characters do not work: < > [ ] & ! | ^ ' \\\n"
		+ "\n"
		);
		text.setFont(FONT);
		text.setBackground(BACKGROUND_COLOR);
		text.setForeground(FOREGROUND_COLOR);
		cp.add(text);
		
		scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cp.add(scroll);
		
		frame.setTitle("Game Instructions (please read them, you need to know some stuff in here)");
		frame.setSize(850,200);
		frame.setLocation(410,10);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public void setVisible(boolean x){
		frame.setVisible(x);
	}
	
	public void dispose(){
		frame.dispose();
	}
}