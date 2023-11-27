package com.example.appdevproject.Tax.Models;

public class Tax_Income {

    private Integer id,foreignKey;

    private String jobTitle;
    private Double hourlyWage,hoursWorked,bonuses;

    public Tax_Income(Integer id, String jobTitle, Double hourlyWage, Double hoursWorked, Double bonuses) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
        this.bonuses = bonuses;
    }


    public Tax_Income(String jobTitle, Double hourlyWage, Double hoursWorked, Double bonuses) {
        this.jobTitle = jobTitle;
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
        this.bonuses = bonuses;
    }

    public Tax_Income(Integer id, Integer foreignKey, String jobTitle, Double hourlyWage, Double hoursWorked, Double bonuses) {
        this.id = id;
        this.foreignKey = foreignKey;
        this.jobTitle = jobTitle;
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
        this.bonuses = bonuses;
    }


    public Double getYearlyIncome(){
        return this.hourlyWage*(this.hoursWorked*2)*26;
        // 80 hours every 2 weeks * (52 annual weeks/2 ) 26 by-weeks
    }











    public Integer getId() {
        return id;
    }

    public Integer getForeignKey() {
        return foreignKey;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Double getHourlyWage() {
        return hourlyWage;
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public Double getBonuses() {
        return bonuses;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setForeignKey(Integer foreignKey) {
        this.foreignKey = foreignKey;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setHourlyWage(Double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setBonuses(Double bonuses) {
        this.bonuses = bonuses;
    }
}