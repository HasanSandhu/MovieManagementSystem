package sait.mms.application;

import sait.mms.manager.MovieManager;

/**
 * AppDriver is the main entry point of the Movie Management System.
 */
public class AppDriver {
    public static void main(String[] args) {
        MovieManager manager = new MovieManager();
        manager.loadMovieList();
        manager.displayMenu();
    }
}
