package advent.utilities.general;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

public class Input {

    static final String PATH = "advent\\input\\";
    static final String URLPATH = "https://adventofcode.com/";
    static final String TOKENPATH = "advent\\input\\token.txt";

    public static String fetchInput(int year, int day) {
        try {
            //ensure proper year and day bounding to prevent url errors
            if (year < 2015 || day < 1 || day > 25) {
                System.out.println("Improper input grabbing parameters!");
                throw new IOException();
            }
            //initialize path to input file
            String inputPath = PATH + "\\" + year + "\\day" + day + ".txt";
            File inputFile = new File(inputPath);
            //if input file does not exist, begin process of http fetching and creating/writing to file
            if (!inputFile.exists()) {
                System.out.println("No input file found, attempting to fetch from web...");

                //ensure AOC authentication token is present and accessible
                File tokenFile = new File(TOKENPATH);
                if (!tokenFile.exists()) {
                    System.out.println("No AOC authentication token found!");
                    System.out.println("Find your authentication token using the resources below, place it into the input directory in a folder named \"token.txt\", and try again.");
                    System.out.println("https://github.com/wimglenn/advent-of-code-wim/issues/1");
                    throw new IOException();
                }
                String token = Files.readString(tokenFile.toPath());

                //construct and make HTTP request
                String url = URLPATH + year + "/day/" + day + "/input";
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder(URI.create(url)).header("User-Agent", "github.com/joshuaruegge/adventofcode").header("cookie", "session=" + token).build();
                System.out.println("Making HTTP request...");

                //receive HTTP response
                HttpResponse<String> response;
                try {
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (IOException e) {
                    System.out.println("URL path error!");
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    System.out.println("Connection error!");
                    throw new RuntimeException(e);
                }
                //check that HTTP response was successful
                if (response.statusCode() != 200) {
                    System.out.println("HTTP error: " + response.statusCode());
                    if (response.body().contains("log in"))
                        System.out.println("Potentially an invalid authentication token, double-check and update your token.txt if necessary!");
                    throw new IOException();
                }
                System.out.println("HTTP response successful! Writing to file");
                //push response output to file
                inputFile.createNewFile();
                FileWriter output = new FileWriter(inputPath);
                output.write(response.body());
                output.close();
            }
            //read file contents and return
            return Files.readString(inputFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

}
