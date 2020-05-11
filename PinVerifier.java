import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class PinVerifier{
	public static void main(String[] args){
		//Initializing scanner to scan.
		//Initializing boolean iterate to  determine when to stop the loop.
		//String message will contain whether the pin is correct/incorrect.
		//userPin is the pin entered by the user.
		//correctPins is a dynamic array, in which every loop iterated has the potential to store a correct pin inside of it.

		Scanner sc = new Scanner(System.in);
		boolean iterate = true;
		String message = "";
		String userPin = "";
		ArrayList<String> correctPins = new ArrayList<String>();
		//While loop to loop over if statements to determine the message to output and to potentially add the pin to the correctPins ArrayList.
		while(iterate == true){
			//First if statement is to determine if the user needs to enter an initial pin. By the end of the loop, user gets asked if he wants to continue by entering a pin.
			//In the case that the user enters a pin to check another pin, it will skip this first if statement asking for an initial pin.

			if(userPin.equals("")){
				System.out.print("Enter pin: ");
				userPin = sc.nextLine();
			}
			//int[] userPinInt will have the userPin entered converted as integers. 
			//n will be the larget number in the pin.
			//k will be the far left value in the pin

			int[] userPinInt = new int[userPin.length()];
			int n = 0;
			int k = 0;

			//for loop to loop over userPin characters, get the numeric value and pass into the userPinInt array.

			for(int i = 0; i < userPin.length(); i++){
				userPinInt[i] = Character.getNumericValue(userPin.charAt(i));

				//if statement to determine largest number in the userPin.

				if(userPinInt[i] > n){
					n = userPinInt[i];
				}
			}
			k = userPinInt[0];

			// will obtain the last four digits in the array.

			int lastFourDigits = userPinInt[userPin.length() - 1] + userPinInt[userPin.length() - 2] + userPinInt[userPin.length() - 3] + userPinInt[userPin.length() - 4];

			//If statements to determine the message.
			//First statement determines the length and if n and k will hold a remainder so that it can continue and assure that n and k are even numbers.
			if((userPinInt.length >= 5) && (userPinInt.length <=7) && (n % 2 == 0) && (k % 2 ==0)){
				//We know that userPin is valid, so continue to below.
				//for loop to determine if the are consecutive numbers.
				for(int i = 0; i < userPinInt.length - 1; i++){
					if(userPinInt[i+1] != userPinInt[i]){
						message = "That pin is valid.";

					}else if(userPinInt[i+1] == userPinInt[i]){
						message = "Your pin is invalid because it has consecutive repeating characters.";
						break;
					}
				}
				//If statements to determine if the pin was valid so that the pin can get added to correctPins array.
				if(message.equals("That pin is valid.")){
					correctPins.add(userPin);
				}
			 //Determining the length of error message.
			 }else if((userPinInt.length < 5) || (userPinInt.length > 7)){
			 	message = "The pin must be between 5 and 7 characters long, so yours is invalid.";
			 //If userPin is divisible by four, obtain the message and add the userPin if valid.
			 }else if ((lastFourDigits % 4 == 0) && (n % 2 == 0) && ((k % 2 == 0))){
		 		message = "That pin is valid.";
		 		correctPins.add(userPin);
			 }else if(lastFourDigits % 4 > 0){
				message = "The sum of the last four digits must be divisible by four, but your pin doesn't meet that criterion.";
			 //Determining if the pin fails n-choose-k
			 }else if((n % 2 > 0) && (k % 2 > 0)){
			 	message = "Your pin failes to meet the n-choose-k criterion, so it is invalid.";
			 }

			//playAgain string will hold the next pin in the loop if the user wants to continue
			//The message if the pin was accepted/rejected will print
			//If statement to determine to quit. If not -1 was entered, then the corrected pins are printed.
			String playAgain;
			System.out.println(message);
			System.out.print("Enter another pin, or -1 to quit: ");
			playAgain = sc.nextLine();
			if(playAgain.equals("-1")){
				iterate = false;
				System.out.print("These were the valid pins: ");
				for(int i = 0; i < correctPins.size(); i++){
					System.out.print(correctPins.get(i) + " ");
				}
			}else{
				userPin = playAgain;
			}

		}
	}
}

