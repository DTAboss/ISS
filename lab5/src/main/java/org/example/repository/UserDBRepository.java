package org.example.repository;

import javafx.scene.chart.ScatterChart;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDBRepository implements UserRepository{

    private String url;
    private String username;
    private String password;

    public UserDBRepository(String url,String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        return new User(id,username,password);
    }

    @Override
    public Optional<User> findById(long id) {
        String query = "select * from users where id = ?";
        try (Connection con = DriverManager.getConnection(url,username,password);)
        {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return Optional.of(extractUser(rs));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        String query = "select * from users";
        List<User> users = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(url, username, password)){
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                users.add(extractUser(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) {
        String query = "insert into users(username,password) values(?,?)";
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void delete(User user) {
        String query = "delete from users where id=?";
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, user.getId());
            ps.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String query = "UPDATE users SET username=? ,password=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url,username,password);PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setLong(3, user.getId());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

        @Override
        public Optional<User> findUser(String username, String password) {
            String query = "Select * from users where username=? and password=?";
            try (Connection connection = DriverManager.getConnection(url, this.username, this.password)) {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return Optional.of(extractUser(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }
    }
