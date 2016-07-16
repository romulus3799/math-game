import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
import javax.swing.text.*;

import java.util.*;

import javax.script.*;

class MathGame extends JFrame{	
	
	//colors, fonts, and borders
	private static final int QA_FONT_SIZE = 28;
	private static final int SCORE_FONT_SIZE = 29;
	private static final int CORRECT_FONT_SIZE = 20;
	
	private static final int TITLE_FONT_SIZE = 44;
	private static final int NAME_FONT_SIZE = 26;
	private static final int SUBJECT_FONT_SIZE = 20;
	private static final int SELECTION_FONT_SIZE = 16;
	private static final int TIMER_FONT_SIZE = 20;
	
	
	private static final int BUTTON_FONT_SIZE = 30;
	
	private static final Font QA_FONT = new Font("Courier New",Font.PLAIN, QA_FONT_SIZE);
	private static final Font SCORE_FONT = new Font("Calibri",Font.PLAIN, SCORE_FONT_SIZE);
	private static final Font CORRECT_FONT = new Font("Courier New",Font.ITALIC, CORRECT_FONT_SIZE);
	
	private static final Font TITLE_FONT = new Font("Agency FB",Font.BOLD, TITLE_FONT_SIZE);
	private static final Font NAME_FONT = new Font("Agency FB",Font.PLAIN, NAME_FONT_SIZE);
	private static final Font SUBJECT_FONT = new Font("Courier New",Font.PLAIN, SUBJECT_FONT_SIZE);
	private static final Font SELECTION_FONT = new Font("Calibri",Font.PLAIN, SELECTION_FONT_SIZE);
	private static final Font TIMER_FONT = new Font("Courier New",Font.BOLD, TIMER_FONT_SIZE);
	
	private static final Font BUTTON_FONT = new Font("Calibri",Font.PLAIN, BUTTON_FONT_SIZE);
	
	private static final Color FOREGROUND_COLOR = (Color.WHITE);
	private static final Color BACKGROUND_COLOR = new Color(0,71,92);
	private static final Color BUTTON_BACKGROUND = new Color(0,95,124);
	private static final Color MSG_BACKGROUND = new Color(136,0,124);
	private static final Color CORRECT_COLOR = new Color(0,98,36);
	private static final Color INCORRECT_COLOR = new Color(153,0,0);
	
	private static final Border WHITE_BORDER = BorderFactory.createLineBorder(FOREGROUND_COLOR);
	private static final Border COMP_BORDER = BorderFactory.createLineBorder(BACKGROUND_COLOR);

	//title frame and components
	private JFrame titleFrame;
	private Container TFCP;
	private GridBagConstraints c;
	private JButton buttonPlay;
	private JButton buttonInstructions;
	private Instructions instructions;
	private JTextPane selectionPrompt;
	
	private JCheckBox arithCheck;
	private JCheckBox preAlgCheck;
	private JCheckBox geomCheck;
	private JCheckBox algCheck;
	private JCheckBox trigCheck;
	
	
	private static final String TF_TITLE = "Main Menu";
	private static final int TF_SIZE_X = 400;
	private static final int TF_SIZE_Y = 350;
	private static final int TF_LOC_X = 10;
	private static final int TF_LOC_Y = 10;
	
	//game frame and components
	private JFrame gameFrame;
	private Container GFCP;
	private Calculator calc;
	
	private JTextPane title;
	private JTextPane name;
	private JTextField subject;
	private JTextField answer;
	private JTextPane correct;
	private JTextPane question;
	private JTextField timer;
	
	private JButton buttonSubmit;
	private JButton buttonQuit;
	
	private static final String GF_TITLE = "Game Client";
	private static final int GF_SIZE_X = 850;
	private static final int GF_SIZE_Y = 225;
	private static final int GF_LOC_X = 410;
	private static final int GF_LOC_Y = 220;
	
	//timers
	private Timer time;
	private double ms;
	private Timer ready;
	private int countdown;
	private static final int ARITH_TIME = 10;
	private static final int PRE_ALG_TIME = 20;
	private static final int ALG_TIME = 30;
	private static final int GEOM_TIME = 45;
	
	
	//text values
	private static final String TITLE_TEXT = "Different Ways to Count";
	private static final String TITLE_TEXT_ONE = " Different Ways to Count  ";
	private static final String NAME_TEXT = " a game by Romil Havewala\n";
	private static final String SUBJECT_TEXT = "Subject: ";
	private static final String SELECTION_TEXT = "\nSelect subjects to be quizzed on:";
	private static final String INIT_ANSWER_TEXT = "(Type answers here)";
	
	private static final String BUTTON_BEGIN_TEXT = "[Begin]";
	private static final String BUTTON_SUBMIT_TEXT = "[Submit Answer]";
	private static final String BUTTON_NEXT_TEXT = "[Next Question]";
	private static final String BUTTON_PLAY_TEXT = "[Start Game]";
	private static final String BUTTON_QUIT_TEXT = "[Exit Game]";
	private static final String BUTTON_INSTRUCTIONS_TEXT = "[Instructions]";
	
	private static final String INIT_QUESTION_TEXT = "(Questions will appear here)";
	
