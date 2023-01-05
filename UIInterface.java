package database;

import java.sql.SQLException;
import java.util.Scanner;

public class UIInterface {
	public static void start() 
			throws ClassNotFoundException, SQLException {
		
		Scanner scan = new Scanner(System.in);
		
		DatabaseInterface.DbConnect();
		
		System.out.println("***** Welcome to Nanavati Hospital *****");
		while(true) {
			System.out.println("Select the operation to perform:");
			System.out.println("1. Show All Patients Information");
			System.out.println("2. Find Patient by id");
			System.out.println("3. Add another Patient");
			System.out.println("4. Update Patient Details");
			System.out.println("5. Remove Patient");
			System.out.println("6. Find By Patient Name");
			System.out.println("0. Quit");
			System.out.print("Enter Your Choice: ");
			
			int choice = scan.nextInt();
			
			if(choice == 0) {
				System.out.println("\n\n***** THANK YOU FOR VISITNG(WON'T SAY VISIT AGAIN) ***** \n\n");
				break;
			}
			else {
				int id, fee;
				int od, da, fb;
				
				String pname;
				String Dname;
				String Diseasetype;
				
				switch(choice) {
				case 1: System.out.println(DatabaseInterface.getAll());
						break;
				case 2: System.out.print("Provide the ID of the patient: ");
						id = scan.nextInt();
						System.out.println(DatabaseInterface.getById(id));
						break;
				case 3: System.out.print("Enter ID: ");
						id = scan.nextInt();
						System.out.print("Enter Patient Name: ");
						pname = scan.next();
						//System.out.print("Enter Price: ");
						//price = scan.nextDouble();
						//System.out.print("Enter Quantity: ");
						//quantity = scan.nextInt();
						System.out.print("Enter Disease Type: ");
						Diseasetype = scan.next();
						System.out.print("Enter Docotr's Name: ");
						Dname = scan.next();
						System.out.print("Enter Days admitted: ");
						da = scan.nextInt();
						System.out.print("Enter One Day Charge: ");
						od = scan.nextInt();
						
						fb = da*od;
						System.out.println(DatabaseInterface.add(id, pname, Diseasetype, Dname, da, od, fb));
						break;
				case 4: System.out.print("Provide the ID of the patient to be updated: ");
						id = scan.nextInt();
						System.out.println("Choose the detail to be updated:");
						System.out.println("1. Patient Name");
						System.out.println("2. Disease Type");
						System.out.println("3. Doctor Name");
						System.out.println("4. Days Admitted");
						System.out.println("5. One Day Charge");
						
						System.out.print("Enter Your Choice: ");
						int upChoice = scan.nextInt();
						switch(upChoice) {
							case 1: System.out.print("Enter New Patient Name: ");
									pname = scan.next();
									System.out.println(DatabaseInterface.updatePName(id, pname));
									break;
							case 2: System.out.print("Enter New Disease Type: ");
									Diseasetype = scan.next();
									System.out.println(DatabaseInterface.updateDt(id, Diseasetype));
									break;
							case 3: System.out.print("Enter New Doctor Name: ");
									Dname = scan.next();
									System.out.println(DatabaseInterface.updateDname(id, Dname));
									break;
									
							case 4: System.out.print("Enter updated Days Admitted: ");
									da = scan.nextInt();
									System.out.println(DatabaseInterface.updateda(id, da));
							break;
							
							case 5: System.out.print("Enter updated One Day Charge: ");
							od = scan.nextInt();
							System.out.println(DatabaseInterface.updateda(id, od));
							break;
							default: System.out.println("\n\n *** WRONG CHOICE *** \n\n");
						}
						break;
				case 5: System.out.print("Provide the ID of the patient to be deleted: ");
						id = scan.nextInt();
						System.out.println(DatabaseInterface.delete(id));
						break;
				case 6: System.out.println("Provide the name of patient:");
				 		pname = scan.next();
				 		System.out.println(DatabaseInterface.getByName(pname));
				 		break;
				default: System.out.println("\n\n *** Put valid number *** \n\n");
				}
			}			
		}
		DatabaseInterface.DbDisconnect();
	}
}
