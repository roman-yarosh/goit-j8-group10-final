package ua.goit.finall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduleService {
    private Logger logger = LoggerFactory.getLogger(ScheduleService.class.getName());

    @Autowired
    private EmployeeService employeeService;

    @Scheduled(cron = "0 0 10 1 * *") // At 10 o'clock at 1 day of a month.
    //    @Scheduled(cron = "30 15 1 * * *")
//    @Scheduled(fixedRate = 1000)
    public void passLetter() {
        employeeService.calculateSalary(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        //sendMessageWithAttachment();
        System.out.println("Email send");
    }


}
