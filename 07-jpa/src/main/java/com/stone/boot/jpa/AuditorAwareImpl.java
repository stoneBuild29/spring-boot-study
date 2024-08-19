package com.stone.boot.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @className: AuditorAwareImpl
 * @author: Scarlet
 * @date: 2024/8/19
 **/
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor(){
        return Optional.of("管理员" + (int)(Math.random() * 10));
    }

}
