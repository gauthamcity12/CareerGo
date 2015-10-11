package io.github.gauthamcity12.careergo;

/**
 * Created by gauthamcity12 on 10/10/15.
 */
public class CompanyInfo {
    protected String companyName;
    protected String positions;
    protected String description;

    public CompanyInfo(String a, String b, String c){
        companyName = a;
        positions = b;
        description = c;
    }

    public String getCompanyName(){ return companyName; }

    public String getPositions(){ return positions; }

    public String getDescription(){ return description; }
}
