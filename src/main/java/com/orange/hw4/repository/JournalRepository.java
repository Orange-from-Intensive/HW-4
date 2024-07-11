package com.orange.hw4.repository;

import com.orange.hw4.model.Journal;
import com.orange.hw4.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface JournalRepository {

    void addPairs(User user1, User user2, LocalDate date);
    void removePairs(Long id);
    void editPairs(Journal journal, Double mark1, Double mark2);

    List<Journal> getJournalFeed();

    /**
     * returns all user opponent objects
     * @param user user for whom the search will be carried out
     * @return  hashmap containing users and frequency of their matches
     */
    Map<Long, Map<Long,Integer>> getAllUserOpponents(User user);
    Map<Long, Map<Long, Integer>> getAllUserOpponents();

}
