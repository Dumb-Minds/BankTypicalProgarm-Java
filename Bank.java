import java.util.Random;
import java.io.*;
import java.util.*;
public class Bank{
	//All are accounts details
	public String name;
	public Account first;
	public Account last;
	public int minimumBalance;
	public int minimumDeposite;
	public long totalAmount;
	
	//Social details
	public String address;
	public String email;
	public String managerName;
	

	public Bank(String name){
		this.name = name;
		this.last = null;
		this.first = null;
	}
	public void update(String name,int minimumBalance, int minimumDeposite,String address, String email, String manager){
		this.name = name;
		this.totalAmount = 0; 
		this.minimumBalance = minimumBalance;
		this.minimumDeposite = minimumDeposite;
		this.address = address;
		this.email = email;
		this.managerName = manager;
	}
	private static int getRandomNumberInRange(int min, int max){
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	private static String generateRandomPassword(){
		Random r = new Random();
		String password="";
		String userset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890&%$#@!*?";
		for(int i=0;i<8;i++){
			password+=userset.charAt(r.nextInt(userset.length()));
		}
		return password;
	}
	public Explain addAccount(String name,int minimumBalance, String answer){
		Explain r = new Explain();
		if(this.minimumBalance>minimumBalance){
			r.result = false;
			r.reason = "Minimum Balance not suffecient";
			return r;
		}
		Account q = first;
		int random = getRandomNumberInRange(100000000,999999999);
		while(q!=null){
			if(q.getAccountNumber()== random){
				q = first;
				random = getRandomNumberInRange(100000000,999999999);
			}
			q = q.link;
		}
		String password;
		if(first == null && last == null){
			password = generateRandomPassword();
			Account p = new Account(name,random,minimumBalance,password,answer);
			r.reason = "Account Added with Number : "+p.getAccountNumber()+" Name : "+p.getName()+" Password : "+p.getPassword();
			totalAmount+=minimumBalance;
			first = p;
			last = p;
			r.result = true;
			return r;
		}else{
			password = generateRandomPassword();
			Account l = new Account(name,random,minimumBalance,password,answer);
			r.reason = "Account Added with Number : "+l.getAccountNumber()+" Name : "+l.getName()+" Password : "+l.getPassword();
			totalAmount+=minimumBalance;
			last.link = l;
			last = l;
			r.result = true;
			return r;	
		}
	}
	public Explain addAccount(String name,long balance, int accountNumber,String password,String transactions,String answer){
		Explain r = new Explain();
		if(first == null && last == null){
			Account p = new Account(name,accountNumber,balance,password,transactions,answer);
			totalAmount+=minimumBalance;
			first = p;
			last = p;
			r.result = true;
			r.reason = "Account Added";
			return r;
		}else{
			totalAmount+=minimumBalance;
			Account l = new Account(name,accountNumber,balance,password,transactions,answer);
			last.link = l;
			last = l;
			r.result = true;
			r.reason = "Account Added";
			return r;	
		}
	}
	public Explain terminateAccount(int accountNumber,String password){
		Explain r = new Explain();
		if((first.getAccountNumber() == accountNumber) && first.getPassword().equals(password)){
			totalAmount-=first.getBalance();
			r.reason = "Account with Number :"+first.getAccountNumber()+" Name : "+first.getName()+" is terminated";
			first = first.link;
			r.result = true;
			return r;
		}else{
			Account p = first;
			while(p.link!=null){
				if((p.link.getAccountNumber() == accountNumber) && p.link.getPassword().equals(password)){
					r.result = true;
					r.reason = "Account with Number : "+p.link.getAccountNumber()+" Name : "+p.link.getName()+" is terminated";
					p.link = p.link.link;
					Account y = first;
					while(y.link!=null){
						y = y.link;
					}
					last = y;
					return r;
				}
				p = p.link;
			}
			r.result = false;
			r.reason = "Account Not Found with the given details";
			return r;
		}
	}
	public Explain deposite(int accountNumber,String password, long amount){
		Explain r = new Explain();
		if(amount < this.minimumDeposite){
			r.result = false;
			r.reason = "Minimum Balace for deposite is "+this.minimumDeposite;
			return r;
		}
		Account p = first;
		while(p!=null){
			if(p.getAccountNumber() == accountNumber && p.getPassword().equals(password)){
				p.deposite(amount);
				r.result = true;
				r.reason = "Deposited "+amount+" into account "+p.getAccountNumber();
				totalAmount+=amount;
				return r;
			}
			p = p.link;
		}
		r.result = false;
		r.reason = "Account Not Found with given details";
		return r;
	}
	public Explain withdraw(int accountNumber,String password, long amount){
		Explain r = new Explain();
		Account p = first;
		while(p!=null){
			if(p.getAccountNumber() == accountNumber && p.getPassword().equals(password)){
				if((p.getBalance()-amount) < minimumBalance){
					r.result = false;
					r.reason = "Not having sufficient balance to remove "+amount;
					return r;
				}
				p.withdraw(amount);
				r.result = true;
				r.reason = "Withdrawn "+amount+" from "+p.getAccountNumber();
				totalAmount-=amount;
				return r;
			}
			p = p.link;
		}
		r.result = false;
		r.reason = "Account Not Fount with Given details";
		return r;
	}
	public Explain changeName(int accountNumber, String password, String name){
		Explain r = new Explain();
		Account p = first;
		while(p!=null){
			if(p.getAccountNumber() == accountNumber && p.getPassword().equals(password)){
				r.reason = "Name Changed from "+p.getName()+" to "+name;
				p.setName(name);
				r.result = true;
				return r;
			}
			p = p.link;
		}
		r.result = false;
		r.reason = "Acocunt Not Found with Given details";
		return r;
	}
	public Explain forgetPassword(int accountNumber,String answer){
		Explain r = new Explain();
		Account p = first;
		while(p!=null){
			if(p.getAccountNumber() == accountNumber && p.getAnswer().equals(answer)){
				String password = generateRandomPassword();
				r.reason = "Password Changed from "+p.getPassword()+" to "+password;
				p.setPassword(password);
				r.result = true;
				return r;
			}
			p = p.link;
		}
		r.result = false;
		r.reason = "Acocunt Not Found with Given details";
		return r;
	}
	public Explain getTransactions(int accountNumber){
		Explain r = new Explain();
		Account p  = first;
		while(p!=null){
			if(p.getAccountNumber()==accountNumber){
				StringTokenizer st = new StringTokenizer(p.getTransactions());
				while(st.hasMoreTokens()){
					System.out.println("\033[1;31m"+" "+st.nextToken()+"\033[0m");
				}
				r.result = true;
				r.reason = "All transactions for account "+p.getAccountNumber();
				return r;
			}
			p = p.link;
		}
		r.result = false;
		r.reason = "Account Not Found";
		return r;
	}
	public void display(){
		Account p = first;
		while(p!=null){
			System.out.println(p);
			p = p.link;
		}
	}
	public void clos()throws IOException{
		FileWriter fw = new FileWriter("data.txt");
		Account p = first;
		while(p!=null){
			fw.write(p.getAccountNumber()+" "+p.getName()+" "+p.getBalance()+" "+p.getPassword()+" "+p.getAnswer()+" "+p.transactions+"\n");
			p = p.link;
		}
		fw.close();
	}
}
