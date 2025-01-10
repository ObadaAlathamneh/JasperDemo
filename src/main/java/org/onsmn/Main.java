package org.onsmn;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Date;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.onsmn.module.Employee;

public class Main {

    public static void main(String[] args) {
        String jasperFilePath = "D:\\MyWork\\Java\\JasperDemo\\src\\main\\resources\\JasperDemo.jasper";

        try (InputStream inputStream = new FileInputStream(jasperFilePath)) {
            final byte[] bytes = JasperRunManager.runReportToPdf(inputStream, getReportParameters(),getData());
            writePdfToLocalFile(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    static void writePdfToLocalFile(byte[] bytes) {
        String pdfFilePath = "D:\\MyWork\\Java\\JasperDemo\\src\\main\\resources\\JasperDemo.pdf";
        try (FileOutputStream fos = new FileOutputStream(pdfFilePath)) {
            fos.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static Map<String, Object> getReportParameters() {
        Map<String, Object> parameters = new HashMap<>();
        return parameters;
    }

    static JRBeanCollectionDataSource getData() {
        List<Employee> employees = new ArrayList<>();
        Random random = new Random();

        // Profiles to choose from
        String[] profiles = {"Developer", "Manager", "Analyst", "Tester", "Designer"};

        for (int i = 0; i < 2; i++) {
            Employee employee = Employee.builder()
                    .id(random.nextInt(1000))
                    .name("Employee" + (i + 1))
                    .profile(profiles[random.nextInt(profiles.length)])
                    .date(new Date())
                    .salary(50000L + random.nextInt(50001))
                    .build();

            employees.add(employee);
        }
        return new JRBeanCollectionDataSource(employees);
    }
}