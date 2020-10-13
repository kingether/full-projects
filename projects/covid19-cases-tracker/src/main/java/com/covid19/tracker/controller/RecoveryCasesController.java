package com.covid19.tracker.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.covid19.tracker.model.LocationStats;
import com.covid19.tracker.service.CoronaVirusDeadCasesDataService;
import com.covid19.tracker.service.CoronaVirusRecoveryCasesDataService;

@Controller
public class RecoveryCasesController {

	CoronaVirusRecoveryCasesDataService coronaVirusRecoveryCasesDataService;

	@Autowired
	public RecoveryCasesController(CoronaVirusRecoveryCasesDataService coronaVirusRecoveryCasesDataService) {
		this.coronaVirusRecoveryCasesDataService = coronaVirusRecoveryCasesDataService;

	}
	
	@GetMapping("/recovery-cases")
	public String confirmedCases(Model model) {
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        List<LocationStats> allRecoveryCasesStats = coronaVirusRecoveryCasesDataService.getRecoveryCasesStats();
        int totalRecoveryCases = allRecoveryCasesStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewRecoveryCases = allRecoveryCasesStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("totalRecoveryCases", numberFormat.format(totalRecoveryCases));
        model.addAttribute("totalNewRecoveryCases", numberFormat.format(totalNewRecoveryCases));
        model.addAttribute("recoveryCasesStats", allRecoveryCasesStats);
		
		return "recovery-cases";
	}
}
