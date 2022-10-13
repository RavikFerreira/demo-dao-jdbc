package Application;

import Model.entities.Department;
import Model.entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Department obj = new Department(1,"BOOK" );

        Seller seller =  new Seller(21, "BOB", "bob@gmail.com", new Date(), 3000.0, obj);

        System.out.println(seller);
    }
}