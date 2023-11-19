package services.user.models;

import java.util.Objects;

public class UserModel {

    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public UserModel(long id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel that = (UserModel) o;
        return userStatus == that.userStatus && Objects.equals(username, that.username) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, email, password, phone, userStatus);
    }

    public static class UserModelBuilder {

        private long id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phone;
        private int userStatus;


        public UserModelBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserModelBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserModelBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserModelBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserModelBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserModelBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserModelBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserModelBuilder setUserStatus(int userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public UserModel build(){
            return new UserModel(id, username, firstName, lastName, email, password, phone, userStatus);
        }
    }
}
