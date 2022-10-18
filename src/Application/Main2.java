package Application;

import Model.dao.DaoFactory;
import Model.dao.DepartmentDao;
import Model.entities.Department;
import Model.entities.Seller;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        //System.out.println("=== TEST 1: department findById =====");
        //Department department = departmentDao.findById(1);
        //System.out.println(department);

        //System.out.println("\n=== TEST 2: departament findByIdDepartment =====");
        //Department department = new Department(2, null);
        //List<Department> list = departmentDao.findByDepartment(department);
        //for (Department obj : list) {
            //System.out.println(obj);
        //}

        //System.out.println("\n=== TEST 3: department findAll =====");
        //List<Department> list = departmentDao.findAll();
        //for (Department obj : list) {
            //System.out.println(obj);
        //}

        System.out.println("\n=== TEST 4: department Insert =====");
        Department newDepartment = new Department(9, "Jogos");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());

        //System.out.println("\n=== TEST 5: department Update =====");
        //Department department = departmentDao.findById(3);
        //department.setName("Jogos");
        //departmentDao.update(department);
        //System.out.println("Update Completed");

        System.out.println("\n=== TEST 6: department Delete =====");
        System.out.println("Enter id for delete teste: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete Completed");
        sc.close();
    }

}

