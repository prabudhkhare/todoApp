package com.spring.arm.jpa.entity;

import com.spring.arm.model.security.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user_details")
public class User extends BaseEntity{
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "password",length = 5000)
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login")
    private Date lastLogin;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_password_changed")
    private Date lastPasswordChanged;
    @Column(name = "failed_login_attempts")
    private Integer failedLoginAttempts;
    @Column(name = "authorities")
    private String authorities;
    @Enumerated(EnumType.STRING)
    @Column(name = "provider",nullable = false)
    private Provider provider;

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (!(object instanceof User))
            return false;
        final User e = (User)object;
        if (this.emailId != null && e.getEmailId() != null) {
            return this.emailId.equalsIgnoreCase(e.getEmailId());
        }
        return false;
    }
}
