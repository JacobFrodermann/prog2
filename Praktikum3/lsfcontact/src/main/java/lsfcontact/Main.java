package lsfcontact;

import java.util.List;

/** Starter for the LSF Contact scenario. */
public class Main {
    /**
     * And go.
     *
     * @param args command line parameters, not used
     */
    public static void main(String... args) {
        // create some students
        Student lea = new Student("lea");
        Student mads = new Student("mads");
        Student hans = new Student("hans");

        lea.setEmail("lea@hsbi.de");
        mads.setPhone("0175 123 456 78");
        hans.setAddress("Irgendwostrasse 9, 12345 Irgendwo");

        //test

        // create a list
        List<Student> sl = List.of(lea, mads, hans);

        // use the LsfContactUtil to contact our students
        LsfContactUtil.contactStudents(sl, Student::getEmail, LsfContactUtil::contact, "EMail to");
        LsfContactUtil.contactStudents(sl, Student::getPhone, LsfContactUtil::contact, "Call to ");
        LsfContactUtil.contactStudents(sl, Student::getAddress, LsfContactUtil::contact, "Write to");
    }
}
