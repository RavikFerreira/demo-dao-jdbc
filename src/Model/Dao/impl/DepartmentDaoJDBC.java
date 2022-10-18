package Model.Dao.impl;

import Model.dao.DepartmentDao;
import Model.entities.Department;
import Model.entities.Seller;
import db.DB;
import db.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;
    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO department "
                            + "(Id , Name) "
                            + "VALUES "
                            + "(?, ?) ", Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE department "
                            + "SET Name = ? "
                            + "WHERE Id = ? ");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM department WHERE Id = ? ");

            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT department.*,department.Name as Name "
                            + "FROM department INNER JOIN department "
                            + "ON department.Id = department.Id "
                            + "WHERE department.Id = ? ");

            st.setInt(1 , id);
            rs = st.executeQuery();
            if(rs.next()){
                return instanteationDepartment(rs);
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    private Department instanteationDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT department.*,department.Name as DepName "
                            + "FROM department INNER JOIN department "
                            + "ON department.Id = department.Id "
                            + "ORDER BY Name");

            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();
            Map<Integer,Department> map = new HashMap<>();

            while(rs.next()){

                Department dep = map.get(rs.getInt("DepartmentID"));
                if(dep == null) {
                    dep = instanteationDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Department obj =  instanteationDepartment(rs);
                list.add(obj);
            }
            return  list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT department.*,department.Id as Id "
                            + "FROM department INNER JOIN department "
                            + "ON department.DepartmentId = department.Id "
                            + "WHERE Id = ? "
                            + "ORDER BY Name "
            );
            st.setInt(1 , department.getId());
            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();
            Map<Integer,Department> map = new HashMap<>();

            while(rs.next()){

                Department dep = map.get(rs.getInt("DepartmentID"));
                if(dep == null) {
                    dep = instanteationDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Department obj =  instanteationDepartment(rs);
                list.add(obj);
            }
            return  list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}

