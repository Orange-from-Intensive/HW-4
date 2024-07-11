package com.orange.hw4.strategy;

import com.orange.hw4.service.JournalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateMarksStrategy implements UserActionStrategy{

    private static final Logger log = LoggerFactory.getLogger(UpdateMarksStrategy.class);
    private final JournalService journalService;

    public UpdateMarksStrategy(JournalService journalService) {
        this.journalService = journalService;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Double mark1 = Double.valueOf(req.getParameter("mark1"));
        log.error("1" + mark1);
        Double mark2 = Double.valueOf(req.getParameter("mark2"));
        log.error("2" + mark2);
        Long id = Long.valueOf(req.getParameter("id"));
        log.error("3" + id);
        try{
            journalService.editPairs(id, mark1, mark2);
            resp.sendRedirect(req.getContextPath() + "/journal.jsp");
        }catch (Exception e){
            log.error(e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving journal." + e.getMessage());
        }
    }
}
