package lsfcontact;

import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.*;

/**
 * Auxiliary module for the LSF for contacting students.
 *
 * <p>Students have various contact options. We would like to contact all students via the LSF, and
 * the LSF should use the contact option available for each student.
 *
 * <p>The module is quite limited - we can't even pass the message to be transmitted.
 */
public class LsfContactUtil {

    private static final Logger logger = Logger.getLogger(LsfContactUtil.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("contacts.csv", true);
            fileHandler.setFormatter(new CSVFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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

    static class ContactInfo {
        String WayOfCommunication, Recipient;
        ContactInfo(String foo, String bar) {
            WayOfCommunication = foo;
            Recipient = bar;
        }
    }


    public static void contactStudents(
        List<Student> students,
        Function<Student, String> getRelevantInfo,
        BiConsumer<Student, ContactInfo> contactAction,
        String WayOfCommunication
    ) {
        for (var s : students) {
            String info = getRelevantInfo.apply(s);
            if (!info.isEmpty()) {
                contactAction.accept(s, new ContactInfo(WayOfCommunication, info));
                logContact(s, WayOfCommunication, info);
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
    public static void contact(Student s, ContactInfo info) {
        // just a silly placeholder - imagine some serious code here
        System.out.println(info.WayOfCommunication + s.getName() + ": " + info.Recipient);
    }

    /**
     * Logs the contact action including the calling class and method.
     *
     * @param s       the student
     * @param mode    the communication mode
     * @param address the used address (email, phone, postal)
     */
    private static void logContact(Student s, String mode, String address) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        String callerClass = findCallerClass(stack);
        String callerMethod = findCallerMethod(stack);
        logger.logp(Level.INFO, callerClass, callerMethod,
            String.format("%s,%s,%s", s.getName(), address, mode));
    }

    /**
     * Finds the calling class from the stack trace.
     *
     * @param stack the stack trace elements
     * @return the calling class name
     */
    private static String findCallerClass(StackTraceElement[] stack) {
        for (StackTraceElement e : stack) {
            if (!e.getClassName().equals(LsfContactUtil.class.getName()) &&
                !e.getClassName().startsWith("java.lang.Thread")) {
                return e.getClassName();
            }
        }
        return LsfContactUtil.class.getName();
    }

    /**
     * Finds the calling method from the stack trace.
     *
     * @param stack the stack trace elements
     * @return the calling method name
     */
    private static String findCallerMethod(StackTraceElement[] stack) {
        for (StackTraceElement e : stack) {
            if (!e.getClassName().equals(LsfContactUtil.class.getName()) &&
                !e.getClassName().startsWith("java.lang.Thread")) {
                return e.getMethodName();
            }
        }
        return "unknown";
    }

    /**
     * Custom CSV formatter for log records.
     */
    static class CSVFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            return String.format("%s,%s,%s,%s%n",
                record.getLevel(),
                record.getSourceMethodName(),
                record.getSourceClassName(),
                record.getMessage());
        }
    }



}
