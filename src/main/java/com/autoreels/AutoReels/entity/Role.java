package com.autoreels.AutoReels.entity;

import com.autoreels.AutoReels.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "roles")
@Table(name = "roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    RoleName name;
    @Column(name = "description")
    String description;

}
