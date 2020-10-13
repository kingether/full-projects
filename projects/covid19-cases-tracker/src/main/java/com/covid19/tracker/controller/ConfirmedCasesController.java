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
public class ConfirmedCasesController {
	CoronaVirusConfirmedDataService coronaVirusConfirmedDataService;

	@Autowired
	public ConfirmedCasesController(CoronaVirusConfirmedDataService coronaVirusConfirmedDataService) {
		this.coronaVirusConfirmedDataService = coronaVirusConfirmedDataService;

	}
	
	@GetMapping("/confirmed-cases")
	public String confirmedCases(Model model) {
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        List<LocationStats> allConfirmedCasesStats = coronaVirusConfirmedDataService.getConfirmedCasesStats();
        int totalReportedConfirmedCases = allConfirmedCasesStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allConfirmedCasesStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("totalReportedCases", numberFormat.format(totalReportedConfirmedCases));
        model.addAttribute("totalNewCases", numberFormat.format(totalNewCases));
        model.addAttribute("locationStats", allConfirmedCasesStats);
		
		return "confirmed-cases";
	}
}
