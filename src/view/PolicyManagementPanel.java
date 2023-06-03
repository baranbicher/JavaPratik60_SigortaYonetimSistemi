package view;

import manager.AccountManager;
import manager.AddressManager;
import model.abstracts.Account;
import model.abstracts.Address;
import model.abstracts.Insurance;
import model.concretes.*;
import model.exceptions.InvalidAuthenticationException;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PolicyManagementPanel {

    private final Scanner scanner = new Scanner(System.in);
    private final AccountManager accountManager;
    public PolicyManagementPanel() throws InvalidAuthenticationException {

        accountManager = new AccountManager();
        guestUserMenu();
    }

    public void guestUserMenu() throws InvalidAuthenticationException {
        String str =
                "--------------------------------------\n" +
                        "Insurance Management Panel!\n" +
                        "1- Sign in,\n" +
                        "2- Register,\n" +
                        "0- Exit The Program!\n" +
                        "--------------------------------------";
        System.out.println(str);

        int preference = getIntegerFromMinToMaxFromUser(0,2, "Your Preference: ");

        switch (preference)
        {
            case 1 -> signInMenu();
            case 2 -> createNewUserAndAccountMenu();
            case 0 -> exitTheProgram();
        }
    }

    public void createNewUserAndAccountMenu() throws InvalidAuthenticationException {
        System.out.println("--------------------------------------\nCreate New User Menu...");

        System.out.print("Enter your e-mail: ");
        String email = scanner.nextLine();

        if(accountManager.isExist(email))
        {
            System.out.println("This email address is already registered.");
            guestUserMenu();
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        int preference = getIntegerFromMinToMaxFromUser(1,2,"Select the account type.\n1- IndividualAccount, 2- EnterpriseAccount. :");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        Account account;
        if(preference == 1) account = new IndividualAccount(user);
        else account = new EnterpriseAccount(user);

        accountManager.addNewAccount(account);

        // New client information
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        user.setFirstName(firstName);

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        user.setLastName(lastName);

        System.out.print("Enter your profession: ");
        String profession = scanner.nextLine();
        user.setProfession(profession);

        int age = getIntegerFromMinToMaxFromUser(1,120,"Enter your age: ");
        user.setAge(age);

        System.out.print("Enter your home address: ");
        String homeAdress = scanner.nextLine();
        Address home = new HomeAddress(homeAdress);
        AddressManager.addAddress(user, home);

        System.out.print("Enter your business address: ");
        String businessAdress = scanner.nextLine();
        Address business = new BusinessAddress(businessAdress);
        AddressManager.addAddress(user, business);

        registeredUserMenu(account);
    }

    private void signInMenu() throws InvalidAuthenticationException {
        SignInAssistant inAssistant = new SignInAssistant();

        Account account;

        try{
            account = accountManager.login(inAssistant.getEmail(), inAssistant.getPassword());
            registeredUserMenu(account);
        }
        catch (InvalidAuthenticationException e){
            System.out.println("Account Not Found!");
            guestUserMenu();
        }


    }

    private void registeredUserMenu(Account account) throws InvalidAuthenticationException {

        User user = account.getUser();

        if(account.getAuthenticationStatus() == Account.AuthenticationStatus.FAIL)
        {
            account.setAuthenticationStatus(Account.AuthenticationStatus.SUCCESS);
            user.setLastEntryDate(new Date());
        }

        String str =
                "--------------------------------------\n" +
                        "Insurance Management Panel!\n" +
                        "1- My user information,\n" +
                        "2- My policies,\n" +
                        "3- Add a new insurance policy,\n" +
                        "4- Sign Out!,\n" +
                        "0- Exit The Program!\n" +
                        "--------------------------------------";
        System.out.println(str);

        int preference = getIntegerFromMinToMaxFromUser(0,4, "Your Preference: ");

        switch (preference){
            case 1-> showAccountInfo(account);
            case 2-> printAllPolicies(account);
            case 3-> addNewInsuranceMenu(account);
            case 4-> signOut();
            case 0-> exitTheProgram();
        }
    }

    private void showAccountInfo(Account account) throws InvalidAuthenticationException {
        account.showUserInfo();

        registeredUserMenu(account);
    }

    private void printAllPolicies(Account account) throws InvalidAuthenticationException {
        account.printPolicies();

        registeredUserMenu(account);
    }

    private void addNewInsuranceMenu(Account account) throws InvalidAuthenticationException {
        String str =
                "--------------------------------------\n" +
                        "Insurance Purchase Menu...\n" +
                        "1- Buy Health Insurance,\n" +
                        "2- Buy Residence Insurance,\n" +
                        "3- Buy Travel Insurance,\n" +
                        "4- Buy Car Insurance,\n" +
                        "0- Back To Main Menu!\n" +
                        "--------------------------------------";
        System.out.println(str);

        int preference = getIntegerFromMinToMaxFromUser(0,4, "Your Preference: ");

        if(preference == 0) registeredUserMenu(account);
        else buyANewInsurance(account, preference);

    }

    private void buyANewInsurance(Account account, int insuranceNo) throws InvalidAuthenticationException {
        Insurance insurance;
        switch (insuranceNo){
            case 1 -> insurance = new HealthInsurance();
            case 2 -> insurance = new ResidenceInsurance();
            case 3 -> insurance = new TravelInsurance();
            default -> insurance = new CarInsurance();
        }

        Calendar cal = Calendar.getInstance();
        Date start = cal.getTime();

        cal.add(Calendar.YEAR, 1);
        Date end = cal.getTime();

        insurance.setStartDate(start);
        insurance.setEndDate(end);

        System.out.println(insurance.print());

        System.out.println("Do you want to buy? 1- Yes, 2- No : ");
        int preference = getIntegerFromMinToMaxFromUser(1,2, "Your Preference: ");

        if(preference == 1){
            account.addInsurance(insurance);
            System.out.println(insurance.getName() + " was purchased.");
        }
        else System.out.println("The purchase was abandoned.");

        registeredUserMenu(account);
    }

    private void signOut() throws InvalidAuthenticationException {
        guestUserMenu();
    }
    private void exitTheProgram(){
        System.out.println("Exiting the program...");
    }

    private int getIntegerFromMinToMaxFromUser(int min, int max, String repeatingMessage){
        int selection;
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.print(repeatingMessage);
            try {
                selection = scanner.nextInt();
                if (selection >= min && selection <= max)
                    break;
                else System.out.println("Invalid entry!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry!");
                scanner.next();
            }
        }
        return selection;
    }



}
