package com.eduardoproject.atdc.entities;

import com.eduardoproject.atdc.entities.enums.TypeUser;
import com.eduardoproject.atdc.entities.enums.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String name;
    private String email;
    @JsonIgnore
    private String password;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "tb_profiles")
    private Set<Integer> profiles = new HashSet<>();
    @Transient
    private Integer profileId;
    @JoinColumn(name = "tb_phones")
    private String phone1;

    @JoinColumn(name = "tb_phones")
    private String phone2;



    public User() {
        addProfile(UserProfile.USER);
    }

    public User(String name, String email, String password, Integer profileId) {
        this.name = name;
        this.email = email;
        this.profileId = profileId;
        this.password = password;
        addProfile(UserProfile.USER);
        }

    public User(String name, String email, String password, String phone1, Integer profileId) {
        this.name = name;
        this.email = email;
        this.phone1 = phone1;
        this.password = password;
        this.profileId = profileId;
        addProfile(UserProfile.USER);
    }

    public User(String name, String email, String password, String phone1, String phone2, Integer profileId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.profileId = profileId;
        addProfile(UserProfile.USER);
    }



    public String getPassword() {
        return password;
    }
    public Set<UserProfile> getProfile(){
        return profiles.stream().map(x -> UserProfile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(UserProfile userProfile) {
        profiles.add(userProfile.getCod());
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
