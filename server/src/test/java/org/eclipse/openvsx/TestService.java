/********************************************************************************
 * Copyright (c) 2020 TypeFox and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 ********************************************************************************/
package org.eclipse.openvsx;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.eclipse.openvsx.entities.PersonalAccessToken;
import org.eclipse.openvsx.entities.UserData;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestService {
    
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void createUser() {
        var user = new UserData();
        user.setLoginName("test_user");
        entityManager.persist(user);
        var token = new PersonalAccessToken();
        token.setCreatedTimestamp(LocalDateTime.now());
        token.setActive(true);
        token.setUser(user);
        token.setValue("test_token");
        entityManager.persist(token);
    }
    
}