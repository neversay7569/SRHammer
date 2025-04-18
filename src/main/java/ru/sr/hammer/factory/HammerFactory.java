package ru.sr.hammer.factory;

public interface HammerFactory {

    /**
     * Loads all hammers and caching it
     */
    void loadHammers();

    /**
     * Method to register all hammers as custom item
     */
    void registerHammers();
}
