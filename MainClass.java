import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class MainClass{
	public static void clearScreen() {  
    	System.out.print("\033[H\033[2J");  
    	System.out.flush();  
	}  
	public static void help(){
		System.out.println("\033[1;37m"+"\n For creating account: add \n For removing: remove \n For depositing to: deposite\n For withdraw: withdraw\n For changing name in account: changeName\n If forget password: forget\n For displaying all accounts : display\n For getting all transactions : transaction\n For help: help / ?\n For destroying Bank: destroy / exit"+"\033[0m");
	}
	public static void add(Bank sbi, BufferedReader br)throws IOException{
		System.out.println("\033[1;32m"+" Enter Account Holder Name "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(add)$ "+"\033[0m");
		String n = br.readLine();
		StringTokenizer s = new StringTokenizer(n);
		String name ;
		int balance;
		String answer;
		if(s.countTokens()>1){
			name = s.nextToken();
			while(s.hasMoreTokens()){
				name=name+"_"+s.nextToken();
			}
		}else{
			name = n;
		}
		try{
			System.out.println("\033[1;32m"+" Enter balace to deposite "+"\033[0m");
			System.out.print("\033[1;32m"+" %$(add)$ "+"\033[0m");
			balance  = Integer.parseInt(br.readLine());
		}catch(Exception e){
			System.out.println("\033[1;31m"+" Invalid input exiting sorry !"+"\033[0m");
			return ;
		}
		try{
			System.out.println("\033[1;32m"+" What is your favourite Pet Name(recovery question)"+"\033[0m");
			System.out.print("\033[1;32m"+" %$(add)$ "+"\033[0m");
			String a = br.readLine();
			StringTokenizer t = new StringTokenizer(a);
			if(t.countTokens()>1){
				a = t.nextToken();
				while(t.hasMoreTokens()){
					a = a+"_"+t.nextToken();
				}
				answer = a;
			}else{
				answer = a;
			}
		}catch(Exception e){
			System.out.println("\033[1;31m"+" Invalid input exiting sorry !"+"\033[0m");
			return;
		}
		Explain r = sbi.addAccount(name,balance,answer);
		System.out.println("\033[1;31m"+r.result+" ? "+r.reason+"\033[0m");
	}
	public static void remove(Bank sbi, BufferedReader br)throws IOException{
		System.out.println("\033[1;32m"+" Enter Account Number "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(remove)$ "+"\033[0m");
		int account;
		try{
			account = Integer.parseInt(br.readLine());
		}catch(Exception e){
			System.out.println("\033[1;31m"+" Invalid input exiting sorry !"+"\033[0m");
			return;
		}
		System.out.println("\033[1;32m"+" Enter password Number "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(remove)$ "+"\033[0m");
		String password = br.readLine();
		Explain r = sbi.terminateAccount(account,password);
		System.out.println("\033[1;31m"+r.result+" ? "+r.reason+"\033[0m");
	}
	public static void deposite(Bank sbi,BufferedReader br)throws IOException{
		System.out.println("\033[1;32m"+" Enter Account Number "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(deposite)$ "+"\033[0m");
		int account;
		String password;
		long amount;
		try{
			account = Integer.parseInt(br.readLine());
		}catch(Exception e){
			System.out.println("\033[1;31m"+" Invalid input exiting sorry !"+"\033[0m");
			return;
		}
		System.out.println("\033[1;32m"+" Enter Your Password "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(deposite)$ "+"\033[0m");
		password = br.readLine();
		try{
			System.out.println("\033[1;32m"+" Enter Your amount to deposite "+"\033[0m");
			System.out.print("\033[1;32m"+" %$(deposite)$ "+"\033[0m");
			amount = Long.parseLong(br.readLine());
		}catch(Exception e){
			System.out.println("\033[1;31m"+" Invalid input exiting sorry !"+"\033[0m");
			return;
		}
		Explain r = sbi.deposite(account,password,amount);
		System.out.println("\033[1;31m"+r.result+" ? "+r.reason+"\033[0m");
	}
	public static void withdraw(Bank sbi, BufferedReader br)throws IOException{
		System.out.println("\033[1;32m"+" Enter Account Number "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(withdraw)$ "+"\033[0m");
		int account;
		String password;
		long amount;
		try{
			account = Integer.parseInt(br.readLine());
		}catch(Exception e){
			System.out.println("\033[1;31m"+" Invalid input exiting sorry !"+"\033[0m");
			return;
		}
		System.out.println("\033[1;32m"+" Enter Your Password "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(withdraw)$ "+"\033[0m");
		password = br.readLine();
		try{
			System.out.println("\033[1;32m"+" Enter Your amount to withdraw "+"\033[0m");
			System.out.print("\033[1;32m"+" %$(withdraw)$ "+"\033[0m");
			amount = Long.parseLong(br.readLine());
		}catch(Exception e){
			System.out.println("\033[1;31m"+" Invalid input exiting sorry !"+"\033[0m");
			return;
		}
		Explain r = sbi.withdraw(account,password,amount);
		System.out.println("\033[1;31m"+r.result+" ? "+r.reason+"\033[0m");
	}
	public static void changeName(Bank sbi, BufferedReader br)throws IOException{
		System.out.println("\033[1;32m"+" Enter Account Number "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(Change Name)$ "+"\033[0m");
		int account;
		String password;
		String name;
		try{
			account = Integer.parseInt(br.readLine());
		}catch(Exception e){
			System.out.println("\033[1;31m"+" Invalid input exiting sorry !"+"\033[0m");
			return;
		}
		System.out.println("\033[1;32m"+" Enter Your Password "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(withdraw)$ "+"\033[0m");
		password = br.readLine();
		System.out.println("\033[1;32m"+" Enter Your Name to change "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(withdraw)$ "+"\033[0m");
		name = br.readLine();
		Explain r = sbi.changeName(account,password,name);
		System.out.println("\033[1;31m"+r.result+" ? "+r.reason+"\033[0m");
	}
	public static void forget(Bank sbi, BufferedReader br)throws IOException{
		System.out.println("\033[1;32m"+" Enter Account Number "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(forget)$ "+"\033[0m");
		int account;
		String answer;
		try{
			account = Integer.parseInt(br.readLine());
		}catch(Exception e){
			System.out.println("\033[1;31m"+" Invalid input exiting sorry !"+"\033[0m");
			return;
		}
		System.out.println("\033[1;32m"+" Enter your answer to question : What is your favourite Pet Name(recovery question) "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(forget)$ "+"\033[0m");
		answer = br.readLine();
		Explain r = sbi.forgetPassword(account,answer);
		System.out.println("\033[1;31m"+r.result+" ? "+r.reason+"\033[0m");
	}
	public static void exit(Bank sbi)throws InterruptedException,IOException{
		sbi.clos();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("\033[1;31m"+"Bank went damaging internally 😥️"+"\033[0m");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("\033[1;31m"+"Situation seems uncontrollable 😩️"+"\033[0m");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("\033[1;31m"+"Sorry our bank went collapsed and but your money is safe run again to reconstruct bank "+"\033[0m");
		return;
	}
	public static void transaction(Bank sbi, BufferedReader br)throws IOException{
		System.out.println("\033[1;32m"+" Enter Account Number "+"\033[0m");
		System.out.print("\033[1;32m"+" %$(transaction)$ "+"\033[0m");
		int account;
		try{
			account = Integer.parseInt(br.readLine());
		}catch(Exception e){
			System.out.println("\033[1;31m"+" Invalid input exiting sorry !"+"\033[0m");
			return;
		}
		Explain r = sbi.getTransactions(account);
		System.out.println("\033[1;31m"+r.result+" ? "+r.reason+"\033[0m");
	}
	public static void main(String args[])throws IOException,InterruptedException{
		FileReader fr = new FileReader("data.txt");
		BufferedReader b = new BufferedReader(fr);
		String line;
		Bank sbi = new Bank("SBI");
		while((line=b.readLine())!=null){
			StringTokenizer st = new StringTokenizer(line);
			int accountNumber = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			long balance = Long.parseLong(st.nextToken());
			String password = st.nextToken();
			String answer = st.nextToken();
			String transactions="";
			while(st.hasMoreTokens()){
				transactions = transactions+" "+st.nextToken();
			}
			sbi.addAccount(name,balance,accountNumber,password,transactions,answer);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		clearScreen();
		System.out.println("Welcome to Bank Management System designed by Dumb Minds............");
		System.out.print("Enter your bank name : ");
		String b_name = br.readLine();
		System.out.print("Enter your bank Minimum balanace for creating Account or make default by pressing enter enter : ");
		int min_bal;
		try{
			min_bal = Integer.parseInt(br.readLine());
		}catch(Exception e){
			min_bal = 500;
			System.out.println("Error occured due to user input, taking default");
		}
		System.out.print("Enter your bank Minimum deposite amount or make defalt by presing enter.");
		int min_dep;
		try{
			min_dep = Integer.parseInt(br.readLine());
		}catch(Exception e){
			min_dep = 100;
			System.out.println("Error occured due to user input, taking default");
		}
		System.out.print("Do you want to enter additional details Y/N");
		String in = br.readLine();
		String address = "";
		String email = "";
		String managerName = "";
		if(in.equals("Y") || in.equals("")|| in.equals("y")){
			System.out.print("Enter Address : ");
			address = br.readLine();
			System.out.print("Enter email : ");
			email = br.readLine();
			System.out.print("Enter manager name : ");
			managerName = br.readLine();
		}
		TimeUnit.SECONDS.sleep(1);
		sbi.update(b_name,min_bal,min_dep,address,email,managerName);
		clearScreen();
		help();
		while(true){
			System.out.print("\033[1;32m"+" %$$ "+"\033[0m");
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input);
			if(st.countTokens()==1){
				String n = st.nextToken().toUpperCase();
				switch(n){
					case "ADD":
						add(sbi,br);
						break;
					case "REMOVE":
						remove(sbi,br);
						break;
					case "DEPOSITE":
						deposite(sbi,br);
						break;
					case "WITHDRAW":
					case "WITH DRAW":
						withdraw(sbi,br);
						break;
					case "CHANGENAME":
					case "CHANGE NAME":
						changeName(sbi,br);
						break;
					case "FORGET":
						forget(sbi,br);
						break;
					case "HELP":
					case "?":
						help();
						break;
					case "DESTROY":
					case "EXIT":
						exit(sbi);
						return;
					case "DISPLAY":
						sbi.display();
						break;
					case "TRANSACTION":
						transaction(sbi,br);
						break;
					default:
						help();
				}
			}else{
				System.out.println("Invalid no of parameters");
			}
		}
	}
}
