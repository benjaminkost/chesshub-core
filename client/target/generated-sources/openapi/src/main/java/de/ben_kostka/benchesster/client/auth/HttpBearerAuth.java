/*
 * ChessGameManagement - OpenAPI 3.0
 * This is a ChessGameManagement Server based on the OpenAPI 3.0 specification.  At the end of this project it should be possible for every chess player  to store and manage your chess scoresheet in your own account. Furthermore,  it should be possible to display every move on a chess board and to analyze the individual moves.
 *
 * The version of the OpenAPI document: 1.0.0-SNAPSHOT
 * Contact: mail@ben-kostka.de
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package de.ben_kostka.benchesster.client.auth;

import de.ben_kostka.benchesster.client.Pair;
import de.ben_kostka.benchesster.client.ApiException;

import java.net.URI;
import java.util.Map;
import java.util.List;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-01-22T16:03:41.005461+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public class HttpBearerAuth implements Authentication {
  private final String scheme;
  private String bearerToken;

  public HttpBearerAuth(String scheme) {
    this.scheme = scheme;
  }

  /**
   * Gets the token, which together with the scheme, will be sent as the value of the Authorization header.
   *
   * @return The bearer token
   */
  public String getBearerToken() {
    return bearerToken;
  }

  /**
   * Sets the token, which together with the scheme, will be sent as the value of the Authorization header.
   *
   * @param bearerToken The bearer token to send in the Authorization header
   */
  public void setBearerToken(String bearerToken) {
    this.bearerToken = bearerToken;
  }

  @Override
  public void applyToParams(List<Pair> queryParams, Map<String, String> headerParams, Map<String, String> cookieParams, String payload, String method, URI uri) throws ApiException {
    if(bearerToken == null) {
      return;
    }

    headerParams.put("Authorization", (scheme != null ? upperCaseBearer(scheme) + " " : "") + bearerToken);
  }

  private static String upperCaseBearer(String scheme) {
    return ("bearer".equalsIgnoreCase(scheme)) ? "Bearer" : scheme;
  }
}
