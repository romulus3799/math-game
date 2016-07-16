import java.util.Random;

public class Sequence {
	private int[] nums;
	private int operation;
	private Random rngesus;
	private static final int START_RANGE = 20;
	private int change;
	private static final int CHANGE_RANGE = 12;
	private static final int MULT_RATE = 5;
	
	public Sequence(){
		nums = new int[4];
		operation = 0;
		rngesus = new Random();
		change = 0;
	}
	
	public void generate(){
		operation = rngesus.nextInt(3);
		nums[0] = START_RANGE-rngesus.nextInt(2*START_RANGE + 1);
		if(operation == 2)
			change = MULT_RATE-rngesus.nextInt(2*MULT_RATE + 1);
		else
			change = CHANGE_RANGE-rngesus.nextInt(2*CHANGE_RANGE + 1);
		
		if(operation == 0){
			for(int i = 1; i < nums.length; i++)
				nums[i] = nums[i-1]+change;
		}
		else if(operation == 1){
			for(int i = 1; i < nums.length; i++)
				nums[i] = nums[i-1]-change;
		}
		else{
			for(int i = 1; i < nums.length; i++)
				nums[i] = nums[i-1]*change;
		}
	}
	
	public int[] getNums(){return nums;}
	
	public int getNext(){
		if(operation == 0)
			return nums[nums.length-1]+change;
		else if(operation == 1)
			return nums[nums.length-1]-change;
		else
			return nums[nums.length-1]*change;
	}
	
	public String toString(){
		String result = "";
		for(int i:nums)
			result += i + ", ";
		return result.substring(0,result.length()-2);
	}
}