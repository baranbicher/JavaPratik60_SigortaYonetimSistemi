package model.concretes;

import model.abstracts.Account;

public class EnterpriseAccount extends Account implements Comparable<EnterpriseAccount> {
    public EnterpriseAccount(User user) {
        super(user);
    }

    @Override
    public void printPolicies() {
        System.out.println("Your Enterprise Policies...");
        super.printPolicies();
    }

    @Override
    public int compareTo(EnterpriseAccount o) {
        return 0;
    }
}
