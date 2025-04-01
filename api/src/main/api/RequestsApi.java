/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.10.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package de.ben_kostka.benchesster.api;

import de.ben_kostka.benchesster.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-31T20:03:31.088752+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
@Validated
@Tag(name = "requests", description = "the requests API")
public interface RequestsApi {
    /**
     * GET /requests/{user_ID} : Get all requested games
     * Get all games that are requested to be played with partner
     *
     * @param user_ID The name that needs to be fetched. Use user1 for testing.  (required)
     * @return successful operation (status code 200)
     *         or Invalid username supplied (status code 400)
     *         or User not found (status code 404)
     */
    @Operation(
        operationId = "getRequestedGames",
        summary = "Get all requested games",
        description = "Get all games that are requested to be played with partner",
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class)),
                @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/requests/{user_ID}",
        produces = { "application/xml", "application/json" }
    )
    
    public ResponseEntity<User> getRequestedGames(
        @Parameter(name = "user_ID", description = "The name that needs to be fetched. Use user1 for testing. ", required = true, in = ParameterIn.PATH) @PathVariable("user_ID") String user_ID
    );
}
