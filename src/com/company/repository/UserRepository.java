package com.company.repository;

import com.company.database.interfaces.IDB;
import com.company.entities.Necklace;
import com.company.entities.Stone;
import com.company.repository.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addStone(Stone stone) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO Stones(name, price, weight)" +
                    "VALUES(?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,stone.getName());
            st.setInt(2,stone.getPrice());
            st.setInt(3,stone.getWeight());

            boolean executed = st.execute();
            return executed;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteStone(int id) {
        Connection con = null;
        try {
            con = db.getConnection();

            String sql = "DELETE FROM Stones WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            boolean executed = st.execute();
            return executed;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public Stone getStoneById(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id, name, ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Stone stone = new Stone(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("weight"));
                return stone;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getNecklaceById(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id, name, weight, price, stones" +
                    "FROM Necklace" +
                    "WHERE id = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            List<String> stones = new ArrayList<String>();

            ResultSet rs = st.executeQuery();
            if(rs.next()){
                String output = rs.getInt("id")+
                        rs.getString("name")+
                        rs.getString("stones")+
                        rs.getInt("weight")+
                        rs.getInt("price");
                return output;
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return "something went wrong";
    }

    @Override
    public boolean createNecklace(Necklace necklace,int amountOfStones ) {
        Connection con = null;
        try {
            con = db.getConnection();

            List<Stone> stones = new ArrayList<>();

            for(int i = 0;i<amountOfStones;i++){
                String sql = "SELECT id, name, price, weight " +
                        "FROM Stones " +
                        "WHERE id = ?";
                PreparedStatement st = con.prepareStatement(sql);

                st.setInt(1,i);

                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    Stone stone = new Stone(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("weight"));
                    stones.add(stone);
                }

                }
            String seql ="INSERT INTO employee(name, stones,weight,price) VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(seql);
            statement.setString(1, necklace.getName());
            statement.setArray(2, (Array) stones);
            statement.setInt(3,necklace.getWeight());
            statement.setInt(4,necklace.getPrice());

            boolean executed = statement.execute();

            return executed;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean deleteNecklace(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM Necklace WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            boolean executed = st.execute();
            return executed;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }
}
