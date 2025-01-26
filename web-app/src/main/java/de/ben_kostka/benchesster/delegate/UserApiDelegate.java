package de.ben_kostka.benchesster.delegate;

import de.ben_kostka.benchesster.api.ApiUtil;
import de.ben_kostka.benchesster.api.UserApi;
import de.ben_kostka.benchesster.controller.UserApiController;
import de.ben_kostka.benchesster.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link UserApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-31T20:03:31.088752+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public interface UserApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /user : Create user
     * This can only be done by the logged in user.
     *
     * @param user Created user object (optional)
     * @return successful operation (status code 200)
     * @see UserApi#createUser
     */
    default ResponseEntity<User> createUser(User user) {
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

    /**
     * POST /user/createWithList : Creates list of users with given input array
     * Creates list of users with given input array
     *
     * @param user  (optional)
     * @return Successful operation (status code 200)
     *         or successful operation (status code 200)
     * @see UserApi#createUsersWithListInput
     */
    default ResponseEntity<User> createUsersWithListInput(List<@Valid User> user) {
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

    /**
     * DELETE /user/{user_ID} : Delete user
     * This can only be done by the logged in user.
     *
     * @param user_ID The name that needs to be fetched. Use user1 for testing.  (required)
     * @return Invalid user id supplied (status code 400)
     *         or User not found (status code 404)
     * @see UserApi#deleteUser
     */
    default ResponseEntity<Void> deleteUser(int user_ID) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /user/{user_ID} : Get user by user name
     * 
     *
     * @param user_ID The name that needs to be fetched. Use user1 for testing.  (required)
     * @return successful operation (status code 200)
     *         or Invalid user id supplied (status code 400)
     *         or User not found (status code 404)
     * @see UserApi#getUserByName
     */
    default ResponseEntity<User> getUserByName(int user_ID) {
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

    /**
     * GET /user/login : Logs user into the system
     * 
     *
     * @param username The user name for login (optional)
     * @param password The password for login in clear text (optional)
     * @return successful operation (status code 200)
     *         or Invalid username/password supplied (status code 400)
     * @see UserApi#loginUser
     */
    default ResponseEntity<String> loginUser(String username,
        String password) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /user/logout : Logs out current logged in user session
     * 
     *
     * @return successful operation (status code 200)
     * @see UserApi#logoutUser
     */
    default ResponseEntity<Void> logoutUser() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /user/{user_ID} : Update user
     * This can only be done by the logged in user.
     *
     * @param user_ID The name that needs to be fetched. Use user1 for testing.  (required)
     * @param user Update an existent user in the store (optional)
     * @return successful operation (status code 200)
     * @see UserApi#updateUser
     */
    default ResponseEntity<Void> updateUser(int user_ID,
        User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
