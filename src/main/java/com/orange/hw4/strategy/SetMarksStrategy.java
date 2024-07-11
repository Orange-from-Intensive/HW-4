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
        Long idaaa = Long.valueOf(req.getParameter("id"));
        Journal j = journalService.getJournalById(idaaa);


        Double mark1 = Double.valueOf(req.getParameter("mark1"));
        Double mark2 = Double.valueOf(req.getParameter("mark2"));
        Long journalId = j.getId();
        User user1 = userService.getUserById(j.getUser1_id());
        User user2 = userService.getUserById(j.getUser2_id());

        JournalViewHelper view = new JournalViewHelper(journalId,j.getLessonDate(), user1, mark1,user2,mark2);
        req.setAttribute("journal", view);
        try{
            journalService.editPairs(j.getId(), mark1, mark2);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        req.getRequestDispatcher("/setmarks.jsp").forward(req,resp);
    }
}
