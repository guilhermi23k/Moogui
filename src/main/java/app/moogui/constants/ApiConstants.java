package app.moogui.constants;

import org.springframework.beans.factory.annotation.Value;

public class ApiConstants {
	public static final String gpt_key = System.getenv().get("GPT_KEY");
	public static final String tmdb_key = System.getenv().get("TMDB_KEY");
}
 