package ua.goit.finall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.EmployeeRepository;
import ua.goit.finall.model.*;
import ua.goit.finall.service.EmployeeService;
import ua.goit.finall.service.PdfService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class.getName());
    private final String TO_ADDRESS = "degtjarova@gmail.com";
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.delete(id);
    }

    @Override
    public Employee update(Employee entity) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeByName(String name) {
        return employeeRepository.findEmployeeByName(name);
    }

    @Override
    public Employee findEmployeeByUser(User user) {
        return employeeRepository.findEmployeeByUser(user);
    }

    @Override
    public void calculateSalary(Integer month, Integer year) {
        for (Employee employee : employeeRepository.findAllWithDeps()) {
            double salary = getSalaryInRange(month, year, employee);

            List<Salary> salariesInDate = employee.getSalary().stream()
                    .filter(s -> s.getMonth().equals(month) && s.getYear().equals(year))
                    .collect(Collectors.toList());

            if (salariesInDate.isEmpty()) {
                employee.getSalary().add(new Salary(employee, month, year, BigDecimal.valueOf(salary)));
            } else {
                salariesInDate.forEach(s -> s.setSalarySum(new BigDecimal(salary)));
            }

            employeeRepository.save(employee);

            try {
                File file = new File("report.pdf");
                FileOutputStream reportStream = new FileOutputStream(file);
                pdfService.createEmployeeReport(employee, reportStream);

                sendMessageWithAttachment(TO_ADDRESS, "report", "report", file.getAbsolutePath());
            } catch (Exception e) {
                logger.error("Report creation error", e);
                e.printStackTrace();
            }

        }
    }

    private void sendMessageWithAttachment(String to, String subject, String text,
                                           String pathToAttachment) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        mailSender.send(message);
    }

    private double getSalaryInRange(Integer month, Integer year, Employee employee) {
        double salarySum = 0;
        double hourSalary = employee.getPosition().getHourSalary().doubleValue();
        for (Event event : employee.getEventList()) {
            if (event.getType().getType().equals(EventType.Types.WORK.getName()) && isDateInRange(event.getEventDate(), month, year)) {
                salarySum += event.getType().getRate() * hourSalary;
            }
        }
        return salarySum;
    }

    private boolean isDateInRange(Date eventDate, Integer month, Integer year) {
        int eventYear = LocalDateTime.ofInstant(eventDate.toInstant(), ZoneId.systemDefault()).toLocalDate().getYear();
        int eventMonth = LocalDateTime.ofInstant(eventDate.toInstant(), ZoneId.systemDefault()).toLocalDate().getMonth().getValue();
        return year == eventYear && month == eventMonth;
    }
}