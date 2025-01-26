package de.ben_kostka.benchesster.delegate;

import de.ben_kostka.benchesster.api.SettingsApi;
import de.ben_kostka.benchesster.controller.SettingsApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link SettingsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-31T20:03:31.088752+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public interface SettingsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /settings : See options panel
     * Returns all possible settings options
     *
     * @return successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Account not found (status code 404)
     * @see SettingsApi#getSettingsoptions
     */
    default ResponseEntity<String> getSettingsoptions() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
