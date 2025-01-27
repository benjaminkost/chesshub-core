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


package de.ben_kostka.benchesster.client;

import java.util.HashSet;

/**
 * Representing a Server Variable for server URL template substitution.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-01-27T11:52:59.664272+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public class ServerVariable {
    public String description;
    public String defaultValue;
    public HashSet<String> enumValues = null;

    /**
     * @param description A description for the server variable.
     * @param defaultValue The default value to use for substitution.
     * @param enumValues An enumeration of string values to be used if the substitution options are from a limited set.
     */
    public ServerVariable(String description, String defaultValue, HashSet<String> enumValues) {
        this.description = description;
        this.defaultValue = defaultValue;
        this.enumValues = enumValues;
    }
}
