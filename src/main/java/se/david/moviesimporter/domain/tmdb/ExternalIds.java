package se.david.moviesimporter.domain.tmdb;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalIds {
	@JsonProperty("imdb_id")
	private String imdbId;
	@JsonProperty("facebook_id")
	private String facebookId;
	@JsonProperty("instagram_id")
	private String instagramId;
	@JsonProperty("twitter_id")
	private String twitterId;

	public ExternalIds() {
	}

	public ExternalIds(String imdbId, String facebookId, String instagramId, String twitterId) {
		this.imdbId = imdbId;
		this.facebookId = facebookId;
		this.instagramId = instagramId;
		this.twitterId = twitterId;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getInstagramId() {
		return instagramId;
	}

	public void setInstagramId(String instagramId) {
		this.instagramId = instagramId;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ExternalIds that = (ExternalIds) o;
		return Objects.equals(imdbId, that.imdbId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(imdbId);
	}

	@Override
	public String toString() {
		return "ExternalIds{" +
				"imdbId='" + imdbId + '\'' +
				", facebookId='" + facebookId + '\'' +
				", instagramId='" + instagramId + '\'' +
				", twitterId='" + twitterId + '\'' +
				'}';
	}
}
