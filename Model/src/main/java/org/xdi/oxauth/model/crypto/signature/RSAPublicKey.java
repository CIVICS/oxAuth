/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.xdi.oxauth.model.crypto.signature;

import static org.xdi.oxauth.model.jwk.JWKParameter.EXPONENT;
import static org.xdi.oxauth.model.jwk.JWKParameter.MODULUS;
import static org.xdi.oxauth.model.jwk.JWKParameter.X;
import static org.xdi.oxauth.model.jwk.JWKParameter.Y;

import java.math.BigInteger;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.xdi.oxauth.model.crypto.PublicKey;
import org.xdi.oxauth.model.util.JwtUtil;
import org.xdi.oxauth.model.util.StringUtils;

/**
 * The Public Key for the RSA Algorithm
 *
 * @author Javier Rojas Blum
 * @version 0.9, 09/23/2014
 */
public class RSAPublicKey extends PublicKey {

    private static final String RSA_ALGORITHM = "RSA";
    private static final String USE = "sig";

    private BigInteger modulus;
    private BigInteger publicExponent;

    public RSAPublicKey(BigInteger modulus, BigInteger publicExponent) {
        this.modulus = modulus;
        this.publicExponent = publicExponent;
    }

    public RSAPublicKey(String modulus, String publicExponent) {
        this(new BigInteger(1, JwtUtil.base64urldecode(modulus)),
                new BigInteger(1, JwtUtil.base64urldecode(publicExponent)));
    }

    public BigInteger getModulus() {
        return modulus;
    }

    public void setModulus(BigInteger modulus) {
        this.modulus = modulus;
    }

    public BigInteger getPublicExponent() {
        return publicExponent;
    }

    public void setPublicExponent(BigInteger publicExponent) {
        this.publicExponent = publicExponent;
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("modulus", JwtUtil.base64urlencode(modulus.toByteArray()));
        jsonObject.put("exponent", JwtUtil.base64urlencode(publicExponent.toByteArray()));
        jsonObject.put(X, JSONObject.NULL);
        jsonObject.put(Y, JSONObject.NULL);

        return jsonObject;
    }

    @Override
    public String toString() {
        try {
            return toJSONObject().toString(4);
        } catch (JSONException e) {
            return StringUtils.EMPTY_STRING;
        } catch (Exception e) {
            return StringUtils.EMPTY_STRING;
        }
    }
}