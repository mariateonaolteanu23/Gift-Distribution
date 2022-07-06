package main;

import checker.Checker;
import common.Constants;
import common.Loaders;
import delivery.DeliveryAction;
import delivery.DeliveryPlanner;
import fileio.input.Input;
import fileio.input.InputLoader;
import fileio.output.Writer;
import observers.DeliveryDataAggregator;
import observers.YellowElfObserver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        //  get output path
        Path outputPath = Paths.get(Constants.RESULT_PATH);

        //  create output directory
        if (!Files.exists(outputPath)) {
            Files.createDirectories(outputPath);
        }

        InputLoader loader = new InputLoader();

        //  get input directory
        File tests = new File(Constants.INPUT_PATH);

        for (File file : Objects.requireNonNull(tests.listFiles())) {
            //  get all input data
            Input input = loader.readInputData(file.getAbsolutePath());

            //  start simulation
            simulation(input, file.getName());
        }

        Checker.calculateScore();
    }

    private static void simulation(final Input input, final String fileName) throws IOException {
        Writer writer = new Writer();

        //  load all data for delivery database
        Loaders.loadDeliveryData(input);

        //  load all data for delivery planner
        DeliveryPlanner deliveryPlanner = new DeliveryPlanner(input.getNumberOfYears());
        Loaders.registerAnnualUpdates(deliveryPlanner, input.getAnnualChanges());

        //  load all data for delivery action
        DeliveryAction deliveryAction = new DeliveryAction();
        Loaders.registerAnnualSetUps(deliveryAction);

        //  add delivery observers
        deliveryAction.addObserver(new YellowElfObserver());
        deliveryAction.addObserver(new DeliveryDataAggregator(writer));



        //  simulate for the specified number of years
        for (int i = 0; i <= deliveryPlanner.getNumberOfYears(); ++i) {
            //  do delivery
            deliveryAction.executeAnnualDelivery();

            //  all planned deliveries were executed
            if (deliveryPlanner.deliveryPlanIsDone()) {
                //  simulation is over
                break;
            }

            //  prepare for next planned delivery
            deliveryPlanner.oneYearHasPassed();
        }

        //  get output path name
        String outputPath = Constants.OUTPUT_PATH + fileName.replace(Constants.TEST, "");

        //  create output file
        File out = new File(outputPath);

        //  write to output file
        writer.closeJSON(new FileWriter(outputPath));
    }
}
