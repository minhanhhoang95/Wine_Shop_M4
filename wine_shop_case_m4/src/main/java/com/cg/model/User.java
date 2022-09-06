package com.cg.model;

import com.cg.model.dto.UserDTO;
import com.cg.model.dto.UserListDTO;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    private String phone;

    @OneToOne
    @JoinColumn(name = "location_region_id")
    private LocationRegion locationRegion;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;
    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    public UserListDTO toUserListDTO(){
        return new UserListDTO()
                .setId(id.toString())
                .setFullName(fullName)
                .setUsername(username)
                .setPassword(password)
                .setPhone(phone)
                .setRole(role.toRoleDTO())
                .setLocationRegion(locationRegion.toLocationRegionDTO())
                .setDeleted(deleted);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }















}
