package forum.enums;

import javax.persistence.AttributeConverter;

public class PermissionsAttributeConverter implements AttributeConverter<Permissions, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Permissions arg0) {
		if (arg0 == null)
			return null;
		return arg0.getCode();
	}

	@Override
	public Permissions convertToEntityAttribute(Integer arg0) {
		if (arg0 == null)
			return null;
		
		switch(arg0) {
		case 0:
			return Permissions.MODERATOR;
		case 1:
			return Permissions.USER;
		default:
			throw new IllegalArgumentException(arg0 + "not supported.");
		}
	}

}
