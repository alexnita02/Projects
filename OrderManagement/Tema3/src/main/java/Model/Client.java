package Model;


public class Client {

    private int ID;
    private String firstName;
    private String lastName;
    private int age;

    /**
     * Constructor default
     * */
    public Client(){

    }

    /**
     * Constructor Client cu parametri specifici
     *
     * @param ID                id-ul clientului
     * @param firstName         prenumele clientului
     * @param lastName          nume de familie a clientului
     * @param age               varsta clientului
     * */

    public Client(int ID, String firstName, String lastName, int age) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     getter pentru id

     @return id ul clientului
     * */


    public int getID() {
        return ID;
    }

    /**
     setter pentru id
    @param ID  id ul clientului
     * */



    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     getter pentru prenume
     @return prenumele clienutului
      * */

    public String getFirstName() {
        return firstName;
    }

    /**
     setter pentru prenume
     @param firstName  prenumele  clientului
      * */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     getter pentru nume
     @return numele de familie a clienutului
      * */

    public String getLastName() {
        return lastName;
    }

    /**
     setter pentru nume
     @param lastName  numele  clientului
      * */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getter pt ani
     *
     * @return varsta clientului

     */

    public int getAge() {
        return age;
    }

    /**
     setter pentru ani
     @param age
     * */

    public void setAge(int age) {
        this.age = age;
    }


    /**

     @return reprezentarea clientului sub forma de string
      * */

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}