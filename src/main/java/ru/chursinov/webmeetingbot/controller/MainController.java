package ru.chursinov.webmeetingbot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chursinov.webmeetingbot.excel.ExcelHandler;
import ru.chursinov.webmeetingbot.model.UserAnswersModel;
import ru.chursinov.webmeetingbot.service.UserAnswersService;
import ru.chursinov.webmeetingbot.utils.GetCurrentDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@Slf4j
public class MainController {

    private UserAnswersService userAnswersService;
    private final GetCurrentDate getCurrentDate;

    @Autowired
    public MainController(UserAnswersService userAnswersService, GetCurrentDate getCurrentDate) {
        this.userAnswersService = userAnswersService;
        this.getCurrentDate = getCurrentDate;
    }

    @RequestMapping("/")
    public ModelAndView homePage(Model model) {
        return new ModelAndView("index");
    }

    @RequestMapping("/userAnswers")
    public ModelAndView getAnswers(Model model) {

        List<UserAnswersModel> allAnswers = userAnswersService.getAllAnswers();
        model.addAttribute("answers", allAnswers);

        return new ModelAndView("userAnswers");
    }

    @RequestMapping("/getExcel")
    public void getExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("applicationt/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", String.format("attachment;filename=\"report_%s.xls\"",
                new Object[] { getCurrentDate.getDate() }));
        File temp = File.createTempFile("report", ".xls");

        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(temp));
            BufferedOutputStream out = new BufferedOutputStream((OutputStream)response.getOutputStream())) {
            ExcelHandler excelHandler = new ExcelHandler();
            List<UserAnswersModel> allAnswers = userAnswersService.getAllAnswers();
            excelHandler.setList(allAnswers);
            excelHandler.createExcel(temp);
            int ch = in.read();
            while (ch != -1) {
                out.write(ch);
                ch = in.read();
            }
        } catch (Exception ex) {
            log.info("Error in create excel controller: ", ex);
        } finally {
            if (temp != null)
                temp.delete();
        }
    }

}
