package com.CL3.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CL3.model.Employee;
import com.CL3.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/about")
public class AboutController {
	
	public EmployeeRepository er;
	public DataSource dataSource;
	
	public AboutController(EmployeeRepository er, DataSource dataSource) {
		this.er = er;
		this.dataSource = dataSource;
	}
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("message", "CL3, es una empresa con muchos empleados altamente capacitados");
		model.addAttribute("style", "css/style.css");
		
		List<Employee> lstEmployees = er.findAll();
		model.addAttribute("lstEmployees", lstEmployees);
		
		return "about/index";
	}
	
	@GetMapping("/registerEmployee")
	public String frmRegisterEmployee(Model model) {
		model.addAttribute("title", "Registro de nuevo empleado");
		model.addAttribute("style", "../css/style.css");
		
		Employee objEmp = new Employee();
		model.addAttribute("objEmp", objEmp);
		
		return "about/registerEmployee";
	}
	
	@PostMapping("registerEmployee")
	public String registerEmployee(Employee objEmp) {
		er.save(objEmp);
		
		return "redirect:/about";
	}
	
	@GetMapping("/frmUpdateEmployee/{id}")
	public String frmUpdateEmployee(@PathVariable Integer id, Model model) {
		model.addAttribute("title", "Actulizar empleado");
		model.addAttribute("style", "../../css/style.css");
		
		Optional<Employee> oEmp = er.findById(id);
		
		if(oEmp.isEmpty()) return "404";
		
		Employee objEmp = oEmp.get();
		model.addAttribute("objEmp", objEmp);
		
		return "about/updateEmployee";
	}
	
	@PostMapping("updateEmployee/{id}")
	public String updateEmployee(@PathVariable Integer id, Employee objEmpFrm) {
		Optional<Employee> oEmp = er.findById(id);
		
		if(oEmp.isEmpty()) return "404";
		
		Employee objEmp = oEmp.get();
		objEmp.setName(objEmpFrm.getName());
		objEmp.setLastName(objEmpFrm.getLastName());
		objEmp.setSalary(objEmpFrm.getSalary());
		objEmp.setPosition(objEmpFrm.getPosition());
		
		er.save(objEmp);
		
		return "redirect:/about";
	}
	
	@GetMapping("deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Integer id) {
		er.deleteById(id);
		
		return "redirect:/about";
	}
	
	@GetMapping("downloadReport")
	public void downloadReport(HttpServletResponse response) throws SQLException {
		try {
			InputStream is = new ClassPathResource("reports/report_employees.jasper").getInputStream();
			JasperReport report = (JasperReport) JRLoader.loadObject(is);
			
			Connection connection = dataSource.getConnection();
			Map<String, Object> parameters = new HashMap<>();
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, connection);
			response.setContentType("application/pdf");
			OutputStream os = response.getOutputStream();
		
			JasperExportManager.exportReportToPdfStream(jasperPrint, os);
			
		} catch (IOException |JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
}
