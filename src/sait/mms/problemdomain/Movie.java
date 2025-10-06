package sait.mms.problemdomain;

/**
 * Movie class represents a movie with duration, title, and year.
 * Author: [Your Name]
 * Date: [Date]
 * Description: Stores and retrieves movie data.
 */
public class Movie {
    private int duration;
    private String title;
    private int year;

    // Constructor
    public Movie(int duration, String title, int year) {
        this.duration = duration;
        this.title = title;
        this.year = year;
    }

    // Getters
    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    // toString for easy display
    @Override
    public String toString() {
        return duration + "\t" + year + "\t" + title;
    }
}