	private static final String CORRECT = "Correct!\n";
	private static final String INCORRECT = "Incorrect.\n";
	private static final String MESSAGE_TEXT = "Message from Romulus3799 -- ";
	private static final String READY_MESSAGE = "Hit 'Begin' when you're ready to start!\n";
	private static final String ROUND_MESSAGE = "REMEMBER: round answers to 3 decimal places";
	private static final String[] MESSAGES = 
		{"Good luck on this problem!\n",
		"Ouch, this one's tough. Not looking good.\n",
		"You can't get this one right.\n",
		"This problem is easy!\n",
		"I could do this one in my head!\n",
		"You can do this!\n",
		"This one is hard.\n",
		"A five-year old could solve this problem.\n",
		"Don't strain yourself thinking.\n",
		"Take a break if you need to.\n",
		"Are you sure you can do this problem?\n",
		"101 d0 u 3\\/3n h4|<, 5|<ru8???\n",
		"You can do this!\n",
		"I believe in you!\n",
		"Keep up the great work.\n",
		"Don't get this problem wrong.\n"};
	
	private static final String ARITH = "Basic Arithmetic ";
	private static final String PRE_ALG = "Pre-Algebra ";
	private static final String ALG = "Algebra ";
	private static final String GEOM = "Geometry ";
	private static final String TRIG = "Trigonometry (coming soon!) ";
	
	
	private static final String CENTER = "center";
	private static final String LEFT = "right";
	private static final String RIGHT = "left";
	
	private static final String TIME_REMAINING = "Time remaining:\t";
	private static final String CHEAT_CODE = "romiliscool";
	
	//logic
	private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
	private ArrayList<String> subjects = new ArrayList<>();
	private static final int QUESTION_INCREMENT = 5;
	private boolean submitted;
	private boolean initial;
	private int numCheckBoxes;
	private boolean started;
	private boolean readyBoolean;
	
	//score
	private JTextField score;
	private int numCorrect;
	private int numQuestions;
	private String scoreText;
	
	
	//math
	private String expression;
	private ScriptEngineManager mgr;
	private Random rngesus;
	private int op;
	//arithmetic
	private int arith1;
	private int arith2;
	private static final int ARITHMETIC_LIMIT = 12;
	//preAlgebra
	private int preAlgProb;
	private int num1;
	private int denom1;
	private Fraction f1;
	private int num2;
	private int denom2;
	private Fraction f2;
	private static final int FRAC_LIMIT = 10;
	private static final int ABS_SUBTRACTOR = 12;
	private static final int ABS_LIMIT = 24;
	private int gcf1;
	private int gcf2;
	private static final int GCF_LIMIT = 100;
	private int lcm1;
	private int lcm2;
	private static final int LCM_LIMIT = 20;
	//geometry
	private int geomProb;
	private static final int TOLERANCE = 3;
	private int base;
	private int height;
	private static final int BHR_LIMIT = 100;
	private int radius;
	private static final double pi = Math.PI;
	private int numSides;
	private int sideLength;
	private static final int SIDE_LIMIT = 100;
	private static final int SIDE_LENGTH_LIMIT = 100;
	//algebra
	private int alg2Prob;
	private static final int TERM_LIMIT = 12;
	private Term term1;
	private Term term2;
	private static final int EQ_LIMIT = 20;
	private Equation equation;
	private Sequence sequence;
	
	//math statements
	private String expStatement = expression + " = ";
	
	private String gcfStatement = expression + " = ";
	private String lcmStatement = expression + " = ";
	
	private String rectAreaStatement = "Area of rectangle w/ base " + base + " and height " + height + " = ";
	private String rectPerimStatement = "Perim of rectangle w/ base " + base + " and height " + height + " = ";
	private String circAreaStatement = "Area of circle w/ radius " + radius + " = ";
	private String circumStatement = "Perim of circle w/ radius " + radius + " = ";
	private String triAreaStatement = "Area of triangle w/ base " + base + " and height " + height + " = ";
	private String polygonPerimStatement = "Perim of " + numSides + "-sided shape w/ side length " + sideLength + " = ";
	private String termStatement = expression + " = ";
	private String eqStatement;
	private String seqStatement;
	//helper methods
	public MathGame() {
		rngesus = new Random();
		numCorrect = 0;
		numQuestions = 0;
		submitted = false;
		calc = new Calculator();
		calc.create();
		calc.setVisible(false);
		checkBoxes.removeAll(checkBoxes);
		subjects.removeAll(subjects);
		instructions = new Instructions();
		instructions.create();
		instructions.setVisible(false);
		initial = true;
		scoreText = "Questions Completed: " + numQuestions + "\tScore: 0%";
		ms = 0;
		countdown = 3;
		equation = new Equation(0,"a",0,0);
		sequence = new Sequence();
		started = false;
		readyBoolean = false;
	}

	private String removeSpaces(String str){
		return str.replaceAll("\\s+","");
	}
	
	private void updateSubject(String sub){
		subject.setText(SUBJECT_TEXT + sub + "");
		if(sub.equals(GEOM))
			calc.setVisible(true);
		else
			calc.setVisible(false);
	}
	
