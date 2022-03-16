package com.sp.fc.web.teacher;

import com.sp.fc.web.student.Student;
import com.sp.fc.web.student.StudentAuthenticationToken;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class TeacherManager implements AuthenticationProvider, InitializingBean {

    private HashMap<String, Student> teacherDB = new HashMap<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TeachertAuthenticationToken token = (TeachertAuthenticationToken) authentication;
        if(teacherDB.containsKey(token.getCredentials())){
            Student student = teacherDB.get(token.getCredentials());
            return StudentAuthenticationToken.builder()
                    .principal(student)
                    .details(student.getUserName())
                    .authenticated(true)
                    .build();
        }
        throw new AuthenticationException("not accept"){};
//        return null; // authentication token을 false로 해서 넘기게 되면 authentication을 핸들링 했다는 것이 되기 때문에 문제가됨.
        /// 처리할 수 없는 authentication은 null로 넘겨야 함
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == TeachertAuthenticationToken.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Student("choi", "최선생", Set.of(new SimpleGrantedAuthority("ROLE_TEACHER")))
        ).forEach(s-> teacherDB.put(s.getId(), s));
    }
}
