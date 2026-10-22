package com.cmpt276.asn2;

enum RoleType {
    TA,
    PROF,
    INSTRUCTOR,
    STAFF
}

interface StaffMemberProfile {
    String displayTitle();
}

class TAProfile implements StaffMemberProfile {
    public String displayTitle() {
        return "Teaching Assistant";
    }
}

class PROFProfile implements StaffMemberProfile {
    public String displayTitle() {
        return "Professor";
    }
}

class INSTRUCTORProfile implements StaffMemberProfile {
    public String displayTitle() {
        return "Instructor";
    }
}

class STAFFProfile implements StaffMemberProfile {
    public String displayTitle() {
        return "Staff Member";
    }
}