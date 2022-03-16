package com.sp.fc.web.teacher;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    private String id;
    private String userName;
    private Set<GrantedAuthority> role;
}
