package com.orange.hw4.service;

import com.orange.hw4.model.Journal;
import com.orange.hw4.model.User;
import com.orange.hw4.repository.JournalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;
    public JournalServiceImpl(JournalRepository journalRepository) {

        this.journalRepository = journalRepository;
    }

    @Override
    public void addPairs(User user1, User user2, LocalDate date) {
        journalRepository.addPairs(user1, user2, date);
    }

    @Override
    public void removePairs(Long id) {
        journalRepository.removePairs(id);

    }

    @Override
    public void editPairs(Journal journal, Double mark1, Double mark2) {
        journalRepository.editPairs(journal, mark1, mark2);
    }

    @Override
    public List<Journal> getJournalFeed() {
        return journalRepository.getJournalFeed();
    }

    @Override
    public Map<Long, Map<Long,Integer>> getAllUserOpponents(User user) {
        return journalRepository.getAllUserOpponents(user);
    }
}
