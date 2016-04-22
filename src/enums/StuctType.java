package enums;

public enum StuctType {
	STRING,INTEGER,FLOAT,DOUBLE,SHORT,LONG,BYTE,
	BIGDECIMAL,DATETIME,DATE,TIME,TIMESTAMP;
	
	public String toDbType() {
		switch (this) {
		case STRING:
			return "varchar";
		case INTEGER:
			return "int";
		case FLOAT:
			return "real";
		case DOUBLE:
			return "double";
		case SHORT:
			return "smallint";
		case LONG:
			return "bigint";
		case BYTE:
			return "tinyint";
		case BIGDECIMAL:
			return "numeric";
		case DATETIME:
			return "datetime";
		case DATE:
			return "date";
		case TIME:
			return "time";
		case TIMESTAMP:
			return "timestamp";
		default:
			return "";
		}
	}
	public String toJavaType() {
		switch (this) {
		case STRING:
			return "varchar";
		case INTEGER:
			return "int";
		case FLOAT:
			return "real";
		case DOUBLE:
			return "double";
		case SHORT:
			return "smallint";
		case LONG:
			return "bigint";
		case BYTE:
			return "tinyint";
		case BIGDECIMAL:
			return "numeric";
		case DATETIME:
			return "datetime";
		case DATE:
			return "date";
		case TIME:
			return "time";
		case TIMESTAMP:
			return "timestamp";
		default:
			return "";
		}
	}
}
