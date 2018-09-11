package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResumeModelEntity {
    private int resumeModelId;
    private boolean applicantName;
    private boolean applicantSex;
    private boolean applicantAge;
    private boolean applicantSchool;
    private boolean applicantDegree;
    private boolean applicantMajor;
    private boolean applicantCity;
    private boolean applicantEmail;
    private boolean applicantPhone;
    private boolean applicantCV;
    private boolean applicantHometown;
    private boolean applicantNation;
    private boolean applicantPoliticalStatus;
    private boolean applicantMaritalStatus;
    private boolean applicantDateOfBirth;
    private boolean applicantTimeToWork;
    private boolean applicantCurrentSalary;
    private boolean applicantExpectSalary;
    private boolean applicantDutyTime;
    private boolean recommenderName;
    private boolean applicantAddress;
    private boolean applicantSelfIntro;
    private boolean applicantPhoto;
    private boolean applicantDegreePhoto;
    private boolean familyContactRelation;
    private boolean familyContactName;
    private boolean familyContactCompany;
    private boolean familyContactPhoneNum;

    private FieldText fieldText = new FieldText();

    @Data
    public class FieldText {
        private String applicantName = "Name (e.g. Jersey)";
        private String applicantSex = "Sex (e.g. Female)";
        private String applicantAge = "Age (e.g. 25)";
        private String applicantSchool = "School (e.g. UniMelb)";
        private String applicantDegree = "Degree (e.g. Master)";
        private String applicantMajor = "Major (e.g. Information Technology)";
        private String applicantCity = "City (e.g. Melbourne)";
        private String applicantEmail = "Email (e.g. aaa@bbb.ccc)";
        private String applicantPhone = "Phone (e.g. 52733999)";
        private String applicantCV = "Resume (PDF File)";
        private String applicantHometown = "Native Place (e.g. Shanghai)";
        private String applicantNation = "Ethnic Group (e.g. Han)";
        private String applicantPoliticalStatus = "Political Status (e.g. Communist)";
        private String applicantMaritalStatus = "Marital Status (e.g. Married)";
        private String applicantDateOfBirth = "Date of Birth (e.g. 1990-01-01)";
        private String applicantTimeToWork = "When to Start Work (e.g. 2014-08-01)";
        private String applicantCurrentSalary = "Current Salary (e.g. $100000)";
        private String applicantExpectSalary = "Expect Salary (e.g. $200000)";
        private String applicantDutyTime = "Duty Time (e.g. 2018-08-01)";
        private String recommenderName = "Recommender Name (e.g. Qiangdong Liu)";
        private String applicantAddress = "Address (e.g. East Nanjing Road No.1)";
        private String applicantSelfIntro = "Self Introduction (e.g. I'm from China...)";
        private String applicantPhoto = "ID Photo (Image File)";
        private String applicantDegreePhoto = "Testamur Photo Copy (Image File)";
        private String familyContactRelation = "Family Contact Relation (e.g. Wife)";
        private String familyContactName = "Family Contact Name (e.g. Zetian Zhang)";
        private String familyContactCompany = "Family Contact Company (e.g. JD.com)";
        private String familyContactPhoneNum = "Family Contact Phone Number (e.g. 400-610-1360)";
    }
}
