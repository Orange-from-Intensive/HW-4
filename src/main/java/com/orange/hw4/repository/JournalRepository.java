package com.orange.hw4.repository;

import com.orange.hw4.model.Journal;
import com.orange.hw4.model.User;

import java.util.List;
import java.util.Map;

public interface JournalRepository {

    void addPairs();
    void removePairs();
    void editPairs();

    List<Journal> getJournalFeed();

    /**
     * returns all user opponent objects
     * @param user user for whom the search will be carried out
     * @return  hashmap containing users and frequency of their matches
     */
    Map<User, Integer> getAllUserOpponents(User user);

}
