package com.orange.hw4.service;

import com.orange.hw4.model.Journal;
import com.orange.hw4.model.User;

import java.util.List;
import java.util.Map;

public interface JournalService {
    void addPairs();
    void removePairs();
    void editPairs();

    List<Journal> getJournalFeed();
    Map<User, Integer> getAllUserOpponents(User user);
}
