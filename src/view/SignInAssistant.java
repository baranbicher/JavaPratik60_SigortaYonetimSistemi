package view;


import java.util.Scanner;

public class SignInAssistant {

    private Scanner scanner = new Scanner(System.in);

    private String email, password;

    public SignInAssistant() {
        signIn();
    }

    private void signIn(){
        System.out.print("Please enter your e-mail: ");
        email = scanner.nextLine().trim();

        System.out.print("Please enter your password: ");
        password = scanner.nextLine().trim();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
