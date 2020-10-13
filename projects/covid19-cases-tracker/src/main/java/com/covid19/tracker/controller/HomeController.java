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
public class HomeController {

   
    CoronaVirusConfirmedDataService coronaVirusConfirmedDataService;
    CoronaVirusDeadCasesDataService coronaVirusDeadDataService;
	
    @Autowired
    public HomeController( CoronaVirusConfirmedDataService coronaVirusConfirmedDataService,
    		CoronaVirusDeadCasesDataService coronaVirusDeadCasesDataService) {
    	this.coronaVirusConfirmedDataService = coronaVirusConfirmedDataService;
    	this.coronaVirusDeadDataService=coronaVirusDeadCasesDataService;
		
	}
    @GetMapping("/")
    public String home(Model model) {
    	
    	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
    	
        List<LocationStats> allConfirmedCasesStats = coronaVirusConfirmedDataService.getConfirmedCasesStats();
        int totalReportedConfirmedCases = allConfirmedCasesStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allConfirmedCasesStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("totalReportedCases", numberFormat.format(totalReportedConfirmedCases));
        model.addAttribute("totalNewCases", numberFormat.format(totalNewCases));
        model.addAttribute("locationStats", allConfirmedCasesStats);
        
        
        List<LocationStats> allDeadCasesStats = coronaVirusDeadDataService.getDeadCasesStats();
        int totalReportedDeadCases = allDeadCasesStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewDeathCases = allDeadCasesStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

        model.addAttribute("totalReportedDeadCases",numberFormat.format(totalReportedDeadCases));
        model.addAttribute("deadCasesStats", allDeadCasesStats);
        model.addAttribute("totalNewDeathCases", numberFormat.format(totalNewDeathCases));
       

        return "home";
    }
}
