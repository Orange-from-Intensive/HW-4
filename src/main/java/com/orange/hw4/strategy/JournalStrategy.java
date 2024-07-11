package com.orange.hw4.strategy;

import com.orange.hw4.model.Journal;
import com.orange.hw4.model.JournalViewHelper;
import com.orange.hw4.model.User;
import com.orange.hw4.service.JournalService;
import com.orange.hw4.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JournalStrategy implements UserActionStrategy {

    private final UserService userService;
    private final JournalService journalService;

    public JournalStrategy(UserService userService, JournalService journalService) {
        this.userService = userService;
        this.journalService = journalService;
    }


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            // get every entry in journal
            List<Journal> journalFeed = journalService.getJournalFeed();
            // list for displaying in html
            List<JournalViewHelper> list = new ArrayList<>();

            // terrible
            for(Journal j: journalFeed){
                Long id = j.getId();
                LocalDate lessonDate = j.getLessonDate();
                User user1 = userService.getUserById(j.getUserOneId());
                User user2 = userService.getUserById(j.getUserTwoId());
                Double user1Mark = j.getUserOneMark();
                Double user2Mark = j.getUserTwoMark();
                list.add(new JournalViewHelper(id,lessonDate,user1, user1Mark,user2,user2Mark));
            }

            req.setAttribute("journalView", list);

            req.getRequestDispatcher("/journal.jsp").forward(req,resp);
        } catch (Exception e) {
            log.warn("Error retrieving journal. " + e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving journal." + e.getMessage());
        }
    }
}
