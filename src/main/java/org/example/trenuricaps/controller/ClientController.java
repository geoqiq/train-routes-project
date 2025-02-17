package org.example.trenuricaps.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.trenuricaps.domain.City;
import org.example.trenuricaps.domain.TrainStation;
import org.example.trenuricaps.service.Service;
import org.example.trenuricaps.utils.observer.Observer;
import org.example.trenuricaps.validators.ComboBoxValidator;
import org.example.trenuricaps.validators.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class ClientController implements Observer {
    private Service service;
    @FXML
    private ComboBox<String> departureCombo;
    @FXML
    private ComboBox<String> destinationCombo;
    @FXML
    private Button searchButton;

    @FXML
    private ListView<String> listView;
    @FXML
    private Label textCheck = new Label();
    @FXML
    private CheckBox checkBox;

    private final ComboBoxValidator validator = new ComboBoxValidator();
    private String userId;
    private final Double basePrice = 10.0;

    private Double priceCalc(Integer nrOfStations) { return basePrice * nrOfStations; }

    ObservableList<String> model = FXCollections.observableArrayList();

    public void setService(Service service) {
        this.service = service;
        initializeModel();
        service.addObserver(this);
        this.userId = UUID.randomUUID().toString();
    }

    @FXML
    public void initialize() { listView.setItems(model); }

    private void initializeModel() {
        setCombo();
        updateCheck();
    }

    private void updateCheck() {
        if (departureCombo.getValue() != null && destinationCombo.getValue() != null) {
            String from = departureCombo.getValue();
            String to = destinationCombo.getValue();
            Integer cnt = service.nrOfUsers(from, to);
            textCheck.setText("There are " + cnt.toString() + " other user(s) looking at the same route.");
        } else {
            textCheck.setText("You have no searches at the moment.");
        }
    }

    public void setCombo() {
        Set<String> from = new HashSet<>();
        Set<String> to = new HashSet<>();
        for (City c : service.getCities()) {
            from.add(c.getName());
            to.add(c.getName());
        }
        departureCombo.getItems().addAll(from);
        destinationCombo.getItems().addAll(to);
    }

    private void showAlert(String message) {
        Stage stage = (Stage) departureCombo.getScene().getWindow();
        MessageAlert.showErrorMessage(stage, message);
    }

    public void handleSearch() {
        try {
            validator.validate(departureCombo.getValue());
            validator.validate(destinationCombo.getValue());

            String from = departureCombo.getValue();
            String to = destinationCombo.getValue();

            model.clear();
            List<List<String>> paths;
            if (checkBox.isSelected()) {
                paths = directPaths(from, to);
            } else {
                paths = allPaths(from, to);
            }
            for (List<String> path : paths) {
                StringBuilder pathString = new StringBuilder();
                for (int i = 0; i < path.size(); i++) {
                    if (i > 0) {
                        pathString.append(" -> ");
                    }
                    pathString.append(path.get(i));
                }
                model.add(pathString.toString() + ", at the price of " + priceCalc(path.size() - 1));
            }
            service.addSearch(userId, from, to);
            service.notifyObservers();
        } catch (ValidationException e) {
            showAlert(e.getMessage());
        }
    }

    private List<List<String>> directPaths(String from, String to) {
        List<List<String>> paths = new ArrayList<>();
        for (TrainStation ts : service.getTrainStations()) {
            String departure = service.getNameById(ts.getDeparture());
            String destination = service.getNameById(ts.getDestination());
            if (departure.equals(from) && destination.equals(to)) {
                paths.add(Arrays.asList(from, to));
            }
        }
        return paths;
    }

    private List<List<String>> allPaths(String from, String to) {
        List<List<String>> paths = new ArrayList<>();
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(from));

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String last = path.get(path.size() - 1);

            if (last.equals(to)) {
                paths.add(new ArrayList<>(path));
            } else {
                for (TrainStation ts : service.getTrainStations()) {
                    String departure = service.getNameById(ts.getDeparture());
                    String destination = service.getNameById(ts.getDestination());
                    if (departure.equals(last) && !path.contains(destination)) {
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(destination);
                        queue.add(newPath);
                    }
                }
            }
        }
        return paths;
    }

    @Override
    public void update() {
        initializeModel();
    }
}