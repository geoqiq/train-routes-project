package org.example.trenuricaps.repo;

import org.example.trenuricaps.domain.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository implements Repository<City>{
    private String urlc;
    private String usernamec;
    private String passwordc;

    public CityRepository(String urlc, String usernamec, String passwordc) {
        this.urlc = urlc;
        this.usernamec = usernamec;
        this.passwordc = passwordc;
    }

    @Override
    public List<City> getAll() {
        List<City> orase = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlc, usernamec, passwordc); PreparedStatement statement = connection.prepareStatement("SELECT * from \"cities\""); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");

                City client=new City(id,name);
                orase.add(client);
            }
            return orase;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orase;
    }
}
