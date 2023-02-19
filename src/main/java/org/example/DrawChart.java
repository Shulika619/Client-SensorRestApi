package org.example;

import org.example.dto.MeasurementResponse;
import org.example.dto.MeasurementsDto;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DrawChart {
    public static void main(String[] args) {
        List<Double> temperatures = getTempFromServer();
        drawChart(temperatures);
    }

    private static List<Double> getTempFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String URL = "http://localhost:8080/measurements";

        MeasurementResponse jsonResponse = restTemplate.getForObject(URL, MeasurementResponse.class);
        if (jsonResponse == null || jsonResponse.getMeasurements() == null)
            return Collections.emptyList();

        return jsonResponse.getMeasurements().stream().map(MeasurementsDto::getValue).collect(Collectors.toList());
    }

    private static void drawChart(List<Double> temperatures) {
        double[] xData = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        double[] yData = temperatures.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Temperatures", "X", "Y", "temperatures", xData, yData);
        new SwingWrapper<>(chart).displayChart();

    }
}
