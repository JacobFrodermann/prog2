package lsfcontact;

import java.util.List;

/**
 * Auxiliary module for the LSF for contacting students.
 *
 * <p>Students have various contact options. We would like to contact all students via the LSF, and
 * the LSF should use the contact option available for each student.
 *
 * <p>The module is quite limited - we can't even pass the message to be transmitted.
 */
public class LsfContactUtil {
    /**
     * Contact students via email if they have registered an email address.
     *
     * <p>Select all students with an email address from the list and send them an email using the
     * auxiliary method {@code email}.
     *
     * @param sl list of students to contact
     */
    public static void emailStudents(List<Student> sl) {
        for (Student s : sl) {
            if (!s.getEmail().isEmpty()) {
                email(s);
            }
        }
    }

    /**
     * Contact students via phone if they have registered a phone number.
     *
     * <p>Select all students with a phone number from the list and give them a call using the
     * auxiliary method {@code phone}.
     *
     * @param sl list of students to contact
     */
    public static void phoneStudents(List<Student> sl) {
        for (Student s : sl) {
            if (!s.getPhone().isEmpty()) {
                phone(s);
            }
        }
    }

    /**
     * Contact students via snail mail if they have registered a postal address.
     *
     * <p>Select all students with a postal address from the list and write them a good old letter
     * using the auxiliary method {@code write}.
     *
     * @param sl list of students to contact
     */
    public static void writeStudents(List<Student> sl) {
        for (Student s : sl) {
            if (!s.getAddress().isEmpty()) {
                write(s);
            }
        }
    }

    /**
     * Auxiliary function for sending an email to a student.
     *
     * @param s student the email should be sent to
     */
    private static void email(Student s) {
        // just a silly placeholder - imagine some serious code here
        System.out.println("EMail to " + s.getName() + ": " + s.getEmail());
    }

    /**
     * Auxiliary function for giving a phone call to a student.
     *
     * @param s student to be called
     */
    private static void phone(Student s) {
        // just a silly placeholder - imagine some serious code here
        System.out.println("Call to " + s.getName() + ": " + s.getPhone());
    }

    /**
     * Auxiliary function for posting a letter to a student.
     *
     * @param s student the letter should be posted to
     */
    private static void write(Student s) {
        // just a silly placeholder - imagine some serious code here
        System.out.println("Write to " + s.getName() + ": " + s.getAddress());
    }
}
