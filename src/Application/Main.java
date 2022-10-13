package Application;

import Model.dao.DaoFactory;
import Model.dao.SellerDao;
import Model.entities.Department;
import Model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById =====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findByIdDepartment =====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list) {
            System.out.println(obj);

        }

        System.out.println("\n=== TEST 3: seller findByIdDepartment =====");
        list = sellerDao.findAll();
        for (Seller obj : list) {
            System.out.println(obj);
        }
        System.out.println("\n=== TEST 4: seller findByIdDepartment =====");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = "+ newSeller.getId()) ;

        System.out.println("\n=== TEST 5: seller findByIdDepartment =====");
        seller = sellerDao.findById(1);
        seller.setName("Martha Wainer");
        sellerDao.update(seller);
        System.out.println("Update Completed");

        System.out.println("\n=== TEST 5: seller findByIdDepartment =====");
        System.out.println("Enter id for delete teste: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete Completed");
        sc.close();


    }
}