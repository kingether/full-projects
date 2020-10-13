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
import com.covid19.tracker.service.CoronaVirusRecoveryCasesDataService;

@Controller
public class HomeController {

   
    CoronaVirusConfirmedDataService coronaVirusConfirmedDataService;
    CoronaVirusDeadCasesDataService coronaVirusDeadDataService;
    CoronaVirusRecoveryCasesDataService coronaVirusRecoveryCasesDataService;
	
    @Autowired
    public HomeController( CoronaVirusConfirmedDataService coronaVirusConfirmedDataService,
    		CoronaVirusDeadCasesDataService coronaVirusDeadCasesDataService,
    		CoronaVirusRecoveryCasesDataService coronaVirusRecoveryCasesDataService) {
    	this.coronaVirusConfirmedDataService = coronaVirusConfirmedDataService;
    	this.coronaVirusDeadDataService=coronaVirusDeadCasesDataService;
    	this.coronaVirusRecoveryCasesDataService = coronaVirusRecoveryCasesDataService;
		
	}
    @GetMapping("/")
    public String home(Model model) {
    	
    	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
    	
        int totalReportedConfirmedCases =  coronaVirusConfirmedDataService.getConfirmedCasesStats().stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = coronaVirusConfirmedDataService.getConfirmedCasesStats().stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("totalReportedCases", numberFormat.format(totalReportedConfirmedCases));
        model.addAttribute("totalNewCases", numberFormat.format(totalNewCases));
        
        
        int totalReportedDeadCases = coronaVirusDeadDataService.getDeadCasesStats().stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewDeathCases = coronaVirusDeadDataService.getDeadCasesStats().stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

        model.addAttribute("totalReportedDeadCases",numberFormat.format(totalReportedDeadCases));
        model.addAttribute("totalNewDeathCases", numberFormat.format(totalNewDeathCases));
        
        int totalRecoveryCases = coronaVirusRecoveryCasesDataService.getRecoveryCasesStats().stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewRecoveryCases = coronaVirusRecoveryCasesDataService.getRecoveryCasesStats().stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("totalRecoveryCases", numberFormat.format(totalRecoveryCases));
        model.addAttribute("totalNewRecoveryCases", numberFormat.format(totalNewRecoveryCases));
    
       

        return "home";
    }
}
