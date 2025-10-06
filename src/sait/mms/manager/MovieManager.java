package sait.mms.manager;

import java.io.*;
import java.util.*;
import sait.mms.problemdomain.Movie;

public class MovieManager {
    private ArrayList<Movie> movieList = new ArrayList<>();
    private final String FILE_PATH = "res/movies.txt";
    private Scanner input = new Scanner(System.in);

    // Load movie list from file
    public void loadMovieList() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        int duration = Integer.parseInt(parts[0].trim());
                        String title = parts[1].trim();
                        int year = Integer.parseInt(parts[2].trim());
                        movieList.add(new Movie(duration, title, year));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid line: " + line);
                    }
                }
            }
            System.out.println(movieList.size() + " movies loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Display main menu
    public void displayMenu() {
        int option;
        do {
            System.out.println("\nMovie Management system");
            System.out.println("1 Add New Movie and Save");
            System.out.println("2 Generate List of Movies Released in a Year");
            System.out.println("3 Generate List of Random Movies");
            System.out.println("4 Exit");
            System.out.print("\nEnter an option: ");

            while (!input.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                input.next();
            }
            option = input.nextInt();
            input.nextLine(); // clear buffer

            switch (option) {
                case 1 -> addMovie();
                case 2 -> generateMovieListInYear();
                case 3 -> generateRandomMovieList();
                case 4 -> {
                    saveMovieListToFile();
                    System.out.println("Exiting... Goodbye!");
                }
                default -> System.out.println("Invalid option!");
            }
        } while (option != 4);
    }

    // Add movie
    public void addMovie() {
        try {
            System.out.print("\nEnter duration: ");
            int duration = input.nextInt();
            input.nextLine();
            if (duration <= 0) throw new IllegalArgumentException("Duration must be positive.");

            System.out.print("Enter movie title: ");
            String title = input.nextLine().trim();
            if (title.isEmpty()) throw new IllegalArgumentException("Title cannot be empty.");

            System.out.print("Enter year: ");
            int year = input.nextInt();
            input.nextLine();
            if (year <= 0) throw new IllegalArgumentException("Year must be positive.");

            Movie movie = new Movie(duration, title, year);
            movieList.add(movie);

            saveMovieListToFile();
            System.out.println("Saving movies... ");
            System.out.println("Added movie to the data file.");
        } catch (Exception e) {
            System.out.println("Error adding movie: " + e.getMessage());
        }
    }

    // List movies released in a year
    public void generateMovieListInYear() {
        System.out.print("\nEnter year: ");
        int year = input.nextInt();
        input.nextLine();

        int totalDuration = 0;
        System.out.println("\nMovie List");
        System.out.println("Duration\tYear\tTitle");

        for (Movie m : movieList) {
            if (m.getYear() == year) {
                System.out.println(m);
                totalDuration += m.getDuration();
            }
        }

        System.out.println("Total duration: " + totalDuration + " minutes");
    }

    // Generate random movie list
    public void generateRandomMovieList() {
        System.out.print("\nEnter number of movies: ");
        int count = input.nextInt();
        input.nextLine();

        if (count <= 0 || count > movieList.size()) {
            System.out.println("Invalid number of movies.");
            return;
        }

        Collections.shuffle(movieList);
        int totalDuration = 0;

        System.out.println("\nMovie List");
        System.out.println("Duration\tYear\tTitle");

        for (int i = 0; i < count; i++) {
            Movie m = movieList.get(i);
            System.out.println(m);
            totalDuration += m.getDuration();
        }

        System.out.println("\nTotal duration: " + totalDuration + " minutes");
    }

    // Save to file
    public void saveMovieListToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Movie m : movieList) {
                pw.println(m.getDuration() + "," + m.getTitle() + "," + m.getYear());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
