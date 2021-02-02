package com.example.test2;

import com.google.gson.Gson;

public class Customer {

    private int loan_amnt;
    private float int_rate;
    private float annual_inc;
    private float dti;
    private int delinq2_years;
    private int emp_length_10more_years;
    private int emp_length_2_years;
    private int emp_length_3_years;
    private int emp_length_4_years;
    private int emp_length_5_years;
    private int emp_length_6_years;
    private int emp_length_7_years;
    private int emp_length_8_years;
    private int emp_length_9_years;
    private int emp_length_1less_years;
    private int home_ownership_MORTGAGE;
    private int home_ownership_NONE;
    private int home_ownership_OWN;
    private int home_ownership_OTHER;
    private int home_ownership_RENT;
    private int purpose_credit_card;
    private int purpose_debt_consolidation;
    private int purpose_educational;
    private int purpose_home_improvement;
    private int purpose_house;
    private int purpose_major_purchase;
    private int purpose_medical;
    private int purpose_moving;
    private int purpose_other;
    private int purpose_renewable_energy;
    private int purpose_small_business;
    private int purpose_vacation;
    private int purpose_wedding;



    public Customer(){
    }

    ///////////set methods////////////////

    public void setLoan_amnt(int loanamnt){
        this.loan_amnt = loanamnt;
    }
    public void setAnnual_inc(float annual_inc) {
        this.annual_inc = annual_inc;
    }
    public void setDelinq2_years(int delinq2_years) {
        this.delinq2_years = delinq2_years;
    }
    public void setDti(float dti) {
        this.dti = dti;
    }
    public void setInt_rate(float int_rate) {
        this.int_rate = int_rate;
    }

    public void setEmp_length_1less_years(int emp_length_1less_years) {
        this.emp_length_1less_years = emp_length_1less_years;
    }

    public void setEmp_length_2_years(int emp_length_2_years) {
        this.emp_length_2_years = emp_length_2_years;
    }

    public void setEmp_length_3_years(int emp_length_3_years) {
        this.emp_length_3_years = emp_length_3_years;
    }

    public void setEmp_length_4_years(int emp_length_4_years) {
        this.emp_length_4_years = emp_length_4_years;
    }

    public void setEmp_length_5_years(int emp_length_5_years) {
        this.emp_length_5_years = emp_length_5_years;
    }

    public void setEmp_length_6_years(int emp_length_6_years) {
        this.emp_length_6_years = emp_length_6_years;
    }

    public void setEmp_length_7_years(int emp_length_7_years) {
        this.emp_length_7_years = emp_length_7_years;
    }

    public void setEmp_length_8_years(int emp_length_8_years) {
        this.emp_length_8_years = emp_length_8_years;
    }

    public void setEmp_length_9_years(int emp_length_9_years) {
        this.emp_length_9_years = emp_length_9_years;
    }

    public void setEmp_length_10more_years(int emp_length_10more_years) {
        this.emp_length_10more_years = emp_length_10more_years;
    }

    public void setHome_ownership_MORTGAGE(int home_ownership_MORTGAGE) {
        this.home_ownership_MORTGAGE = home_ownership_MORTGAGE;
    }

    public void setHome_ownership_NONE(int home_ownership_NONE) {
        this.home_ownership_NONE = home_ownership_NONE;
    }

    public void setHome_ownership_OTHER(int home_ownership_OTHER) {
        this.home_ownership_OTHER = home_ownership_OTHER;
    }

    public void setHome_ownership_OWN(int home_ownership_OWN) {
        this.home_ownership_OWN = home_ownership_OWN;
    }

    public void setHome_ownership_RENT(int home_ownership_RENT) {
        this.home_ownership_RENT = home_ownership_RENT;
    }

    public void setPurpose_credit_card(int purpose_credit_card) {
        this.purpose_credit_card = purpose_credit_card;
    }

