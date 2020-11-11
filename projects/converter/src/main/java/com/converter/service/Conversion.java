package com.converter.service;

import org.springframework.stereotype.Service;

@Service
public class Conversion {

     /**
     * convert kelvin to celsius
     *
     * @param  kelvin to be converted to celsius
     @return the result
     */
	public double KelvinToCelcius(double Kelvin) {

		return Kelvin - 273.15F;

	}

	public double poundsToKilogram(double pounds) {
		return pounds * 0.454;
	}
	
	public double milesToKilometes(double miles) {
		return miles * 1.6;
	}
}