	private void updateScore(){
		score.setText("");
		
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
		}
		scoreText = "Score: " + (int)(numCorrect*100/numQuestions)
				+ "%\tQuestions completed: " + numQuestions;
		score.setText(scoreText);
	}

	private void updateStatements(){
		expStatement = expression + " = ";
		if(preAlgProb==1)
			expStatement = "|" + expression + "| = ";
		gcfStatement = expression + " = ";
		lcmStatement = expression + " = ";
		
		rectAreaStatement = "Area of rectangle w/ base " + base
				+ " and height " + height + " = ";
		
		rectPerimStatement = "Perim of rectangle w/ base " + base
				+ " and height " + height + " = ";
		
		circAreaStatement = "Area of circle w/ radius " + radius + " = ";
		circumStatement = "Circumference of circle w/ radius " + radius + " = ";
		triAreaStatement = "Area of triangle w/ base " + base + " and height " + height + " = ";
		polygonPerimStatement = "Perim of " + numSides + "-sided shape w/ side length " + sideLength + " = ";
		termStatement = expression + " = ";
		eqStatement = "Solve for " + equation.getVar() + ":\t\t" + equation;
		seqStatement = "Find next in sequence: " + sequence + ", ___";
	}
	
	private void setQuestionArith(){
		updateSubject(ARITH);
		op = rngesus.nextInt(4);
		
		if(op == 3){
			arith1 = rngesus.nextInt(ARITHMETIC_LIMIT+1);
			do{
				arith2 = rngesus.nextInt(ARITHMETIC_LIMIT+1);
			}while(arith2 == 0 || arith1 % arith2 != 0);
		}
		else if(op == 1){
			arith1 = rngesus.nextInt(ARITHMETIC_LIMIT+1);
			arith2 = rngesus.nextInt(arith1+1);
		}
		else{
			arith1 = rngesus.nextInt(ARITHMETIC_LIMIT+1);
			arith2 = rngesus.nextInt(ARITHMETIC_LIMIT+1);
		}
		
		switch(op){
			case 0: expression = (arith1 + " + " + arith2);
			break;
			
			case 1: expression = (arith1 + " - " + arith2);
			break;
		
			case 2: expression = (arith1 + " * " + arith2);
			break;
		
			case 3: expression = (arith1 + " / " + arith2);
			break;
		}
		if(op == 2)
			question.setText(arith1 + " · " + arith2 + " = ");
		else if(op == 3)
			question.setText(arith1 + " ÷ " + arith2 + " = ");
		else
			question.setText(expression + " = ");
	}
	
	private void setQuestionPreAlg(){
		updateSubject(PRE_ALG);
		preAlgProb = rngesus.nextInt(4);
		switch(preAlgProb){
		case 0: fractionQuestion();
		break;
		case 1: absQuestion();
		break;
		case 2: gcfQuestion();
		break;
		case 3: lcmQuestion();
		break;
		}
	}
	
	private void setQuestionGeom(){
		updateSubject(GEOM);
		geomProb = rngesus.nextInt(6);
		switch(geomProb){
		case 0: rectAreaQuestion();
		break;
		case 1: rectPerimQuestion();
		break;
		case 2: circAreaQuestion();
		break;
		case 3: circumQuestion();
		break;
		case 4: triAreaQuestion();
		break;
		case 5: polygonPerimQuestion();
		break;
		}
	}
	
	private void setQuestionAlg(){
		updateSubject(ALG);
		alg2Prob = rngesus.nextInt(3);
		switch(alg2Prob){
		case 0: termQuestion();
		break;
		case 1: eqQuestion();
		break;
		case 2: seqQuestion();
		break;
		}
	}
	
	private void fractionQuestion(){
		op = rngesus.nextInt(4);
		num1 = rngesus.nextInt(FRAC_LIMIT+1);
		if(op == 3 && num1 == 0)
			num1++;
		denom1 = (1+rngesus.nextInt(FRAC_LIMIT));
		f1 = new Fraction(num1, denom1);
		
		num2 = rngesus.nextInt(FRAC_LIMIT+1);
		if(op == 3 && num2 == 0)
			num2++;
		denom2 = (1+rngesus.nextInt(FRAC_LIMIT));
		if(op == 0)
			while(gcf(denom1,denom2) == 1)
				denom2 = rngesus.nextInt(FRAC_LIMIT);
		f2 = new Fraction(num2, denom2);
		
		f1 = f1.simplify();
		f2 = f2.simplify();
		
		switch(op){
		case 0: expression = (f1 + " + " + f2);
		break;
		
		case 1: expression = (f1 + " - " + f2);
		break;
	
		case 2: expression = (f1 + " * " + f2);
		break;
	
		case 3: expression = (f1 + " / " + f2);
		break;
		}
		
			if(op == 2)
				question.setText(f1 + " · " + f2 + " = ");
			else if(op == 3)
				question.setText(f1 + " ÷ " + f2 + " = ");
			else
				question.setText(expression + " = ");
	}
	
	private void absQuestion(){
		op = rngesus.nextInt(4);
		
		if(op == 3){
			arith1 = ABS_SUBTRACTOR-rngesus.nextInt(ABS_LIMIT+1);
			do{
				arith2 = ABS_SUBTRACTOR-rngesus.nextInt(ABS_LIMIT+1);
			}while(arith2 == 0 || arith1 % arith2 != 0);
		}
		else if(op == 1){
			arith1 = ABS_SUBTRACTOR-rngesus.nextInt(ABS_LIMIT+1);
			arith2 = ABS_SUBTRACTOR-rngesus.nextInt(ABS_LIMIT+1);
		}
		else{
			arith1 = ABS_SUBTRACTOR-rngesus.nextInt(ABS_LIMIT+1);
			arith2 = ABS_SUBTRACTOR-rngesus.nextInt(ABS_LIMIT+1);
		}
		
		switch(op){
			case 0: expression = (arith1 + " + " + arith2);
			break;
			
			case 1: expression = (arith1 + " - " + arith2);
			break;
		
			case 2: expression = (arith1 + " * " + arith2);
			break;
		
			case 3: expression = (arith1 + " / " + arith2);
			break;
		}
		if(op == 2)
			question.setText("|" + arith1 + " · " + arith2 + "| = ");
		else if(op == 3)
			question.setText("|" + arith1 + " ÷ " + arith2 + "| = ");
		else
			question.setText("|" + expression + "| = ");
	}
	
	private void gcfQuestion(){
		gcf1 = rngesus.nextInt(GCF_LIMIT);
		gcf2 = rngesus.nextInt(GCF_LIMIT);
		if(rngesus.nextInt(4) != 0)
			while(gcf(gcf1,gcf2) == 1)
				gcf2 = rngesus.nextInt(GCF_LIMIT);
		
		expression = "GCF of " + gcf1 + " and " + gcf2;
		updateStatements();
		question.setText(gcfStatement);
	}
	
	private void lcmQuestion(){
		lcm1 = rngesus.nextInt(LCM_LIMIT);
		lcm2 = rngesus.nextInt(LCM_LIMIT);
		if(rngesus.nextInt(3) != 0)
			while(lcm(lcm1,lcm2) == 1)
				lcm2 = rngesus.nextInt(LCM_LIMIT);
		
		expression = "LCM of " + lcm1 + " and " + lcm2;
		updateStatements();
		question.setText(lcmStatement);
	}
	
	private int gcf(int n1, int n2){
		int gcf = 1;
		for(int i = 1; i <= min(n1,n2); i++)
			if(n1%i==0 && n2%i==0)
				gcf = i;
		return gcf;
	}
	
	private int lcm(int n1, int n2){
		return n1*n2/gcf(n1,n2);
	}

	private void rectAreaQuestion(){
		base = 1+rngesus.nextInt(BHR_LIMIT);
		height = 1+rngesus.nextInt(BHR_LIMIT);
		
		updateStatements();
		question.setText(rectAreaStatement);
	}

	private void rectPerimQuestion(){
		base = 1+rngesus.nextInt(BHR_LIMIT);
		height = 1+rngesus.nextInt(BHR_LIMIT);
		
		updateStatements();
		question.setText(rectPerimStatement);
	}

	private void triAreaQuestion(){
		base = 1+rngesus.nextInt(BHR_LIMIT);
		height = 1+rngesus.nextInt(BHR_LIMIT);
		
		updateStatements();
		question.setText(triAreaStatement);
	}

	private void circAreaQuestion(){
		radius = 1+rngesus.nextInt(BHR_LIMIT);
		
		updateStatements();
		question.setText(circAreaStatement);
	}

	private void circumQuestion(){
		radius = 1+rngesus.nextInt(BHR_LIMIT);
		
		updateStatements();
		question.setText(circumStatement);
	}

	private void polygonPerimQuestion(){
		numSides = rngesus.nextInt(SIDE_LIMIT);
		sideLength = rngesus.nextInt(SIDE_LENGTH_LIMIT);
		
		updateStatements();
		question.setText(polygonPerimStatement);
	}
	
	private void termQuestion(){
		op = rngesus.nextInt(4);
		
		term1 = new Term(rngesus.nextInt(TERM_LIMIT+1),"x",1+rngesus.nextInt(TERM_LIMIT));
		if(op<2)
			term2 = new Term(rngesus.nextInt(TERM_LIMIT+1),"x",term1.exp);
		else if(op == 3)
			term2 = new Term(term1.coeff,"x",1+rngesus.nextInt(term1.exp));
		else
			term2 = new Term(rngesus.nextInt(TERM_LIMIT+1),"x",1+rngesus.nextInt(TERM_LIMIT));
		
		
		switch(op){
		case 0: expression = (term1.toString() + " + " + term2.toString());
		break;
		
		case 1: expression = (term1.toString() + " - " + term2.toString());
		break;
	
		case 2: expression = (term1.toString() + " * " + term2.toString());
		break;
	
		case 3: expression = (term1.toString() + " / " + term2.toString());
		break;
		}
		
		if(op == 2)
			question.setText(term1.toString() + " · " + term2.toString() + " = ");
		else if(op == 3)
			question.setText(term1.toString() + " ÷ " + term2.toString() + " = ");
		else
			question.setText(expression + " = ");
	}
	
	private void eqQuestion(){
		equation = new Equation(1+rngesus.nextInt(EQ_LIMIT),
							Character.toString ((char) (97 + rngesus.nextInt(25))),
							rngesus.nextInt(EQ_LIMIT+1),
							rngesus.nextInt(EQ_LIMIT+1));
		
		while((equation.getResult() - equation.getAddend())%equation.getCoeff() != 0)
			equation.setCoeff(1+rngesus.nextInt(EQ_LIMIT));
		
		updateStatements();
		question.setText(eqStatement);
	}
	
	private void seqQuestion(){
		sequence = new Sequence();
		sequence.generate();
		updateStatements();
		question.setText(seqStatement);
	}
	
	private int min(int n1, int n2){
		if(n1>n2)return n2;
		else return n1;
	}
	
	private boolean accurate(double num1, double num2, double tolerance){
		return Math.abs(num1-num2) < tolerance;
	}

	private double round(double num, int decs){
		return Math.round(num*Math.pow(10,decs))/Math.pow(10,decs);
	}
	
	
	private void markCorrect(){
		correct.setForeground(Color.WHITE);
		correct.setBackground(CORRECT_COLOR);
		correct.setText(CORRECT);
		numCorrect++;
	}

	private void markIncorrect(String input){
		correct.setForeground(Color.WHITE);
		correct.setBackground(INCORRECT_COLOR);
		correct.setText(INCORRECT + input);
	}
	
	private void checkArith(int input){
		try{
			if(removeSpaces(answer.getText()).equalsIgnoreCase(Integer.toString(input))){
				markCorrect();
			}
			else{
				updateStatements();
				markIncorrect(expStatement + input);
			}
		}catch(NumberFormatException e){
			markIncorrect(expStatement + input);
		}
	}
	
	private void checkAbs(int input){
		try{
			if(removeSpaces(answer.getText()).equalsIgnoreCase(Integer.toString(Math.abs(input)))){
				markCorrect();
			}
			else{
				updateStatements();
				markIncorrect(expStatement + Math.abs(input));
			}
		}catch(NumberFormatException e){
			updateStatements();
			markIncorrect(expStatement + Math.abs(input));
		}
	}
	
	private void checkFrac(){
		try{
			double result;
			if(answer.getText().contains("/"))
				result = (new Fraction(removeSpaces(answer.getText()))).toDouble();
			else
				result = Double.parseDouble(removeSpaces(answer.getText()));
			switch(op){
				case 0: if(result == (f1.add(f2).toDouble())){
					markCorrect();
				}
				else{
					updateStatements();
					markIncorrect(expStatement + f1.add(f2).toString());
				}
				break;
				
				case 1: if(result == (f1.subtract(f2).toDouble())){
					markCorrect();
				}
				else{
					updateStatements();
					markIncorrect(expStatement + f1.subtract(f2).toString());
				}
				break;
			
				case 2: if(result == (f1.multiply(f2).toDouble())){
					markCorrect();
				}
				else{
					updateStatements();
					markIncorrect(expStatement + f1.multiply(f2).toString());
				}
				break;
			
				case 3: if(result == (f1.divide(f2).toDouble())){
					markCorrect();
				}
				else{
					updateStatements();
					markIncorrect(expStatement + f1.divide(f2).toString());
				}
				break;
			}
		}catch(NumberFormatException e){
			markIncorrect(expStatement + f1.divide(f2).toString());
		}
	}
	
	private void checkGcf(){
		int num = 0;
		try{
			num = Integer.parseInt(removeSpaces(answer.getText()));
			if(num == gcf(gcf1,gcf2))
				markCorrect();
			else{
				updateStatements();
				markIncorrect(gcfStatement + gcf(gcf1,gcf2));
			}
		}catch(NumberFormatException e){
			markIncorrect(gcfStatement + gcf(gcf1,gcf2));
		}
	}
	
	private void checkLcm(){
		int num = 0;
		try{
			num = Integer.parseInt(removeSpaces(answer.getText()));
			if(num == lcm(lcm1,lcm2))
				markCorrect();
			else{
				updateStatements();
				markIncorrect(lcmStatement + lcm(lcm1,lcm2));
			}
		}catch(NumberFormatException e){
			markIncorrect(lcmStatement + lcm(lcm1,lcm2));
		}
	}
	
	private void checkRectArea(){
		int num = 0;
		try{
			num = Integer.parseInt(removeSpaces(answer.getText()));
			if(num == base*height)
				markCorrect();
			else{
				updateStatements();
				markIncorrect(base + " · " + height + " = " + base*height);
			}
		}catch(NumberFormatException e){
			markIncorrect(base + " · " + height + " = " + base*height);
		}
	}
	
	private void checkRectPerim(){
		int num = 0;
		try{
			num = Integer.parseInt(removeSpaces(answer.getText()));
			if(num == 2*base + 2*height)
				markCorrect();
			else{
				updateStatements();
				markIncorrect("2·" + base + " + " + "2·" + height + " = " + base*height);
			}
		}catch(NumberFormatException e){
			markIncorrect("2·" + base + " + " + "2·" + height + " = " + base*height);
		}
	}
	
	private void checkCircArea(){
		double num = 0;
		try{
			num = Double.parseDouble(removeSpaces(answer.getText()));
			if(accurate(num, pi*radius*radius, TOLERANCE))
				markCorrect();
			else{
				updateStatements();
				markIncorrect("pi · " + radius + "^2 = " + round(pi*radius*radius, TOLERANCE));
			}
		}catch(NumberFormatException e){
			markIncorrect("pi · " + radius + "^2 = " + round(pi*radius*radius, TOLERANCE));
		}
	}
	
	private void checkCircum(){
		double num = 0;
		try{
			num = Double.parseDouble(removeSpaces(answer.getText()));
			if(accurate(num, 2*pi*radius, TOLERANCE))
				markCorrect();
			else{
				updateStatements();
				markIncorrect("2 · pi · " + radius + " = " + round(2*pi*radius, TOLERANCE));
			}
		}catch(NumberFormatException e){
			markIncorrect("2 · pi · " + radius + " = " + round(2*pi*radius, TOLERANCE));
		}
	}
	
	private void checkTriArea(){
		double num = 0;
		try{
			num = Double.parseDouble(removeSpaces(answer.getText()));
			if(num == base*height/2.)
				markCorrect();
			else{
				updateStatements();
				markIncorrect(base + " · " + height + " / 2 = " + base*height/2.);
			}
		}catch(NumberFormatException e){
			markIncorrect(base + " · " + height + " / 2 = " + base*height/2.);
		}
	}
	
	private void checkPolygonPerim(){
		int num = 0;
		try{
			num = Integer.parseInt(removeSpaces(answer.getText()));
			if(num == numSides*sideLength)
				markCorrect();
			else{
				updateStatements();
				markIncorrect(sideLength + " · " + numSides + " = " + numSides*sideLength);
			}
		}catch(NumberFormatException e){
			markIncorrect(sideLength + " · " + numSides + " = " + numSides*sideLength);
		}
	}

	private void checkTerm(){
		updateStatements();
		String ans = "";
		switch(op){
		case 0: ans = term1.add(term2).toString();
			break;
		case 1: ans = term1.subtract(term2).toString();
			break;
		case 2: ans = term1.multiply(term2).toString();
			break;
		case 3: ans = term1.divide(term2).toString();
			break;
			
		}
		
		if(removeSpaces(answer.getText()).equalsIgnoreCase(removeSpaces(ans))
				|| removeSpaces(ans).equals("x^0") && removeSpaces(answer.getText()).equals("1")
				|| removeSpaces(ans).equals("1x") && removeSpaces(answer.getText()).equals("x"))
			markCorrect();
		else
			markIncorrect(termStatement + ans.replaceAll("x^0", "1").replaceAll("1x", "x"));
	}
	
	private void checkEq(){
		updateStatements();
		if(removeSpaces(answer.getText()).equals(removeSpaces(equation.solve() + ""))
			|| removeSpaces(answer.getText()).equals(removeSpaces(equation.getVar() + " = " + equation.solve())))
			markCorrect();
		else
			markIncorrect(equation.getVar() + " = " + equation.solve());
	}
	
	private void checkSeq(){
		updateStatements();
		if(removeSpaces(answer.getText()).equals(removeSpaces(sequence.getNext() + "")))
			markCorrect();
		else
			markIncorrect(sequence + ", " + sequence.getNext());
	}
	
	private void align(JTextPane textPane, String alignment){
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet align = new SimpleAttributeSet();
		if(alignment.equals(CENTER))
			StyleConstants.setAlignment(align, StyleConstants.ALIGN_CENTER);
		else if(alignment.equals(RIGHT))
			StyleConstants.setAlignment(align, StyleConstants.ALIGN_RIGHT);
		else if(alignment.equals(LEFT))
			StyleConstants.setAlignment(align, StyleConstants.ALIGN_LEFT);
		doc.setParagraphAttributes(0, doc.getLength(), align, false);
	}
	
	private void setupTFComponents(){
		
		titleFrame = new MathGame();
		TFCP = titleFrame.getContentPane();
		
		title = new JTextPane();
		title.setText(TITLE_TEXT);
		title.setFont(TITLE_FONT);
		align(title,CENTER);
		title.setEditable(false);
		title.setForeground(FOREGROUND_COLOR);
		title.setBackground(BACKGROUND_COLOR);
		title.setBorder(COMP_BORDER);
		
		name = new JTextPane();
		name.setText(NAME_TEXT);
		name.setFont(NAME_FONT);
		align(name,CENTER);
		name.setEditable(false);
		name.setForeground(FOREGROUND_COLOR);
		name.setBackground(BACKGROUND_COLOR);
		name.setBorder(COMP_BORDER);
		
		selectionPrompt = new JTextPane();
		selectionPrompt.setText(SELECTION_TEXT);
		selectionPrompt.setFont(SELECTION_FONT);
		selectionPrompt.setEditable(false);
		selectionPrompt.setForeground(FOREGROUND_COLOR);
		selectionPrompt.setBackground(BACKGROUND_COLOR);
		selectionPrompt.setBorder(COMP_BORDER);
		
		arithCheck = new JCheckBox(ARITH);
		arithCheck.setSelected(true);
		
		preAlgCheck = new JCheckBox(PRE_ALG);
		preAlgCheck.setSelected(true);
		
		geomCheck = new JCheckBox(GEOM);
		geomCheck.setSelected(true);
		
		algCheck = new JCheckBox(ALG);
		algCheck.setSelected(true);
		
		trigCheck = new JCheckBox(TRIG);
		trigCheck.setSelected(false);
		trigCheck.setEnabled(false);
		
	}
	
	private void setupGFComponents(){
		
		gameFrame = new MathGame();
		GFCP = gameFrame.getContentPane();
		
		title = new JTextPane();
		title.setText(TITLE_TEXT_ONE);
		title.setFont(TITLE_FONT);
		align(title,CENTER);
		title.setForeground(FOREGROUND_COLOR);
		title.setBackground(BACKGROUND_COLOR);
		title.setBorder(COMP_BORDER);
		title.setEditable(false);
		
		subject = new JTextField();
		updateSubject(ARITH);
		subject.setFont(SUBJECT_FONT);
		subject.setHorizontalAlignment(SwingConstants.CENTER);
		subject.setEditable(false);
		subject.setForeground(FOREGROUND_COLOR);
		subject.setBackground(BACKGROUND_COLOR);
		subject.setBorder(COMP_BORDER);
		
		question = new JTextPane();
		question.setText(INIT_QUESTION_TEXT);
		question.setFont(QA_FONT);
		align(question,LEFT);
		question.setEditable(false);
		question.setForeground(FOREGROUND_COLOR);
		question.setBackground(BACKGROUND_COLOR);
		question.setBorder(COMP_BORDER);
		
		answer = new JTextField(INIT_ANSWER_TEXT);
		answer.setFont(QA_FONT);
		answer.setBackground(FOREGROUND_COLOR);
		answer.setForeground(BACKGROUND_COLOR);
		Action checkOnEnter = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
		    {
		    	if(submitted)
		    		next();
		    	else
		        	checkAnswer();
		    }
		};
		answer.addActionListener(checkOnEnter);
		answer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(initial)
					answer.setText("");
				initial = false;
			}
			});
		answer.setEditable(false);
		
		correct = new JTextPane();
		correct.setText(MESSAGE_TEXT + READY_MESSAGE);
		correct.setFont(CORRECT_FONT);
		align(correct,CENTER);
		correct.setEditable(false);
		correct.setForeground(FOREGROUND_COLOR);
		correct.setBackground(MSG_BACKGROUND);
		correct.setBorder(WHITE_BORDER);
		
		score = new JTextField(scoreText);
		score.setFont(SCORE_FONT);
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setEditable(false);
		score.setForeground(FOREGROUND_COLOR);
		score.setBackground(BACKGROUND_COLOR);
		score.setBorder(COMP_BORDER);
		
		setupTimer();
	}
	
	private void setupTimer(){
		Action count = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
		    {
				double temp = round(ms/100,3);
				double timeLeft = 15;
				if(subject.getText().contains(ARITH))
					timeLeft = round(ARITH_TIME-temp,3);
				if(subject.getText().contains(PRE_ALG))
					timeLeft = round(PRE_ALG_TIME-temp,3);
				if(subject.getText().contains(GEOM))
					timeLeft = round(GEOM_TIME-temp,3);
				if(subject.getText().contains(ALG))
					timeLeft = round(ALG_TIME-temp,3);
				
				String tmr = Double.toString(timeLeft);
				while(tmr.substring(tmr.indexOf(".")).length() < 4)
					tmr += "0";
				timer.setText(TIME_REMAINING + tmr);
				
	    		if(timeLeft > 0)
		    		ms += 1.5;
	    		else{
	    			timer.setText(TIME_REMAINING + "0.000");
	    			checkAnswer();
	    		}
		    }
		};
		time = new Timer(10,count);
		
		timer = new JTextField();
		timer.setFont(TIMER_FONT);
		timer.setHorizontalAlignment(SwingConstants.LEFT);
		timer.setEditable(false);
		timer.setForeground(FOREGROUND_COLOR);
		timer.setBackground(BACKGROUND_COLOR);
		timer.setBorder(COMP_BORDER);
		
	}
	
	private void checkSubjects(String s){
		if(s.equals(ARITH))
			setQuestionArith();
		if(s.equals(PRE_ALG))
			setQuestionPreAlg();
		if(s.equals(GEOM))
			setQuestionGeom();
		if(s.equals(ALG))
			setQuestionAlg();
	}
	
	private void checkAnswer(){
		time.stop();
		ms = 0;
		mgr = new ScriptEngineManager();
	    final ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    
		numQuestions++;
		if(answer.getText().equals(CHEAT_CODE)){
			markCorrect();
		}
		else{
			if(subject.getText().contains(ARITH))
				try {
					checkArith((int)(engine.eval(expression)));
				} catch (ScriptException e1) {
				}
			else if(subject.getText().contains(PRE_ALG)){
				switch(preAlgProb){
				case 0:checkFrac();
				break;
				case 1:
					try {
						checkAbs((int)(engine.eval(expression)));
					} catch (ScriptException e1) {
					}
				break;
				case 2: checkGcf();
				break;
				case 3: checkLcm();
				break;
				}
			}
			else if(subject.getText().contains(GEOM)){
				switch(geomProb){
				case 0:checkRectArea();
				break;
				case 1:checkRectPerim();
				break;
				case 2:checkCircArea();
				break;
				case 3:checkCircum();
				break;
				case 4:checkTriArea();
				break;
				case 5:checkPolygonPerim();
				break;
				}
				
			}
			else if(subject.getText().contains(ALG)){
				switch(alg2Prob){
				case 0:checkTerm();
				break;
				case 1:checkEq();
				break;
				case 2:checkSeq();
				break;
				}
			}
		}
		updateScore();
		submitted = true;
		buttonSubmit.setText(BUTTON_NEXT_TEXT);
		
	}
	
	private void next(){
		if(numQuestions/QUESTION_INCREMENT < subjects.size())
			checkSubjects(subjects.get(numQuestions/QUESTION_INCREMENT));
		else
			checkSubjects(subjects.get(subjects.size()-1));
		
		answer.setText("");
		answer.requestFocus();
		
		correct.setText(MESSAGE_TEXT + MESSAGES[rngesus.nextInt(MESSAGES.length)]);
		if(subject.getText().contains(GEOM))
			correct.setText(correct.getText() + ROUND_MESSAGE);
		correct.setBackground(MSG_BACKGROUND);
		
		submitted = false;
		buttonSubmit.setText(BUTTON_SUBMIT_TEXT);
		time.start();
	}
	
	private void setupGFButtons(){
	    
		buttonSubmit = new JButton(new AbstractAction(BUTTON_BEGIN_TEXT){
			public void actionPerformed(ActionEvent e){
				if(started)
					if(!submitted)
						checkAnswer();
					else
						next();
				else
					getReady();
			}
		});
		
		buttonQuit = new JButton(new AbstractAction(BUTTON_QUIT_TEXT){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		buttonSubmit.setBackground(BUTTON_BACKGROUND);
		buttonSubmit.setForeground(FOREGROUND_COLOR);
		buttonSubmit.setFont(BUTTON_FONT);
		buttonSubmit.setBorder(WHITE_BORDER);
		buttonSubmit.setEnabled(true);
		
		buttonQuit.setBackground(BUTTON_BACKGROUND);
		buttonQuit.setForeground(FOREGROUND_COLOR);
		buttonQuit.setFont(BUTTON_FONT);
		buttonQuit.setBorder(WHITE_BORDER);
	}
	
	private void setupTFButtons(){
		buttonPlay = new JButton(new AbstractAction(BUTTON_PLAY_TEXT){
			public void actionPerformed(ActionEvent e){
				titleFrame.dispose();
				checkBoxes();
				setupGameFrame();
			}
		});
		
		
		
		buttonInstructions = new JButton(new AbstractAction(BUTTON_INSTRUCTIONS_TEXT){
			
			public void actionPerformed(ActionEvent e){
				instructions.setVisible(true);
			}
		});
		
		buttonPlay.setFont(BUTTON_FONT);
		buttonPlay.setBackground(BUTTON_BACKGROUND);
		buttonPlay.setForeground(FOREGROUND_COLOR);
		buttonPlay.setBorder(WHITE_BORDER);
		
		buttonInstructions.setFont(BUTTON_FONT);
		buttonInstructions.setBackground(BUTTON_BACKGROUND);
		buttonInstructions.setForeground(FOREGROUND_COLOR);
		buttonInstructions.setBorder(WHITE_BORDER);
	}

	private void checkBoxes(){
		checkBoxes.add(arithCheck);
		checkBoxes.add(preAlgCheck);
		checkBoxes.add(algCheck);
		checkBoxes.add(geomCheck);
		
		ArrayList<JCheckBox> removed = new ArrayList<>();
		
		for(JCheckBox i:checkBoxes)
			if(!i.isSelected())
				removed.add(i);
		
		for(JCheckBox i:removed)
			if(removed.contains(i))
				checkBoxes.remove(i);
		
		if(checkBoxes.contains(arithCheck))
			subjects.add(ARITH);
		if(checkBoxes.contains(preAlgCheck))
			subjects.add(PRE_ALG);
		if(checkBoxes.contains(algCheck))
			subjects.add(ALG);
		if(checkBoxes.contains(geomCheck))
			subjects.add(GEOM);
	}
	
	private void setIndex(double weight, int y, int x, boolean tf){
		if(tf)
			c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = weight;
		c.gridx = x;
		c.gridy = y;
	}
	
	private void addTFComponents(){
		
		TFCP.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		setIndex(0.5,0,0, true);
		c.gridwidth = 2;
		TFCP.add(title, c);
		
		setIndex(0.5,1,0, true);
		c.gridwidth = 2;
		TFCP.add(name, c);
		
		setIndex(0,2,0, false);
		TFCP.add(buttonPlay, c);
		
		setIndex(0,3,0, false);
		TFCP.add(buttonInstructions, c);
		
		setIndex(0.5,4,0, true);
		TFCP.add(selectionPrompt, c);
		
		c.gridy++;
		TFCP.add(arithCheck, c);
		numCheckBoxes++;
		
		c.gridy++;
		TFCP.add(preAlgCheck, c);
		numCheckBoxes++;
		
		c.gridy++;
		TFCP.add(algCheck, c);
		numCheckBoxes++;
		
		c.gridy++;
		TFCP.add(geomCheck, c);
		numCheckBoxes++;
		
		c.gridy++;
		TFCP.add(trigCheck, c);
		numCheckBoxes++;
		
		TFCP.setBackground(BACKGROUND_COLOR);
	}
	
	private void addGFComponents(){
		GFCP.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.gridx = 0;
		
		
		//timer, subject
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		GFCP.add(timer,c);
		
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx++;
		GFCP.add(subject,c);
		
		
		//correct
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		GFCP.add(correct,c);
		c.gridwidth = 1;
		
		
		//question
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		GFCP.add(question,c);
		c.gridwidth = 1;
		
		
		//answer, buttonSubmit
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;
		c.gridx = 0;
		GFCP.add(answer,c);
		
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx++;
		GFCP.add(buttonSubmit,c);
		
		
		//score, buttonQuit
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;
		c.gridx = 0;
		GFCP.add(score,c);
		

		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx++;
		GFCP.add(buttonQuit,c);

		
		GFCP.setBackground(BACKGROUND_COLOR);
	}
	
	private void configTFFrame(){
		
		titleFrame.setTitle(TF_TITLE);
		titleFrame.setSize(TF_SIZE_X,TF_SIZE_Y + 20*numCheckBoxes);
		titleFrame.setLocation(TF_LOC_X,TF_LOC_Y);
		
		titleFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			System.exit(0);
		  	}
		} );
		
		titleFrame.setVisible(true);
	}
	
	private void configGFFrame(){
		gameFrame.setTitle(GF_TITLE);
		gameFrame.setSize(GF_SIZE_X,GF_SIZE_Y);
		gameFrame.setLocation(GF_LOC_X,GF_LOC_Y);

		gameFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			System.exit(0);
		  	}
		} );
		
		gameFrame.setVisible(true);
		
	}
	
	private void getReady(){
		Action prepare = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
		    {
				if(countdown > 0){
					timer.setText("Game starts in " + countdown + "...");
					countdown--;
				}
				else if(countdown == 0){
					timer.setText("BEGIN!");
					countdown--;
				}
				else{//after countdown
					time.start();
					ready.stop();
					started = true;
					if(!readyBoolean){
						buttonSubmit.setEnabled(true);
						buttonSubmit.setText(BUTTON_SUBMIT_TEXT);
						
						answer.setText("");
						answer.setEditable(true);
						
						checkSubjects(subjects.get(0));
						correct.setText(MESSAGE_TEXT + MESSAGES[0]);
						correct.setBackground(MSG_BACKGROUND);
						readyBoolean = true;
					}
				}
		    }
		};
		ready = new Timer(1000,prepare);
		ready.start();
		buttonSubmit.setEnabled(false);
	}
	
	private void setupTitleFrame(){
		setupTFComponents();
		setupTFButtons();
		addTFComponents();
		configTFFrame();
	}
	
	private void setupGameFrame(){
		setupGFComponents();
		setupGFButtons();
		addGFComponents();
		configGFFrame();
	}
	
	public void getStarted(){
		setupTitleFrame();
	}
}