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


package de.ben_kostka.benchesster.client.model;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import de.ben_kostka.benchesster.client.model.User;
import java.util.Arrays;
import org.joda.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.ben_kostka.benchesster.client.JSON;


/**
 * Game
 */
@JsonPropertyOrder({
  Game.JSON_PROPERTY_GAME_I_D,
  Game.JSON_PROPERTY_EVENT,
  Game.JSON_PROPERTY_SITE,
  Game.JSON_PROPERTY_DATE,
  Game.JSON_PROPERTY_ROUND,
  Game.JSON_PROPERTY_WHITE,
  Game.JSON_PROPERTY_BLACK,
  Game.JSON_PROPERTY_RESULT,
  Game.JSON_PROPERTY_MOVES,
  Game.JSON_PROPERTY_COMMENT,
  Game.JSON_PROPERTY_COMMENT_WHITE,
  Game.JSON_PROPERTY_COMMENT_BLACK
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-01-27T11:52:59.664272+01:00[Europe/Berlin]", comments = "Generator version: 7.10.0")
public class Game {
  public static final String JSON_PROPERTY_GAME_I_D = "game_ID";
  @javax.annotation.Nullable
  private Long gameID;

  public static final String JSON_PROPERTY_EVENT = "event";
  @javax.annotation.Nullable
  private String event;

  public static final String JSON_PROPERTY_SITE = "site";
  @javax.annotation.Nullable
  private String site;

  public static final String JSON_PROPERTY_DATE = "date";
  @javax.annotation.Nullable
  private LocalDate date;

  public static final String JSON_PROPERTY_ROUND = "round";
  @javax.annotation.Nullable
  private String round;

  public static final String JSON_PROPERTY_WHITE = "white";
  @javax.annotation.Nullable
  private User white;

  public static final String JSON_PROPERTY_BLACK = "black";
  @javax.annotation.Nullable
  private User black;

  public static final String JSON_PROPERTY_RESULT = "result";
  @javax.annotation.Nullable
  private String result;

  public static final String JSON_PROPERTY_MOVES = "moves";
  @javax.annotation.Nullable
  private String moves;

  public static final String JSON_PROPERTY_COMMENT = "comment";
  @javax.annotation.Nullable
  private String comment;

  public static final String JSON_PROPERTY_COMMENT_WHITE = "commentWhite";
  @javax.annotation.Nullable
  private String commentWhite;

  public static final String JSON_PROPERTY_COMMENT_BLACK = "commentBlack";
  @javax.annotation.Nullable
  private String commentBlack;

  public Game() { 
  }

  public Game gameID(@javax.annotation.Nullable Long gameID) {
    this.gameID = gameID;
    return this;
  }

  /**
   * Get gameID
   * @return gameID
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_GAME_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getGameID() {
    return gameID;
  }


  @JsonProperty(JSON_PROPERTY_GAME_I_D)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setGameID(@javax.annotation.Nullable Long gameID) {
    this.gameID = gameID;
  }


  public Game event(@javax.annotation.Nullable String event) {
    this.event = event;
    return this;
  }

  /**
   * Get event
   * @return event
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EVENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getEvent() {
    return event;
  }


  @JsonProperty(JSON_PROPERTY_EVENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setEvent(@javax.annotation.Nullable String event) {
    this.event = event;
  }


  public Game site(@javax.annotation.Nullable String site) {
    this.site = site;
    return this;
  }

  /**
   * Get site
   * @return site
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getSite() {
    return site;
  }


  @JsonProperty(JSON_PROPERTY_SITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSite(@javax.annotation.Nullable String site) {
    this.site = site;
  }


  public Game date(@javax.annotation.Nullable LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public LocalDate getDate() {
    return date;
  }


  @JsonProperty(JSON_PROPERTY_DATE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDate(@javax.annotation.Nullable LocalDate date) {
    this.date = date;
  }


  public Game round(@javax.annotation.Nullable String round) {
    this.round = round;
    return this;
  }

  /**
   * Get round
   * @return round
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ROUND)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getRound() {
    return round;
  }


  @JsonProperty(JSON_PROPERTY_ROUND)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setRound(@javax.annotation.Nullable String round) {
    this.round = round;
  }


  public Game white(@javax.annotation.Nullable User white) {
    this.white = white;
    return this;
  }

  /**
   * Get white
   * @return white
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_WHITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public User getWhite() {
    return white;
  }


  @JsonProperty(JSON_PROPERTY_WHITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setWhite(@javax.annotation.Nullable User white) {
    this.white = white;
  }


  public Game black(@javax.annotation.Nullable User black) {
    this.black = black;
    return this;
  }

  /**
   * Get black
   * @return black
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_BLACK)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public User getBlack() {
    return black;
  }


  @JsonProperty(JSON_PROPERTY_BLACK)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setBlack(@javax.annotation.Nullable User black) {
    this.black = black;
  }


  public Game result(@javax.annotation.Nullable String result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * @return result
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_RESULT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getResult() {
    return result;
  }


  @JsonProperty(JSON_PROPERTY_RESULT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setResult(@javax.annotation.Nullable String result) {
    this.result = result;
  }


  public Game moves(@javax.annotation.Nullable String moves) {
    this.moves = moves;
    return this;
  }

  /**
   * Get moves
   * @return moves
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_MOVES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getMoves() {
    return moves;
  }


  @JsonProperty(JSON_PROPERTY_MOVES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMoves(@javax.annotation.Nullable String moves) {
    this.moves = moves;
  }


  public Game comment(@javax.annotation.Nullable String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * Get comment
   * @return comment
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COMMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getComment() {
    return comment;
  }


  @JsonProperty(JSON_PROPERTY_COMMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setComment(@javax.annotation.Nullable String comment) {
    this.comment = comment;
  }


  public Game commentWhite(@javax.annotation.Nullable String commentWhite) {
    this.commentWhite = commentWhite;
    return this;
  }

  /**
   * Get commentWhite
   * @return commentWhite
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COMMENT_WHITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCommentWhite() {
    return commentWhite;
  }


  @JsonProperty(JSON_PROPERTY_COMMENT_WHITE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCommentWhite(@javax.annotation.Nullable String commentWhite) {
    this.commentWhite = commentWhite;
  }


  public Game commentBlack(@javax.annotation.Nullable String commentBlack) {
    this.commentBlack = commentBlack;
    return this;
  }

  /**
   * Get commentBlack
   * @return commentBlack
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_COMMENT_BLACK)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCommentBlack() {
    return commentBlack;
  }


  @JsonProperty(JSON_PROPERTY_COMMENT_BLACK)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCommentBlack(@javax.annotation.Nullable String commentBlack) {
    this.commentBlack = commentBlack;
  }


  /**
   * Return true if this Game object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Game game = (Game) o;
    return Objects.equals(this.gameID, game.gameID) &&
        Objects.equals(this.event, game.event) &&
        Objects.equals(this.site, game.site) &&
        Objects.equals(this.date, game.date) &&
        Objects.equals(this.round, game.round) &&
        Objects.equals(this.white, game.white) &&
        Objects.equals(this.black, game.black) &&
        Objects.equals(this.result, game.result) &&
        Objects.equals(this.moves, game.moves) &&
        Objects.equals(this.comment, game.comment) &&
        Objects.equals(this.commentWhite, game.commentWhite) &&
        Objects.equals(this.commentBlack, game.commentBlack);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gameID, event, site, date, round, white, black, result, moves, comment, commentWhite, commentBlack);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Game {\n");
    sb.append("    gameID: ").append(toIndentedString(gameID)).append("\n");
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
    sb.append("    site: ").append(toIndentedString(site)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    round: ").append(toIndentedString(round)).append("\n");
    sb.append("    white: ").append(toIndentedString(white)).append("\n");
    sb.append("    black: ").append(toIndentedString(black)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    moves: ").append(toIndentedString(moves)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    commentWhite: ").append(toIndentedString(commentWhite)).append("\n");
    sb.append("    commentBlack: ").append(toIndentedString(commentBlack)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

