package com.orange.hw4.service;

import com.orange.hw4.model.Journal;
import com.orange.hw4.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface JournalService {
    void addPairs(User user1, User user2, LocalDate date);
    void removePairs(Long id);
    void editPairs(Journal journal, Double mark1, Double mark2);

    List<Journal> getJournalFeed();
    Map<Long, Map<Long,Integer>> getAllUserOpponents(User user);
}
