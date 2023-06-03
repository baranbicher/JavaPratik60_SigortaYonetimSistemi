package manager;

import model.abstracts.Address;
import model.concretes.BusinessAddress;
import model.concretes.HomeAddress;
import model.concretes.User;

public class AddressManager {

    public static void addAddress(User user, Address address){
        if(address.getClass() == HomeAddress.class){
            System.out.println("ev adresi eklendi");
        }
        else if(address.getClass() == BusinessAddress.class){
            System.out.println("iş adresi eklendi...");
        }

        user.getAddressList().add(address);
    }

    public static void deleteAddress(User user, Address address){
        user.getAddressList().remove(address);
    }
}