    public void setPurpose_debt_consolidation(int purpose_debt_consolidation) {
        this.purpose_debt_consolidation = purpose_debt_consolidation;
    }

    public void setPurpose_educational(int purpose_educational) {
        this.purpose_educational = purpose_educational;
    }

    public void setPurpose_home_improvement(int purpose_home_improvement) {
        this.purpose_home_improvement = purpose_home_improvement;
    }

    public void setPurpose_house(int purpose_house) {
        this.purpose_house = purpose_house;
    }

    public void setPurpose_major_purchase(int purpose_major_purchase) {
        this.purpose_major_purchase = purpose_major_purchase;
    }

    public void setPurpose_medical(int purpose_medical) {
        this.purpose_medical = purpose_medical;
    }

    public void setPurpose_moving(int purpose_moving) {
        this.purpose_moving = purpose_moving;
    }

    public void setPurpose_other(int purpose_other) {
        this.purpose_other = purpose_other;
    }

    public void setPurpose_renewable_energy(int purpose_renewable_energy) {
        this.purpose_renewable_energy = purpose_renewable_energy;
    }

    public void setPurpose_small_business(int purpose_small_business) {
        this.purpose_small_business = purpose_small_business;
    }

    public void setPurpose_vacation(int purpose_vacation) {
        this.purpose_vacation = purpose_vacation;
    }

    public void setPurpose_wedding(int purpose_wedding) {
        this.purpose_wedding = purpose_wedding;
    }
    //////////get methods/////////

    public int getDelinq2_years() {
        return delinq2_years;
    }
    public int getLoan_amnt(){
        return loan_amnt;
    }
    public float getInt_rate(){
        return int_rate;
    }
    public float getAnnual_inc(){
        return annual_inc;
    }
    public float getDti(){
        return dti;
    }

    public int getEmp_length_1less_years() {
        return emp_length_1less_years;
    }

    public int getEmp_length_2_years() {
        return emp_length_2_years;
    }

    public int getEmp_length_3_years() {
        return emp_length_3_years;
    }

    public int getEmp_length_4_years() {
        return emp_length_4_years;
    }

    public int getEmp_length_5_years() {
        return emp_length_5_years;
    }

    public int getEmp_length_6_years() {
        return emp_length_6_years;
    }

    public int getEmp_length_7_years() {
        return emp_length_7_years;
    }

    public int getEmp_length_8_years() {
        return emp_length_8_years;
    }

    public int getEmp_length_9_years() {
        return emp_length_9_years;
    }

    public int getEmp_length_10more_years() {
        return emp_length_10more_years;
    }

    public int getHome_ownership_MORTGAGE() {
        return home_ownership_MORTGAGE;
    }

    public int getHome_ownership_NONE() {
        return home_ownership_NONE;
    }

    public int getHome_ownership_OTHER() {
        return home_ownership_OTHER;
    }

    public int getHome_ownership_OWN() {
        return home_ownership_OWN;
    }

    public int getHome_ownership_RENT() {
        return home_ownership_RENT;
    }

    public int getPurpose_credit_card() {
        return purpose_credit_card;
    }

    public int getPurpose_debt_consolidation() {
        return purpose_debt_consolidation;
    }

    public int getPurpose_educational() {
        return purpose_educational;
    }

    public int getPurpose_home_improvement() {
        return purpose_home_improvement;
    }

    public int getPurpose_house() {
        return purpose_house;
    }

    public int getPurpose_major_purchase() {
        return purpose_major_purchase;
    }

    public int getPurpose_medical() {
        return purpose_medical;
    }

    public int getPurpose_moving() {
        return purpose_moving;
    }

    public int getPurpose_other() {
        return purpose_other;
    }

    public int getPurpose_renewable_energy() {
        return purpose_renewable_energy;
    }

    public int getPurpose_small_business() {
        return purpose_small_business;
    }

    public int getPurpose_vacation() {
        return purpose_vacation;
    }

    public int getPurpose_wedding() {
        return purpose_wedding;
    }
}
