/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.xdi.oxauth.model.config;

import org.xdi.util.LDAPConstants;

/**
 * Constants
 * 
 * @author Yuriy Movchan Date: 10.14.2010
 */
public final class Constants extends LDAPConstants {

	public static final String RESULT_SUCCESS = "success";
	public static final String RESULT_FAILURE = "failure";
	public static final String RESULT_DUPLICATE = "duplicate";
	public static final String RESULT_DISABLED = "disabled";
	public static final String RESULT_NO_PERMISSIONS = "no_permissions";
	public static final String RESULT_VALIDATION_ERROR = "validation_error";
	public static final String RESULT_LOGOUT = "logout";
	public static final String RESULT_EXPIRED = "expired";

    public static final String EVENT_OXAUTH_CUSTOM_LOGIN_SUCCESSFUL = "org.xdi.oxauth.security.loginSuccessful";
}
