package lsfcontact;

/**
 * Model of a student having a name and three different contact options.
 *
 * <p>Includes the traditional Java shenanigans, as we will cover record classes later on.
 */
public class Student {
    private final String name;
    private String eMail;
    private String phone;
    private String address;

    /**
     * Build a new student having a name.
     *
     * <p>All other fields are initially empty and can be set individually.
     *
     * @param name name of the student
     */
    public Student(String name) {
        this.name = name;
        eMail = "";
        phone = "";
        address = "";
    }

    /**
     * Retrieve student's name.
     *
     * @return name of the student as {@code String}
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve student's email address.
     *
     * @return email address as {@code String}
     */
    public String getEmail() {
        return eMail;
    }

    /**
     * Change student's email address.
     *
     * @param eMail New email address
     */
    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * Retrieve student's phone number.
     *
     * @return phone number as {@code String}
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Change student's phone number.
     *
     * @param phone New phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieve student's postal address.
     *
     * @return postal address as {@code String}
     */
    public String getAddress() {
        return address;
    }

    /**
     * Change student's postal address.
     *
     * @param address New postal address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
