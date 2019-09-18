import java.text.SimpleDateFormat;  
import java.util.Date;
public class Account{
	private String name;
	private int accountNumber;
	private long balance;
	private String password;
	public String transactions;
	private String answer;

	public Account link;
	public Account(String name, int accountNumber, int minimumBalance,String password,String answer){
		this.name = name;
		this.accountNumber = accountNumber;
		this.balance = minimumBalance;
		this.link = null;
		this.password = password;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy|HH:mm:ss");
		Date date = new Date();
		this.transactions = "Account_acreated@"+formatter.format(date)+" ";
		this.answer = answer;
	}
	public Account(String name, int accountNumber, long balance,String password,String transactions,String answer){
		this.name = name;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.link = null;
		this.password = password;
		this.transactions = transactions;
		this.answer = answer;
	}
	public void deposite(long amount){
		this.balance+=amount;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy|HH:mm:ss");
		Date date = new Date();
		this.transactions += "Account_Deposited_"+amount+"_@"+formatter.format(date)+" ";
	}
	public void withdraw(long amount){
		this.balance-=amount;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy|HH:mm:ss");
		Date date = new Date();
		this.transactions += "Account_Withdrawn_"+amount+"_@"+formatter.format(date)+" ";
	}
	public void setName(String name){
		this.name = name;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy|HH:mm:ss");
		Date date = new Date();
		this.transactions += "Account_ChangedNameTo_"+name+"_@"+formatter.format(date)+" ";
	}
	public String getName(){
		return this.name;
	}
	public long getBalance(){
		return this.balance;
	}
	public int getAccountNumber(){
		return this.accountNumber;
	}
	public String getAnswer(){
		return this.answer;
	}
	public void setPassword(String password){
		this.password = password;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy|HH:mm:ss");
		Date date = new Date();
		this.transactions += "Account_PasswordChanged_"+name+"_@"+formatter.format(date)+" ";
	}
	public String getPassword(){
		return this.password;
	}
	public String getTransactions(){
		return this.transactions;
	}
	public String toString(){
		return "Account No : "+accountNumber+" Name : "+name+" Balance : "+balance+" Password : "+password+" Answer : "+answer;
	}
}
