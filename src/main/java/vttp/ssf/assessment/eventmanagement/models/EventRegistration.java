package vttp.ssf.assessment.eventmanagement.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EventRegistration {
 
    @NotEmpty(message = "Full Name is Mandatory.")
    @Size(min = 5, max = 25, message = "Full Name must be between 5 and 25 characters long.")
    private String fullName;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Past(message = "Birth date cannot be greater than or equals to present date.")
    private Date birthDate;

    @NotEmpty(message = "Email is Mandatory.")
    @Email(message = "Invalid Email Format.")
    @Max(50)
    private String email;

    @NotEmpty(message = "Phone Number is mandatory.")
    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number entered, must start with 8 or 9 and be 8 digits long.")
    private String mobileNumber;

    @Size(min = 1, max = 3, message = "Minimum 1 ticket, Maximum 3 tickets per request")
    private Integer numberOfTicketsRequested;

    // To track what event the user registered for
    private Integer eventId; // Enables us to easily check the remaining amount of tix.
    private String eventName;
    private Long eventDate;

    public EventRegistration() { }

    public EventRegistration(
            @NotEmpty(message = "Full Name is Mandatory.") @Size(min = 5, max = 25, message = "Full Name must be between 5 and 25 characters long.") String fullName,
            @Past(message = "Birth date cannot be greater than or equals to present date.") Date birthDate,
            @NotEmpty(message = "Email is Mandatory.") @Email(message = "Invalid Email Format.") @Max(50) String email,
            @NotEmpty(message = "Phone Number is mandatory.") @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number entered, must start with 8 or 9 and be 8 digits long.") String mobileNumber,
            @Size(min = 1, max = 3, message = "Minimum 1 ticket, Maximum 3 tickets per request") Integer numberOfTicketsRequested,
            Integer eventId, String eventName, Long eventDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.numberOfTicketsRequested = numberOfTicketsRequested;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getNumberOfTicketsRequested() {
        return numberOfTicketsRequested;
    }
    
    public void setNumberOfTicketsRequested(Integer numberOfTicketsRequested) {
        this.numberOfTicketsRequested = numberOfTicketsRequested;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getEventDate() {
        return eventDate;
    }

    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "EventRegistration [fullName=" + fullName + ", birthDate=" + birthDate + ", email=" + email
                + ", mobileNumber=" + mobileNumber + ", numberOfTicketsRequested=" + numberOfTicketsRequested
                + ", eventId=" + eventId + ", eventName=" + eventName + ", eventDate=" + eventDate + "]";
    }
    
}
