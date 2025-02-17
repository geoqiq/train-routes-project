package org.example.trenuricaps.repo;

import org.example.trenuricaps.domain.TrainStation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainStationRepository implements Repository<TrainStation> {
    private String urlt;
    private String usernamet;
    private String passwordt;

    public TrainStationRepository(String urlt, String usernamet, String passwordt) {
        this.urlt = urlt;
        this.usernamet = usernamet;
        this.passwordt = passwordt;
    }

    @Override
    public List<TrainStation> getAll() {
        List<TrainStation> statiitren = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(urlt, usernamet, passwordt); PreparedStatement statement = connection.prepareStatement("SELECT * from \"train_stations\""); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String departure = resultSet.getString("city_id_from");
                String destination = resultSet.getString("city_id_to");
                TrainStation ts=new TrainStation(id,departure,destination);
                statiitren.add(ts);
            }
            return statiitren;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statiitren;
    }
}
