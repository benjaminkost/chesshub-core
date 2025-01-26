package de.ben_kostka.benchesster.delegate;

import de.ben_kostka.benchesster.api.ApiUtil;
import de.ben_kostka.benchesster.api.RequestsApi;
import de.ben_kostka.benchesster.controller.RequestsApiController;
import de.ben_kostka.benchesster.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link RequestsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-31T20:03:31.088752+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public interface RequestsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /requests/{user_ID} : Get all requested games
     * Get all games that are requested to be played with partner
     *
     * @param user_ID The name that needs to be fetched. Use user1 for testing.  (required)
     * @return successful operation (status code 200)
     *         or Invalid username supplied (status code 400)
     *         or User not found (status code 404)
     * @see RequestsApi#getRequestedGames
     */
    default ResponseEntity<User> getRequestedGames(String user_ID) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"John\", \"lastName\" : \"James\", \"password\" : \"12345\", \"userStatus\" : 1, \"user_ID\" : 10, \"phone\" : \"12345\", \"email\" : \"john@email.com\", \"username\" : \"theUser\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                    String exampleString = "<user> <user_ID>10</user_ID> <username>theUser</username> <firstName>John</firstName> <lastName>James</lastName> <email>john@email.com</email> <password>12345</password> <phone>12345</phone> <userStatus>1</userStatus> </user>";
                    ApiUtil.setExampleResponse(request, "application/xml", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
