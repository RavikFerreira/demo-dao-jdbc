package Model.dao;

import Model.Dao.impl.DepartmentDaoJDBC;
import Model.Dao.impl.SellerDaoJDBC;
import db.DB;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static  DepartmentDao createDepartmentDao(){
        return  new DepartmentDaoJDBC(DB.getConnection());
    }
}
