/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.xdi.oxauth.comp;

import java.util.Date;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.xdi.oxauth.BaseComponentTest;
import org.xdi.oxauth.model.ldap.TokenLdap;
import org.xdi.oxauth.model.ldap.TokenType;
import org.xdi.oxauth.service.GrantService;

/**
 * @author Yuriy Zabrovarnyy
 * @version 0.9, 14/01/2013
 */

public class GrantServiceTest extends BaseComponentTest {

    private static final String TEST_TOKEN_CODE = UUID.randomUUID().toString();

    private String m_clientId;
    private GrantService m_grantService;
    private TokenLdap m_tokenLdap;

    @Parameters(value = "clientId")
    public GrantServiceTest(String p_clientId) {
        m_clientId = p_clientId;
    }

    @Override
    public void beforeClass() {
        m_grantService = GrantService.instance();
        m_tokenLdap = createTestToken();
        m_grantService.persist(m_tokenLdap);
    }

    @Override
    public void afterClass() {
        final TokenLdap t = m_grantService.getGrantsByCode(TEST_TOKEN_CODE);
        if (t != null) {
            m_grantService.remove(t);
        }
    }

    @Test
    public void testCleanUp() {
        m_grantService.cleanUp(); // clean up must remove just created token because expiration is set to new Date()
        final TokenLdap t = m_grantService.getGrantsByCode(TEST_TOKEN_CODE);
        Assert.assertTrue(t == null);
    }

    private TokenLdap createTestToken() {
        final String id = GrantService.generateGrantId();
        final String dn = GrantService.buildDn(id, m_clientId);

        final TokenLdap t = new TokenLdap();
        t.setId(id);
        t.setDn(dn);
        t.setTokenCode(TEST_TOKEN_CODE);
        t.setTokenType(TokenType.ACCESS_TOKEN.getValue());
        t.setCreationDate(new Date());
        t.setExpirationDate(new Date());
        return t;
    }

}
