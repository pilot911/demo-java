//package ru.telenok.newspaper.auth.service.impl;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import ru.telenok.newspaper.auth.annotation.SecureUpdate;
//import ru.telenok.newspaper.auth.service.EntitySecurityService;
//
//import java.lang.reflect.Field;
//import java.util.Collection;
//
//@Slf4j
//@Service
//public class EntitySecurityServiceImpl implements EntitySecurityService {
//
//
//    @Override
//    public Boolean canUpdate(Object object) {
//        final Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//
//        for (Field field : object.getClass().getDeclaredFields()) {
//
//            final SecureUpdate annotation = field.getAnnotation(SecureUpdate.class);
//
//            AnnotationUtils.findAnnotation()
//
//            if (annotation != null) {
//                try {
//                    field.setAccessible(true);
//                    final Object persistedField = field.get(object);
//                    final Object originalField = field.get(object);
//
//
//                } catch (Exception e) {
//                    log.error(e.getMessage());
//                }
//
//            }
//
//        }
//    }
//}
