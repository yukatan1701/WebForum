package forum.enums;

public enum Permissions {
	MODERATOR(0), USER(1);
	private int code;
	Permissions(int code) {
		if (code < 0 || code > 1)
			throw new IllegalArgumentException(code + " not supported.");
		this.code = code;
	}
	public int getCode() { return code; }
	public static Permissions getFromCode(int code) {
		if (code == 0)
			return MODERATOR;
		if (code == 1)
			return USER;
		throw new IllegalArgumentException(code + " not supported.");
	}
}
