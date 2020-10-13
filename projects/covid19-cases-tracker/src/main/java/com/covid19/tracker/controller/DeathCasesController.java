package com.covid19.tracker.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.covid19.tracker.model.LocationStats;
import com.covid19.tracker.service.CoronaVirusConfirmedDataService;
import com.covid19.tracker.service.CoronaVirusDeadCasesDataService;

@Controller
public class DeathCasesController {

	CoronaVirusDeadCasesDataService coronaVirusDeadCasesDataService;

	@Autowired
	public DeathCasesController(CoronaVirusDeadCasesDataService coronaVirusDeadCasesDataService) {
		this.coronaVirusDeadCasesDataService = coronaVirusDeadCasesDataService;

	}
	
	@GetMapping("/death-cases")
	public String confirmedCases(Model model) {
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        List<LocationStats> allDeathCasesStats = coronaVirusDeadCasesDataService.getDeadCasesStats();
        int totalReportedDeathCases = allDeathCasesStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allDeathCasesStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("totalReportedDeathCases", numberFormat.format(totalReportedDeathCases));
        model.addAttribute("totalNewDeathCases", numberFormat.format(totalNewCases));
        model.addAttribute("deathCasesStats", allDeathCasesStats);
		
		return "death-cases";
	}
}
