/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.dto.BookingDTO;
import com.example.sem4.repository.BookingRepository;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StatisticsViewController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/admin/statistics")
    public String getStatistics(HttpServletRequest request, ModelMap model, Model attr, String from, String to) {
        if (request.getSession().getAttribute("username") == null) {
            return "redirect:/admin";
        } else {
            try {
                model.addAttribute("msg", attr.asMap().get("msg"));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                model.addAttribute("user", request.getSession().getAttribute("username"));
                List<BookingDTO> list = bookingRepository.retrieveBookingAsDTONoParams();
                List<BookingDTO> listi = new ArrayList<>();
                Double Total = 0.0;
                for (int i = 0; i < 5; i++) {
                    if (list.get(i).getDiscount().intValue() > 0) {
                        listi.add(new BookingDTO(list.get(i).getTourName(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue()), list.get(i).getDiscount(), list.get(i).getQuantity(),
                                BigDecimal.valueOf((list.get(i).getPriceTour().doubleValue() - (list.get(i).getPriceTour().doubleValue() * list.get(i).getDiscount().intValue() / 100)) * list.get(i).getQuantity())));
                    } else {
                        listi.add(new BookingDTO(list.get(i).getTourName(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue()), list.get(i).getDiscount(), list.get(i).getQuantity(),
                                BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue() * list.get(i).getQuantity())));

                    }
                }
                model.addAttribute("list", listi);
                return "statistics";
            } catch (Exception ex) {
                Logger.getLogger(StatisticsViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "statistics";
    }

    @PostMapping("/admin/bookings/report")
    public String Top5BookingReport(HttpServletRequest request, HttpServletResponse response, String from, String to, ModelMap model, RedirectAttributes redirect) throws IOException, ParseException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
            Date fromDate = format.parse(from);
            Date toDate = format.parse(to);
            List<BookingDTO> list = bookingRepository.retrieveBookingAsDTO(fromDate, toDate);
            List<BookingDTO> listi = new ArrayList<>();
            Double Total = 0.0;
            if (list.isEmpty()) {
                redirect.addFlashAttribute("msg", "No data for date from: " + from + " to " + to);
                return "redirect:/admin/statistics";
            }
            if (list.size() < 5) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getDiscount().intValue() > 0) {
                        listi.add(new BookingDTO(list.get(i).getTourName(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue()), list.get(i).getDiscount(), list.get(i).getQuantity(),
                                BigDecimal.valueOf((list.get(i).getPriceTour().doubleValue() - (list.get(i).getPriceTour().doubleValue() * list.get(i).getDiscount().intValue() / 100)) * list.get(i).getQuantity())));
                    } else {
                        listi.add(new BookingDTO(list.get(i).getTourName(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue()), list.get(i).getDiscount(), list.get(i).getQuantity(),
                                BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue() * list.get(i).getQuantity())));

                    }
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    if (list.get(i).getDiscount().intValue() > 0) {
                        listi.add(new BookingDTO(list.get(i).getTourName(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue()), list.get(i).getDiscount(), list.get(i).getQuantity(), BigDecimal.valueOf((list.get(i).getPriceTour().doubleValue() - (list.get(i).getPriceTour().doubleValue() * list.get(i).getDiscount().intValue() / 100)) * list.get(i).getQuantity())));
                    } else {
                        listi.add(new BookingDTO(list.get(i).getTourName(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue()), list.get(i).getDiscount(), list.get(i).getQuantity(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue() * list.get(i).getQuantity())));

                    }
                }
            }

            String report = "src/main/resources/Blank_A4Copy.jrxml";

            JasperReport jreport = JasperCompileManager.compileReport(report);

            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listi);

            HashMap params = new HashMap();
            params.put("totalPrice", Total);
            params.put("Top5DataSource", ds);
            params.put("from", format2.format(fromDate));
            params.put("to", format2.format(toDate));
            params.put("image", "src/main/resources/static/images/logo_330x330.png");

            JasperPrint jprint = JasperFillManager.fillReport(jreport, params, new JREmptyDataSource());
            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "filename=BookingReport.pdf");
            OutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jprint, out);
//            JasperExportManager.exportReportToPdfFile(jprint,
//                    "src/main/resources/report1.pdf");

        } catch (JRException ex) {
            return "redirect:/admin/statistics";
        }
        return null;
    }

    @PostMapping("/admin/bookings/reportHTML")
    public String Top5BookingReportHTML(HttpServletRequest request, HttpServletResponse response, String from, String to, ModelMap model, RedirectAttributes redirect) throws IOException, ParseException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
            Date fromDate = format.parse(from);
            Date toDate = format.parse(to);
            List<BookingDTO> list = bookingRepository.retrieveBookingAsDTO(fromDate, toDate);
            List<BookingDTO> listi = new ArrayList<>();
            Double Total = 0.0;
//            if (list.isEmpty()) {
//                redirect.addFlashAttribute("msg", "No data for date from: " + from + " to " + to);
//                return "redirect:/admin/statistics";
//            }
            if (list.size() < 5) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getDiscount().intValue() > 0) {
                        listi.add(new BookingDTO(list.get(i).getTourName(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue()), list.get(i).getDiscount(), list.get(i).getQuantity(),
                                BigDecimal.valueOf((list.get(i).getPriceTour().doubleValue() - (list.get(i).getPriceTour().doubleValue() * list.get(i).getDiscount().intValue() / 100)) * list.get(i).getQuantity())));
                    } else {
                        listi.add(new BookingDTO(list.get(i).getTourName(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue()), list.get(i).getDiscount(), list.get(i).getQuantity(),
                                BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue() * list.get(i).getQuantity())));

                    }
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    if (list.get(i).getDiscount().intValue() > 0) {
                        listi.add(new BookingDTO(list.get(i).getTourName(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue()), list.get(i).getDiscount(), list.get(i).getQuantity(), BigDecimal.valueOf((list.get(i).getPriceTour().doubleValue() - (list.get(i).getPriceTour().doubleValue() * list.get(i).getDiscount().intValue() / 100)) * list.get(i).getQuantity())));
                    } else {
                        listi.add(new BookingDTO(list.get(i).getTourName(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue()), list.get(i).getDiscount(), list.get(i).getQuantity(), BigDecimal.valueOf(list.get(i).getPriceTour().doubleValue() * list.get(i).getQuantity())));

                    }
                }
            }

            String report = "src/main/resources/Blank_A4Copy.jrxml";
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listi);
            HashMap params = new HashMap();
            params.put("totalPrice", Total);
            params.put("Top5DataSource", ds);
            params.put("from", format2.format(fromDate));
            params.put("to", format2.format(toDate));
            params.put("image", "src/main/resources/static/images/logo_330x330.png");
            response.setContentType("text/html");
            InputStream is=this.getClass().getResourceAsStream(report);
            JasperReport jasperReport=JasperCompileManager.compileReport(report);
            JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, params,new JREmptyDataSource());
            HtmlExporter htmlExporter=new HtmlExporter(DefaultJasperReportsContext.getInstance());
            htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
            htmlExporter.exportReport();
        } catch (Exception ex) {
//            return "redirect:/admin/statistics";
        }
        return null;
    }

}
