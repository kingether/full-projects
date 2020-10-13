package com.covid19.tracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.covid19.tracker.model.ConfirmedCases;
import com.covid19.tracker.model.DeadCases;
import com.covid19.tracker.model.LocationStats;

@Service
public class CoronaVirusDeadCasesDataService {
	
	private final static String DEAD_CASES_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	private List<LocationStats> DeadCasesStats = new ArrayList<>();
	
	public List<LocationStats> getDeadCasesStats() {
		return DeadCasesStats;
	}
	

	@PostConstruct
	// @Scheduled(cron = "* * 1 * * *")
	@Scheduled(cron = "0/20 * * * * ?")
	public void fetchDeadCasesVirusData() throws IOException, InterruptedException {
		
		
		List<LocationStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DEAD_CASES_DATA_URL)).build();
		
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			LocationStats locationStat = new DeadCases();
			locationStat.setState(record.get("Province/State"));
			locationStat.setCountry(record.get("Country/Region"));
			int latestCases = Integer.parseInt(record.get(record.size() - 1));
			int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
			locationStat.setLatestTotalCases(latestCases);
			locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
			newStats.add(locationStat);
		}
		this.DeadCasesStats =  newStats;
		System.out.println("Spring scheduler - :" + LocalTime.now());
	}


	

}
