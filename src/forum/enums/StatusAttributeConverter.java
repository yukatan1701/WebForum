package forum.enums;

import javax.persistence.AttributeConverter;

public class StatusAttributeConverter implements AttributeConverter<Status, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Status arg0) {
		if (arg0 == null)
			return null;
		switch (arg0) {
		case NORMAL:
			return 0;
		case BLOCKED:
			return 1;
		default:
			throw new IllegalArgumentException(arg0 + "not supported.");
		}
	}

	@Override
	public Status convertToEntityAttribute(Integer arg0) {
		if (arg0 == null)
			return null;
		
		switch(arg0) {
		case 0:
			return Status.NORMAL;
		case 1:
			return Status.BLOCKED;
		default:
			throw new IllegalArgumentException(arg0 + "not supported.");
		}
	}

}
