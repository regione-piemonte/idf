/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class LocalDateFormat {
	
	private int year;
	private String month;
	private String era;
	private int dayOfMonth;
	private String dayOfWeek;
	private int dayOfYear;
	private boolean leapYear;
	private int monthValue;
	private ChronologyFormat chronology;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getEra() {
		return era;
	}
	public void setEra(String era) {
		this.era = era;
	}
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public int getDayOfYear() {
		return dayOfYear;
	}
	public void setDayOfYear(int dayOfYear) {
		this.dayOfYear = dayOfYear;
	}
	public boolean isLeapYear() {
		return leapYear;
	}
	public void setLeapYear(boolean leapYear) {
		this.leapYear = leapYear;
	}
	public int getMonthValue() {
		return monthValue;
	}
	public void setMonthValue(int monthValue) {
		this.monthValue = monthValue;
	}
	public ChronologyFormat getChronology() {
		return chronology;
	}
	public void setChronology(ChronologyFormat chronology) {
		this.chronology = chronology;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LocalDateFormat [year=");
		builder.append(year);
		builder.append(", month=");
		builder.append(month);
		builder.append(", era=");
		builder.append(era);
		builder.append(", dayOfMonth=");
		builder.append(dayOfMonth);
		builder.append(", dayOfWeek=");
		builder.append(dayOfWeek);
		builder.append(", dayOfYear=");
		builder.append(dayOfYear);
		builder.append(", leapYear=");
		builder.append(leapYear);
		builder.append(", monthValue=");
		builder.append(monthValue);
		builder.append(", chronology=");
		builder.append(chronology);
		builder.append("]");
		return builder.toString();
	}
}
