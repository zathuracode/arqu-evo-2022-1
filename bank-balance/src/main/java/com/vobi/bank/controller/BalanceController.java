package com.vobi.bank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/balances")
@CrossOrigin("*")
public class BalanceController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Value("${diego.time.out}")
	private String timeOut;
	
	@GetMapping("/{id}")
	public BalanceDTO findBalance(@PathVariable("id") String id) {
		String query=String.format("SELECT balance FROM account WHERE acco_id='%s'", id);
		Double balance=jdbcTemplate.queryForObject(query, Double.class);
		return new BalanceDTO(balance);
	}
	
	
	@GetMapping("/sayHello/{nombre}")
	public String sayHello(@PathVariable("nombre") String name) {
		return "Hello "+name+" "+timeOut;
	}
	

}
class BalanceDTO{
	
	private Double balance;

	public BalanceDTO(Double balance) {
		super();
		this.balance = balance;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}