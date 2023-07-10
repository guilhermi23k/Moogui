package app.moogui.constants;

public class SecurityConstants {
	public static final String JWT_KEY = System.getenv().get("JWT_KEY");
    public static final String JWT_HEADER = "Authorization";
}
