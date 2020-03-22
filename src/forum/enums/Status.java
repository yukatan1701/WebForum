package forum.enums;

public enum Status {
	NORMAL(0), BLOCKED(1);
	private int code;
	Status(int code) {
		if (code < 0 || code > 1)
			throw new IllegalArgumentException(code + " not supported.");
		this.code = code;
	}
	public int getCode() { return code; }
	public static Status getFromCode(int code) {
		if (code == 0)
			return NORMAL;
		if (code == 1)
			return BLOCKED;
		throw new IllegalArgumentException(code + " not supported.");
	}
}
