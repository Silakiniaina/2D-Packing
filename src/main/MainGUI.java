package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import packing2Dshapes.*;

import java.util.ArrayList;
import java.util.List;

public class MainGUI extends Application {

    private List<Shape> shapes = new ArrayList<>();
    private double containerWidth = 500;
    private double containerHeight = 500;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("2D Packing Application");

        // Create layout
        VBox mainLayout = new VBox(10);
        HBox inputLayout = new HBox(10);
        Pane drawingPane = new Pane();
        drawingPane.setPrefSize(containerWidth, containerHeight);
        drawingPane.setStyle("-fx-border-color: black;");

        // Create input fields
        ComboBox<String> shapeType = new ComboBox<>();
        shapeType.getItems().addAll("Circle", "Rectangle", "Triangle");
        TextField sizeField = new TextField();
        sizeField.setPromptText("Size/Radius/Base");
        TextField widthField = new TextField();
        widthField.setPromptText("Width (for Rectangle)");
        Button addButton = new Button("Add Shape");
        Button packButton = new Button("Pack Shapes");

        inputLayout.getChildren().addAll(shapeType, sizeField, widthField, addButton);
        mainLayout.getChildren().addAll(inputLayout, packButton, drawingPane);

        // Add shape button action
        addButton.setOnAction(e -> {
            try {
                String type = shapeType.getValue();
                double size = Double.parseDouble(sizeField.getText());
                if ("Rectangle".equals(type)) {
                    double width = Double.parseDouble(widthField.getText());
                    shapes.add(new Rectangle2D(width, size));
                } else if ("Circle".equals(type)) {
                    shapes.add(new Circle(size));
                } else if ("Triangle".equals(type)) {
                    shapes.add(new IsoscelesTriangle(size));
                }
                sizeField.clear();
                widthField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Invalid input. Please enter valid numbers.");
            }
        });

        // Pack shapes button action
        packButton.setOnAction(e -> {
            HeuristicPacker packer = new HeuristicPacker(containerWidth, containerHeight);
            List<Shape> packedShapes = packer.pack(new ArrayList<>(shapes));
            drawShapes(drawingPane, packedShapes);
        });

        Scene scene = new Scene(mainLayout, 600, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawShapes(Pane pane, List<Shape> shapes) {
        pane.getChildren().clear();
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(
                        shape.getX() + ((Circle) shape).getWidth() / 2,
                        shape.getY() + ((Circle) shape).getHeight() / 2,
                        ((Circle) shape).getWidth() / 2
                );
                circle.setStroke(javafx.scene.paint.Color.BLACK);
                circle.setFill(javafx.scene.paint.Color.TRANSPARENT);
                pane.getChildren().add(circle);
            } else if (shape instanceof Rectangle2D) {
                javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(
                        shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight()
                );
                rectangle.setStroke(javafx.scene.paint.Color.BLACK);
                rectangle.setFill(javafx.scene.paint.Color.TRANSPARENT);
                pane.getChildren().add(rectangle);
            } else if (shape instanceof IsoscelesTriangle) {
                double base = ((IsoscelesTriangle) shape).getWidth();
                double height = ((IsoscelesTriangle) shape).getHeight();
                javafx.scene.shape.Polygon triangle = new javafx.scene.shape.Polygon(
                        shape.getX(), shape.getY() + height,
                        shape.getX() + base / 2, shape.getY(),
                        shape.getX() + base, shape.getY() + height
                );
                triangle.setStroke(javafx.scene.paint.Color.BLACK);
                triangle.setFill(javafx.scene.paint.Color.TRANSPARENT);
                pane.getChildren().add(triangle);
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}