package com.orange.hw4.strategy;

import com.orange.hw4.model.Journal;
import com.orange.hw4.model.JournalViewHelper;
import com.orange.hw4.model.User;
import com.orange.hw4.repository.JournalRepository;
import com.orange.hw4.service.JournalService;
import com.orange.hw4.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetMarksStrategy implements UserActionStrategy {

    private static final Logger log = LoggerFactory.getLogger(SetMarksStrategy.class);
    private final JournalService journalService;
    private final UserService userService;

    public SetMarksStrategy(JournalService journalService, UserService userService) {
        this.journalService = journalService;
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long id = Long.valueOf(req.getParameter("id"));
        try{
        Journal journal = journalService.getJournalById(id);


        Double mark1 = journal.getUser1_mark();
        Double mark2 = journal.getUser2_mark();

        Long journalId = journal.getId();
        User user1 = userService.getUserById(journal.getUser1_id());
        User user2 = userService.getUserById(journal.getUser2_id());



        JournalViewHelper view = new JournalViewHelper(id,journal.getLessonDate(), user1, mark1,user2,mark2);
        req.setAttribute("journal", view);
        req.getRequestDispatcher("/setmarks.jsp").forward(req,resp);
        }catch (Exception e){
            log.error(e.getMessage() + " ");
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving journal. " + e.getMessage());
        }
    }
}
